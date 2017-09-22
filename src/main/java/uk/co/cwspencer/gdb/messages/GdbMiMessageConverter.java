/*
 * (These files where modified from: https://bitbucket.org/spencercw/ideagdb/src
 * Original Copyright:
 * Copyright (c) 2013 Chris Spencer <spencercw@gmail.com>
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.)
 */

package uk.co.cwspencer.gdb.messages;

import com.intellij.openapi.diagnostic.Logger;
import uk.co.cwspencer.gdb.gdbmi.GdbMiList;
import uk.co.cwspencer.gdb.gdbmi.GdbMiResult;
import uk.co.cwspencer.gdb.gdbmi.GdbMiResultRecord;
import uk.co.cwspencer.gdb.gdbmi.GdbMiValue;
import uk.co.cwspencer.gdb.messages.annotations.GdbMiConversionRule;
import uk.co.cwspencer.gdb.messages.annotations.GdbMiDoneEvent;
import uk.co.cwspencer.gdb.messages.annotations.GdbMiEvent;
import uk.co.cwspencer.gdb.messages.annotations.GdbMiField;

import java.lang.reflect.*;
import java.util.Arrays;
import java.util.List;

/**
 * Class which handles the conversion of GDB/MI messages to Java objects.
 */
public class GdbMiMessageConverter {
    /**
     * Special value that can be returned by value processors to indicate that the normal automatic
     * processing should be applied to the value.
     */
    public static final Object ValueProcessorPassThrough = new Object();
    private static final Logger m_log =
        Logger.getInstance("#uk.co.cwspencer.gdb.messages.GdbMiMessageConverter");

    /**
     * Converts the given GDB/MI result record into a suitable Java object.
     *
     * @param record The GDB result record.
     * @return The new object, or null if it could not be created.
     */
    public static GdbEvent processRecord(final GdbMiResultRecord record) {
        return processRecord(record, null);
    }

    /**
     * Converts the given GDB/MI result record into a suitable Java object.
     *
     * @param record      The GDB result record.
     * @param commandType The original command type (excluding any parameters) that was sent which
     *                    caused GDB to send the record. This is used for mapping 'done' events to the appropriate
     *                    type.
     * @return The new object, or null if it could not be created.
     */
    public static GdbEvent processRecord(final GdbMiResultRecord record, final String commandType) {
        // Iterate through the list of event types
        GdbEvent event = null;
        for (Class<?> clazz : GdbMiEventTypes.classes) {
            // Verify the type has a GdbMiEvent annotation
            final GdbMiEvent eventAnnotation = clazz.getAnnotation(GdbMiEvent.class);
            if (eventAnnotation == null) {
                m_log.warn("Class " + clazz.getName() + " is in the GdbMiEventTypes.classes list " +
                    "but does not have a GdbMiEvent annotation");
                continue;
            }

            // Check if this type is appropriate for the record
            if (eventAnnotation.recordType() == record.type) {
                boolean match = false;
                for (final String className : eventAnnotation.className()) {
                    if (className.equals(record.className)) {
                        match = true;
                        break;
                    }
                }

                if (match) {
                    // Found a matching event type wrapper
                    List<GdbMiResult> results = record.getResults();

                    // If it is a 'done' event then search for a more specific event type
                    if (commandType != null && clazz.equals(GdbDoneEvent.class)) {
                        for (final Class<?> doneEventClass : GdbMiEventTypes.doneEventTypes) {
                            final GdbMiDoneEvent doneEventAnnotation =
                                doneEventClass.getAnnotation(GdbMiDoneEvent.class);
                            if (doneEventAnnotation == null) {
                                m_log.warn("Class " + doneEventClass.getName() + " is in the " +
                                    "GdbMiEventTypes.doneEventTypes list but does not have a" +
                                    "GdbMiDoneEvent annotation");
                                continue;
                            }

                            if (doneEventAnnotation.command().equals(commandType)) {
                                // Found a match; check if we need to transpose a specific result
                                // onto this class
                                if (!doneEventAnnotation.transpose().isEmpty()) {
                                    final List<GdbMiResult> transposedResults = transposeDoneEvent(record,
                                        doneEventAnnotation);
                                    if (transposedResults == null) {
                                        m_log.warn("Class " + doneEventClass.getName() + " is " +
                                            "trying to transpose '" +
                                            doneEventAnnotation.transpose() + "', but the result " +
                                            "does not exist or is not a tuple or list of results");
                                        break;
                                    }

                                    results = transposedResults;
                                }

                                clazz = doneEventClass;
                                break;
                            }
                        }
                    }

                    // Process the object
                    event = (GdbEvent) processObject(clazz, results);
                    break;
                }

            }

        }
        return event;
    }

