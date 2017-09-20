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

import uk.co.cwspencer.gdb.gdbmi.GdbMiValue;

/**
 * Utility functions for use with the message converter.
 */
@SuppressWarnings("unused")
public class GdbMiMessageConverterUtils {
    /**
     * Converts a hexadecimal string to a long.
     */
    public static Long hexStringToLong(final GdbMiValue value) {
        Long longValue = null;
        if (value.type == GdbMiValue.Type.String && value.string.substring(0, 2).equals("0x")) {
            longValue = Long.parseLong(value.string.substring(2), 16);
        }
        return longValue;
    }

    /**
     * Returns true if value is equal to "all".
     */
    public static Boolean valueIsAll(final GdbMiValue value) {
        return value.type == GdbMiValue.Type.String && value.string.equals("all");
    }

    /**
     * Returns null if value is equal to "all", or otherwise requests normal processing for the
     * value.
     */
    public static Object passThroughIfNotAll(final GdbMiValue value) {
        if (valueIsAll(value)) {
            return null;
        }
        return GdbMiMessageConverter.ValueProcessorPassThrough;
    }

    /**
     * Returns null if value is equal to "??", or otherwise requests normal processing for the
     * value.
     */
    public static Object passThroughIfNotQQ(final GdbMiValue value) {
        if (value.type == GdbMiValue.Type.String && value.string.equals("??")) {
            return null;
        }
        return GdbMiMessageConverter.ValueProcessorPassThrough;
    }

}
