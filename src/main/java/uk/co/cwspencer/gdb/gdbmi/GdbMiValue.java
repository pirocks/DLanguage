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

package uk.co.cwspencer.gdb.gdbmi;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

/**
 * Class representing a variable value read from a GDB/MI stream.
 */
public class GdbMiValue {
    /**
     * Type of the value.
     */
    public @NotNull
    Type type;
    /**
     * String. Will be null if type is not String.
     */
    public @Nullable
    String string;
    /**
     * Tuple. Will be null if type is not Tuple.
     */
    public @Nullable
    List<GdbMiResult> tuple;
    /**
     * List. Will be null if type is not List.
     */
    public @NotNull
    GdbMiList list;

    /**
     * Default constructor.
     */
    public GdbMiValue() {
    }

    /**
     * Constructor; sets the type only.
     */
    public GdbMiValue(@NotNull final Type type) {
        this.type = type;

        if (type == Type.String) {
            string = "";
        } else if (type == Type.Tuple) {
            tuple = new ArrayList<GdbMiResult>();
        } else if (type == Type.List) {
            list = new GdbMiList();
        }
    }

    /**
     * Converts the value to a string.
     *
     * @return A string containing the value.
     */
    @NotNull
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        switch (type) {
            case String:
                sb.append("\"");
                sb.append(string);
                sb.append("\"");
                break;

            case Tuple: {
                sb.append("{");
                for (int i = 0; i != tuple.size(); ++i) {
                    sb.append(tuple.get(i));
                    if (i < tuple.size() - 1) {
                        sb.append(", ");
                    }
                }
                sb.append("}");
            }
            break;

            case List:
                sb.append(list);
                break;
        }
        return sb.toString();
    }

    /**
     * Possible types the value can take.
     */
    public enum Type {
        String,
        Tuple,
        List
    }
}