    /**
     * Extracts data from the result requested by the class.
     *
     * @param record              The result record.
     * @param doneEventAnnotation The annotation on the class.
     * @return The new list of results, or null if it could not be transposed.
     */
    private static List<GdbMiResult> transposeDoneEvent(final GdbMiResultRecord record,
                                                        final GdbMiDoneEvent doneEventAnnotation) {
        // Search for the requested result
        for (final GdbMiResult result : record.getResults()) {
            if (result.variable.equals(doneEventAnnotation.transpose())) {
                // Found it; check it is an appropriate type (it must be a tuple or a list of
                // results)
                if (!(result.value.type == GdbMiValue.Type.Tuple ||
                    (result.value.type == GdbMiValue.Type.List &&
                        (result.value.list.type == GdbMiList.Type.Empty ||
                            result.value.list.type == GdbMiList.Type.Results)))) {
                    return null;
                }

                return result.value.type ==
                    GdbMiValue.Type.Tuple ? result.value.tuple : result.value.list.results;
            }
        }
        return null;
    }

    /**
     * Converts the given list of GDB/MI results to an object of the given type.
     *
     * @param clazz   The type of object to create.
     * @param results The results from GDB.
     * @return The new object, or null if it could not be created.
     */
    public static Object processObject(final Class<?> clazz, final List<GdbMiResult> results) {
        try {
            final Object object = clazz.newInstance();

            // Populate the fields with data from the result
            final Field[] fields = clazz.getFields();
            for (final Field field : fields) {
                final GdbMiField fieldAnnotation = field.getAnnotation(GdbMiField.class);
                if (fieldAnnotation == null) {
                    continue;
                }

                // Find a result with the requested variable name
                for (final GdbMiResult result : results) {
                    if (!fieldAnnotation.name().equals(result.variable)) {
                        continue;
                    }

                    // Found a matching field; convert the value
                    convertField(object, clazz, field, fieldAnnotation, result);
                    break;
                }
            }

            return object;
        } catch (final Throwable ex) {
            m_log.warn("Failed to convert GDB/MI message to a Java object", ex);
            return null;
        }
    }

    /**
     * Converts a GdbMiResult into a suitable Java type and puts it in the given field on the given
     * object.
     *
     * @param event           The object to put the value into.
     * @param clazz           The class of the object.
     * @param field           The field on the object to put the value into.
     * @param fieldAnnotation The GdbMiField annotation on the field.
     * @param result          The result to get the data from.
     */
    private static void convertField(final Object event, final Class<?> clazz, final Field field,
                                     final GdbMiField fieldAnnotation, final GdbMiResult result) throws InvocationTargetException,
        IllegalAccessException {
        // Check the result type is supported by the field
        boolean foundValueType = false;
        for (final GdbMiValue.Type valueType : fieldAnnotation.valueType()) {
            if (valueType == result.value.type) {
                foundValueType = true;
                break;
            }
        }
        if (!foundValueType) {
            m_log.warn("Annotation on \"" + field.getName() + "\" requires on of GDB/MI types \"" +
                Arrays.toString(fieldAnnotation.valueType()) + "\"; got \"" + result.value.type + "\"");
            return;
        }

        if (!fieldAnnotation.valueProcessor().isEmpty()) {
            // Field has a custom value processor
            convertFieldUsingValueProcessor(event, clazz, field, fieldAnnotation, result);
        } else {
            // Field does not have a custom value processor; convert it manually
            convertFieldManually(event, field, result);
        }
    }

