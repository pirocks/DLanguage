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

package uk.co.cwspencer.gdb.gdbmi

import com.intellij.execution.impl.ConsoleViewImpl
import org.fest.util.Lists
import uk.co.cwspencer.gdb.gdbmi.parser.GdbMiParser2

import java.util.ArrayList
import java.util.regex.Pattern

/**
 * Created by francis on 9/24/2017.
 */
class MagoMiParser2(m_gdbRawConsole: ConsoleViewImpl) : GdbMiParser2(m_gdbRawConsole) {

    override fun parseLine(line: String): GdbMiRecord? {
        var line = line
        if (line[0] == '@') {
            line = line.substring(1)
        }
        return super.parseLine(line)
    }

    override fun parseStackListVariablesLine(line: String): GdbMiResult {
        val p = Pattern.compile("\"([A-Za-z0-9_]+)\"")
        val m = p.matcher(line)
        val stackListVarsVal = GdbMiValue(GdbMiList(GdbMiList.Type.Values))
        while (true) {
            val success = m.find()
            if (!success) break
            val name = m.group(1)
            val varNameVal = GdbMiResult("name", GdbMiValue(name))
            val varVal = GdbMiValue(Lists.newArrayList(varNameVal))
            if (m.groupCount() > 1) {
                val argVal = GdbMiResult("arg",GdbMiValue(m.group(2)))
                varVal.tuple.add(argVal)
            }

            stackListVarsVal.list.values.add(varVal)
        }
        val subRes = GdbMiResult("variables",stackListVarsVal)
        return subRes
    }

    override fun parseBreakpointHitLineFrameLine(line: String): GdbMiResult {
        return super.parseBreakpointHitLineFrameLine("{$line}")
    }

    override fun parseVarCreateLine(line: String): Collection<GdbMiResult> {
        //        return super.parseVarCreateLine(line);
        val result = ArrayList<GdbMiResult>()
        val threadPattern = Pattern.compile("(?:thread-id=\"([^\"]+)\"),")
        val threadMatcher = threadPattern.matcher(line)
        val hasThreadPattern = threadMatcher.find()
        val threadValue: String?
        if (hasThreadPattern) {
            threadValue = threadMatcher.group(1)
        } else {
            threadValue = null
        }
        val namePattern = Pattern.compile("(?:name=\"([^\"]+)\"),?")
        val nameMatcher = namePattern.matcher(line)
        val hasNamePattern = nameMatcher.find()
        val nameValue: String?
        if (hasNamePattern) {
            nameValue = nameMatcher.group(1)
        } else {
            nameValue = null
        }
        val numChildPattern = Pattern.compile("(?:numchild=\"([^\"]+)\"),?")
        val numChildMatcher = numChildPattern.matcher(line)
        val hasNumChildPattern = numChildMatcher.find()
        val numChildValue: String?
        if (hasNumChildPattern) {
            numChildValue = numChildMatcher.group(1)
        } else {
            numChildValue = null
        }
        val valuePattern = Pattern.compile("(?:value=\"(.*?)\"),?")
        val valueMatcher = valuePattern.matcher(line)
        val hasValuePattern = valueMatcher.find()
        val valueValue: String?
        if (hasValuePattern) {
            valueValue = valueMatcher.group(1)
        } else {
            valueValue = null
        }
        val typePattern = Pattern.compile("(?:type=\"([^\"]+)\"),?")
        val typeMatcher = typePattern.matcher(line)
        val hasTypePattern = typeMatcher.find()
        val typeValue: String?
        if (hasTypePattern) {
            typeValue = typeMatcher.group(1)
        } else {
            typeValue = null
        }
        val hasMorePattern = Pattern.compile("(?:has_more=\"([^\"]+)\")")
        val hasMoreMatcher = hasMorePattern.matcher(line)
        val hasHasMorePattern = hasMoreMatcher.find()
        val hasMoreValue: String?
        if (hasHasMorePattern) {
            hasMoreValue = hasMoreMatcher.group(1)
        } else {
            hasMoreValue = null
        }


        // name="var1"
        if (hasNamePattern) {
            val nameVal = GdbMiResult("name", GdbMiValue(nameValue!!))
            result.add(nameVal)
        }

        // numchild="0"
        if (hasNumChildPattern) {
            val numChildVal = GdbMiResult("numchild", GdbMiValue(numChildValue!!))
            result.add(numChildVal)
        }

        // value="false"
        if (hasValuePattern) {
            val valueVal = GdbMiResult("value", GdbMiValue(valueValue!!))
            result.add(valueVal)
        }

        // type="bool"
        if (hasTypePattern) {
            val typeVal = GdbMiResult("type", GdbMiValue(typeValue!!))
            result.add(typeVal)
        }

        // thread-id="1"
        if (hasThreadPattern) {
            val threadIdVal = GdbMiResult("thread-id", GdbMiValue(threadValue!!))
            result.add(threadIdVal)
        }

        // has_more="0"
        if (hasHasMorePattern) {
            val hasMoreVal = GdbMiResult("has_more", GdbMiValue(hasMoreValue!!))
            result.add(hasMoreVal)
        }

        return result
    }

