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

package uk.co.cwspencer.gdb.gdbmi.parser;

import com.intellij.execution.impl.ConsoleViewImpl;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import uk.co.cwspencer.gdb.gdbmi.GdbMiList;
import uk.co.cwspencer.gdb.gdbmi.GdbMiRecord;
import uk.co.cwspencer.gdb.gdbmi.GdbMiResult;
import uk.co.cwspencer.gdb.gdbmi.GdbMiValue;

import java.util.ArrayList;
import java.util.Collection;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by francis on 9/24/2017.
 */
public class MagoMiParser2 extends GdbMiParser2 {
    public MagoMiParser2(final ConsoleViewImpl m_gdbRawConsole) {
        super(m_gdbRawConsole);
    }

    @Nullable
    @Override
    protected GdbMiRecord parseLine(@NotNull String line) {
        if (line.charAt(0) == '@') {
            line = line.substring(1);
        }
        return super.parseLine(line);
    }

    @NotNull
    @Override
    protected GdbMiResult parseStackListVariablesLine(@NotNull final String line) {
        final Pattern p = Pattern.compile("\"([A-Za-z0-9_]+)\"");
        final Matcher m = p.matcher(line);
        final GdbMiResult subRes = new GdbMiResult("variables");
        final GdbMiValue stackListVarsVal = new GdbMiValue(GdbMiValue.Type.List);
        stackListVarsVal.list.type = GdbMiList.Type.Values;
        stackListVarsVal.list.values = new ArrayList<GdbMiValue>();
        while (true) {
            final boolean success = m.find();
            if (!success) break;
            final String name = m.group(1);
            final GdbMiValue varVal = new GdbMiValue(GdbMiValue.Type.Tuple);
            varVal.tuple = new ArrayList<GdbMiResult>();

            final GdbMiResult varNameVal = new GdbMiResult("name");
            varNameVal.value.type = GdbMiValue.Type.String;
            varNameVal.value.string = name;
            varVal.tuple.add(varNameVal);

            if (m.groupCount() > 1) {
                final GdbMiResult argVal = new GdbMiResult("arg");
                argVal.value.type = GdbMiValue.Type.String;
                argVal.value.string = m.group(2);
                varVal.tuple.add(argVal);
            }

            stackListVarsVal.list.values.add(varVal);
        }
        subRes.value = stackListVarsVal;
        return subRes;
    }

    @NotNull
    @Override
    protected GdbMiResult parseBreakpointHitLineFrameLine(@NotNull final String line) {
        return super.parseBreakpointHitLineFrameLine("{" + line + "}");
    }

    @NotNull
    @Override
    protected Collection<GdbMiResult> parseVarCreateLine(@NotNull final String line) {
//        return super.parseVarCreateLine(line);
        final Collection<GdbMiResult> result = new ArrayList<GdbMiResult>();
        final Pattern threadPattern = Pattern.compile("(?:thread-id=\"([^\"]+)\"),");
        final Matcher threadMatcher = threadPattern.matcher(line);
        final boolean hasThreadPattern = threadMatcher.find();
        final String threadValue;
        if (hasThreadPattern) {
            threadValue = threadMatcher.group(1);
        } else {
            threadValue = null;
        }
        final Pattern namePattern = Pattern.compile("(?:name=\"([^\"]+)\"),?");
        final Matcher nameMatcher = namePattern.matcher(line);
        final boolean hasNamePattern = nameMatcher.find();
        final String nameValue;
        if (hasNamePattern) {
            nameValue = nameMatcher.group(1);
        } else {
            nameValue = null;
        }
        final Pattern numChildPattern = Pattern.compile("(?:numchild=\"([^\"]+)\"),?");
        final Matcher numChildMatcher = numChildPattern.matcher(line);
        final boolean hasNumChildPattern = numChildMatcher.find();
        final String numChildValue;
        if (hasNumChildPattern) {
            numChildValue = numChildMatcher.group(1);
        } else {
            numChildValue = null;
        }
        final Pattern valuePattern = Pattern.compile("(?:value=\"(.*?)\"),?");
        final Matcher valueMatcher = valuePattern.matcher(line);
        final boolean hasValuePattern = valueMatcher.find();
        final String valueValue;
        if (hasValuePattern) {
            valueValue = valueMatcher.group(1);
        } else {
            valueValue = null;
        }
        final Pattern typePattern = Pattern.compile("(?:type=\"([^\"]+)\"),?");
        final Matcher typeMatcher = typePattern.matcher(line);
        final boolean hasTypePattern = typeMatcher.find();
        final String typeValue;
        if (hasTypePattern) {
            typeValue = typeMatcher.group(1);
        } else {
            typeValue = null;
        }
        final Pattern hasMorePattern = Pattern.compile("(?:has_more=\"([^\"]+)\")");
        final Matcher hasMoreMatcher = hasMorePattern.matcher(line);
        final boolean hasHasMorePattern = hasMoreMatcher.find();
        final String hasMoreValue;
        if (hasHasMorePattern) {
            hasMoreValue = hasMoreMatcher.group(1);
        } else {
            hasMoreValue = null;
        }


        // name="var1"
        if (hasNamePattern) {
            final GdbMiResult nameVal = new GdbMiResult("name");
            nameVal.value.type = GdbMiValue.Type.String;
            nameVal.value.string = nameValue;
            result.add(nameVal);
        }

        // numchild="0"
        if (hasNumChildPattern) {
            final GdbMiResult numChildVal = new GdbMiResult("numchild");
            numChildVal.value.type = GdbMiValue.Type.String;
            numChildVal.value.string = numChildValue;
            result.add(numChildVal);
        }

        // value="false"
        if (hasValuePattern) {
            final GdbMiResult valueVal = new GdbMiResult("value");
            valueVal.value.type = GdbMiValue.Type.String;
            valueVal.value.string = valueValue;
            result.add(valueVal);
        }

        // type="bool"
        if (hasTypePattern) {
            final GdbMiResult typeVal = new GdbMiResult("type");
            typeVal.value.type = GdbMiValue.Type.String;
            typeVal.value.string = typeValue;
            result.add(typeVal);
        }

        // thread-id="1"
        if (hasThreadPattern) {
            final GdbMiResult threadIdVal = new GdbMiResult("thread-id");
            threadIdVal.value.type = GdbMiValue.Type.String;
            threadIdVal.value.string = threadValue;
            result.add(threadIdVal);
        }

        // has_more="0"
        if (hasHasMorePattern) {
            final GdbMiResult hasMoreVal = new GdbMiResult("has_more");
            hasMoreVal.value.type = GdbMiValue.Type.String;
            hasMoreVal.value.string = hasMoreValue;
            result.add(hasMoreVal);
        }

        return result;
    }

