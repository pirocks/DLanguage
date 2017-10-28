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

import com.google.common.collect.Lists;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Class representing a list read from a GDB/MI stream.
 */
public class GdbMiList {
    /**
     * The type of list.
     */
    @NotNull
    public final Type type;
    /**
     * List of results. This will be null if type is not Results.
     */
    public @NotNull List<GdbMiResult> results = new ArrayList<>();
    /**
     * List of values. This will be null if type is not Values.
     */
    public @NotNull List<GdbMiValue> values = new ArrayList<>();

    public GdbMiList() {
        this.type = Type.Empty;
    }

    public GdbMiList(@NotNull final GdbMiValue... values) {
        this.type = Type.Values;
        this.values = Lists.newArrayList();
    }

    public GdbMiList(@NotNull final GdbMiResult... results) {
        this.type = Type.Results;
        this.results = Lists.newArrayList(results);
    }

    public GdbMiList(@NotNull final Type type) {
        this.type = type;
        if(type.equals(Type.Values)){
            values = new ArrayList<>();
        }
        else if(type.equals(Type.Results)){
            results = new ArrayList<>();
        }
    }

    /**
     * Converts the list to a string.
     *
     * @return A string containing the contents of the list.
     */
    @NotNull
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        sb.append("[");
        switch (type) {
            case Values:
                for (int i = 0; i != values.size(); ++i) {
                    sb.append(values.get(i));
                    if (i < values.size() - 1) {
                        sb.append(", ");
                    }
                }
                break;

            case Results:
                for (int i = 0; i != results.size(); ++i) {
                    sb.append(results.get(i));
                    if (i < results.size() - 1) {
                        sb.append(", ");
                    }
                }
                break;
        }
        sb.append("]");
        return sb.toString();
    }

    /**
     * Possible types of lists. GDB/MI lists may contain either results or values, but not both. If
     * the list is empty there is no way to know which was intended, so it is classified as a
     * separate type. If the list is empty, both results and values will be null.
     */
    public enum Type {
        Empty,
        Results,
        Values
    }
}