    override fun parseChangelistLine(line: String): GdbMiResult {
        //in real gdb the changelist might be for more than one var but this won't happen with mago.
        val result = GdbMiResult("changelist", GdbMiValue(GdbMiValue.Type.List))
        val changeVal = GdbMiValue(GdbMiValue.Type.Tuple)

        val namePattern = Pattern.compile("\\{name=\"([^\"]+)\",")
        val nameMatcher = namePattern.matcher(line)
        val hasNamePattern = nameMatcher.find()
        val nameValue: String
        if (hasNamePattern) {
            nameValue = nameMatcher.group(1)
            val res = GdbMiResult("name", GdbMiValue(nameValue))
            changeVal.tuple.add(res)
        }
        val typePattern = Pattern.compile("type=\"([^\"])+\"")
        val typeMatcher = typePattern.matcher(line)
        val hasTypePattern = typeMatcher.find()
        val typeValue: String
        if (hasTypePattern) {
            typeValue = typeMatcher.group(1)
            val res = GdbMiResult("type", GdbMiValue(typeValue))
            changeVal.tuple.add(res)
        }
        val valuePattern = Pattern.compile("value=\"(.*?)\",")
        val valueMatcher = valuePattern.matcher(line)
        val hasValuePattern = valueMatcher.find()
        val valueValue: String
        if (hasValuePattern) {
            valueValue = valueMatcher.group(1)
            val res = GdbMiResult("value", GdbMiValue(valueValue))
            changeVal.tuple.add(res)
        }
        val childPattern = Pattern.compile("numchild=\"([0-9]+)\"")
        val childMatcher = childPattern.matcher(line)
        val hasChildPattern = childMatcher.find()
        val childValue: String
        if (hasChildPattern) {
            childValue = childMatcher.group(1)
            val res = GdbMiResult("numchild", GdbMiValue(childValue))
            changeVal.tuple.add(res)
        }
        val scopePattern = Pattern.compile("in_scope=\"([^\"]+)\",")
        val scopeMatcher = scopePattern.matcher(line)
        val hasScopePattern = scopeMatcher.find()
        val scopeValue: String
        if (hasScopePattern) {
            scopeValue = scopeMatcher.group(1)
            val res = GdbMiResult("in_scope", GdbMiValue(scopeValue))
            changeVal.tuple.add(res)
        }
        val typeChangedPattern = Pattern.compile("type_changed=\"([^\"]+)\",")
        val typeChangedMatcher = typeChangedPattern.matcher(line)
        val hasTypeChangedPattern = typeChangedMatcher.find()
        val typeChangedValue: String
        if (hasTypeChangedPattern) {
            typeChangedValue = typeChangedMatcher.group(1)
            val res = GdbMiResult("type_changed", GdbMiValue(typeChangedValue))
            changeVal.tuple.add(res)
        }
        val hasMorePattern = Pattern.compile("has_more=\"([^\"]+)\"")
        val hasMoreMatcher = hasMorePattern.matcher(line)
        val hasHasMorePattern = hasMoreMatcher.find()
        val hasMoreValue: String
        if (hasHasMorePattern) {
            hasMoreValue = hasMoreMatcher.group(1)
            val res = GdbMiResult("has_more", GdbMiValue(hasMoreValue))
            changeVal.tuple.add(res)
        }
//        if (result.value.list.values == null) {
//            result.value.list.type = GdbMiList.Type.Values
//            result.value.list.values = ArrayList()
//        }
        result.value.list.values.add(changeVal)
        return result
    }
}