    @NotNull
    @Override
    protected GdbMiResult parseChangelistLine(@NotNull final String line) {
        //in real gdb the changelist might be for more than one var but this won't happen with mago.
        final GdbMiResult result = new GdbMiResult("changelist");
        result.value.type = GdbMiValue.Type.List;
        result.value.list = new GdbMiList();
        final GdbMiValue changeVal = new GdbMiValue(GdbMiValue.Type.Tuple);

        final Pattern namePattern = Pattern.compile("\\{name=\"([^\"]+)\",");
        final Matcher nameMatcher = namePattern.matcher(line);
        final boolean hasNamePattern = nameMatcher.find();
        final String nameValue;
        if (hasNamePattern) {
            nameValue = nameMatcher.group(1);
            final GdbMiResult res = new GdbMiResult("name");
            res.value.type = GdbMiValue.Type.String;
            res.value.string = nameValue;
            changeVal.tuple.add(res);
        }
        final Pattern typePattern = Pattern.compile("type=\"([^\"])+\"");
        final Matcher typeMatcher = typePattern.matcher(line);
        final boolean hasTypePattern = typeMatcher.find();
        final String typeValue;
        if (hasTypePattern) {
            typeValue = typeMatcher.group(1);
            final GdbMiResult res = new GdbMiResult("type");
            res.value.type = GdbMiValue.Type.String;
            res.value.string = typeValue;
            changeVal.tuple.add(res);
        }
        final Pattern valuePattern = Pattern.compile("value=\"(.*?)\",");
        final Matcher valueMatcher = valuePattern.matcher(line);
        final boolean hasValuePattern = valueMatcher.find();
        final String valueValue;
        if (hasValuePattern) {
            valueValue = valueMatcher.group(1);
            final GdbMiResult res = new GdbMiResult("value");
            res.value.type = GdbMiValue.Type.String;
            res.value.string = valueValue;
            changeVal.tuple.add(res);
        }
        final Pattern childPattern = Pattern.compile("numchild=\"([0-9]+)\"");
        final Matcher childMatcher = childPattern.matcher(line);
        final boolean hasChildPattern = childMatcher.find();
        final String childValue;
        if (hasChildPattern) {
            childValue = childMatcher.group(1);
            final GdbMiResult res = new GdbMiResult("numchild");
            res.value.type = GdbMiValue.Type.String;
            res.value.string = childValue;
            changeVal.tuple.add(res);
        }
        final Pattern scopePattern = Pattern.compile("in_scope=\"([^\"]+)\",");
        final Matcher scopeMatcher = scopePattern.matcher(line);
        final boolean hasScopePattern = scopeMatcher.find();
        final String scopeValue;
        if (hasScopePattern) {
            scopeValue = scopeMatcher.group(1);
            final GdbMiResult res = new GdbMiResult("in_scope");
            res.value.type = GdbMiValue.Type.String;
            res.value.string = scopeValue;
            changeVal.tuple.add(res);
        }
        final Pattern typeChangedPattern = Pattern.compile("type_changed=\"([^\"]+)\",");
        final Matcher typeChangedMatcher = typeChangedPattern.matcher(line);
        final boolean hasTypeChangedPattern = typeChangedMatcher.find();
        final String typeChangedValue;
        if (hasTypeChangedPattern) {
            typeChangedValue = typeChangedMatcher.group(1);
            final GdbMiResult res = new GdbMiResult("type_changed");
            res.value.type = GdbMiValue.Type.String;
            res.value.string = typeChangedValue;
            changeVal.tuple.add(res);
        }
        final Pattern hasMorePattern = Pattern.compile("has_more=\"([^\"]+)\"");
        final Matcher hasMoreMatcher = hasMorePattern.matcher(line);
        final boolean hasHasMorePattern = hasMoreMatcher.find();
        final String hasMoreValue;
        if (hasHasMorePattern) {
            hasMoreValue = hasMoreMatcher.group(1);
            final GdbMiResult res = new GdbMiResult("has_more");
            res.value.type = GdbMiValue.Type.String;
            res.value.string = hasMoreValue;
            changeVal.tuple.add(res);
        }
        if (result.value.list.values == null) {
            result.value.list.type = GdbMiList.Type.Values;
            result.value.list.values = new ArrayList<>();
        }
        result.value.list.values.add(changeVal);
        return result;
    }
}