    /**
     * Converts a GdbMiResult into a suitable Java type and puts it in the given field on the given
     * object using a custom value processor defined by the field.
     *
     * @param event           The object to put the value into.
     * @param clazz           The class of the object.
     * @param field           The field on the object to put the value into.
     * @param fieldAnnotation The GdbMiField annotation on the field.
     * @param result          The result to get the data from.
     */
    private static void convertFieldUsingValueProcessor(final Object event, final Class<?> clazz, final Field field,
                                                        final GdbMiField fieldAnnotation, final GdbMiResult result) throws InvocationTargetException,
        IllegalAccessException {
        // Get the value processor function
        final Method valueProcessor;
        try {
            final String valueProcessorName = fieldAnnotation.valueProcessor();
            final int lastDotIndex = valueProcessorName.lastIndexOf('.');
            if (lastDotIndex == -1) {
                // Value processor is a function on the parent class
                valueProcessor = clazz.getMethod(valueProcessorName, GdbMiValue.class);
            } else {
                // Value processor is a fully-qualified name
                final String className = valueProcessorName.substring(0, lastDotIndex);
                final String methodName = valueProcessorName.substring(lastDotIndex + 1);

                final Class<?> valueProcessorClass = Class.forName(className);
                valueProcessor = valueProcessorClass.getMethod(methodName, GdbMiValue.class);
            }
        } catch (final NoSuchMethodException ex) {
            m_log.warn("Annotation on " + field.getName() + " has value processor " +
                fieldAnnotation.valueProcessor() + ", but no such function exists on the class " +
                "(or it does not take the right arguments)", ex);
            return;
        } catch (final ClassNotFoundException ex) {
            m_log.warn("Annotation on " + field.getName() + " has value processor " +
                fieldAnnotation.valueProcessor() + ", but the referenced class does not exist", ex);
            return;
        }

        // Invoke the method
        final Object resultValue = null;
        final Object value;
        try {
            value = valueProcessor.invoke(event, result.value);
        } catch (final Throwable ex) {
            m_log.warn("Field to invoke value processor for field " + field.getName() + " with " +
                "value " + resultValue, ex);
            return;
        }

        // We don't need to do anything if the value processor returned null
        if (value == null) {
            return;
        }

        // If the value processor returns the special value ValueProcessorPassThrough then we need
        // to apply the default processing to the value
        if (value == ValueProcessorPassThrough) {
            convertFieldManually(event, field, result);
            return;
        }

        // Check the returned value is of the correct type
        if (!field.getType().isAssignableFrom(value.getClass())) {
            m_log.warn("Field " + field.getName() + " is of type " + field.getType() + ", but " +
                "the value processor returned " + value + " [type=" + value.getClass() + "]");
            return;
        }

        // Set the value on the field
        try {
            field.set(event, value);
        } catch (final IllegalAccessException ex) {
            m_log.warn("Failed to set value on field " + field, ex);
        }
    }

    /**
     * Converts a GdbMiResult into a suitable Java type and puts it in the given field on the given
     * object using built-in value processors.
     *
     * @param event  The object to put the value into.
     * @param field  The field on the object to put the value into.
     * @param result The result to get the data from.
     */
    static void convertFieldManually(final Object event, final Field field, final GdbMiResult result) throws
        InvocationTargetException, IllegalAccessException {
        ParameterizedType genericType = null;
        {
            final Type basicGenericType = field.getGenericType();
            if (basicGenericType instanceof ParameterizedType) {
                genericType = (ParameterizedType) basicGenericType;
            }
        }

        final Object value = applyConversionRules(field.getType(), genericType, result.value);
        if (value != null) {
            field.set(event, value);
        } else {
            m_log.warn("No conversion rules were available to convert GDB/MI result '" +
                result + "' for field " + field);
        }
    }

    /**
     * Applies the conversion rules to the given GDB/MI result and returns the converted object.
     *
     * @param targetType        The type of object to be created.
     * @param genericTargetType The generic type of the object to be created. May be null.
     * @param value             The value to get the data from.
     * @return The new object, or null if it could not be created.
     */
    static Object applyConversionRules(final Class<?> targetType, final ParameterizedType genericTargetType,
                                       final GdbMiValue value) throws InvocationTargetException, IllegalAccessException {
        // Apply the conversion rules until we get a match
        Object jValue = null;
        final Method[] methods = GdbMiValueConversionRules.class.getMethods();
        for (final Method method : methods) {
            // Verify it is a conversion rule
            if (method.getAnnotation(GdbMiConversionRule.class) == null) {
                continue;
            }

            jValue = method.invoke(null, targetType, genericTargetType, value);
            if (jValue != null) {
                break;
            }
        }
        return jValue;
    }
}
