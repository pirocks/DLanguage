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

import com.intellij.execution.ui.ConsoleView;
import com.intellij.execution.ui.ConsoleViewContentType;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import uk.co.cwspencer.gdb.gdbmi.*;

import java.io.UnsupportedEncodingException;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

//import com.intellij.openapi.util.SystemInfo;

/**
 * Parser for GDB/MI output version 2.
 *
 * @author Florin Patan <florinpatan@gmail.com>
 */
public class GdbMiParser2 {

    protected static final Set<String> START_TOKENS = new HashSet<String>(Arrays.asList(
        "*", "+", "=", "~", "@", "&"));
    @Nullable
    protected final ConsoleView rawConsole;
    // List of unprocessed records
    protected final List<GdbMiRecord> m_records = new ArrayList<GdbMiRecord>();
    // Partially processed record
    protected GdbMiResultRecord m_resultRecord;
    protected GdbMiStreamRecord m_streamRecord;
    @Nullable
    protected Long currentToken;

    public GdbMiParser2(@Nullable final ConsoleView rawConsole) {
        this.rawConsole = rawConsole;
    }

    @NotNull
    protected GdbMiResult parseStackListVariablesLine(final @NotNull String line) {
        final GdbMiResult subRes = new GdbMiResult("variables");
        final GdbMiValue stackListVarsVal = new GdbMiValue(GdbMiValue.Type.List);
        stackListVarsVal.list.type = GdbMiList.Type.Values;
        stackListVarsVal.list.values = new ArrayList<GdbMiValue>();

        final Pattern p = Pattern.compile("\\{(?:name=\"([^\"]+)\")(?:,arg=\"([^\"]+)\")?\\}");
        final Matcher m = p.matcher(line);

        while (m.find()) {
            final GdbMiValue varVal = new GdbMiValue(GdbMiValue.Type.Tuple);
            varVal.tuple = new ArrayList<GdbMiResult>();

            final GdbMiResult varNameVal = new GdbMiResult("name");
            varNameVal.value.type = GdbMiValue.Type.String;
            varNameVal.value.string = m.group(1);
            varVal.tuple.add(varNameVal);

            if (m.group(2) != null) {
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
    protected GdbMiResult parseChangelistLine(final @NotNull String line) {
        final GdbMiResult result = new GdbMiResult("changelist");
        result.value.type = GdbMiValue.Type.List;
        result.value.list = new GdbMiList();

        Pattern p = Pattern.compile(
            "(?:\\{name=\"([^\"]+)\"," +
                "value=\"(.*?)\"," +
                "in_scope=\"([^\"]+)\"," +
                "type_changed=\"([^\"]+)\"," +
                "has_more=\"([^\"]+)\"\\})+"
        );
        Matcher m = p.matcher(line);
        if (m.find()) {
            parseChangelistLineReal(line, result, true);
        }

        p = Pattern.compile(
            "(?:\\{name=\"([^\"]+)\"," +
                "in_scope=\"([^\"]+)\"," +
                "type_changed=\"([^\"]+)\"," +
                "has_more=\"([^\"]+)\"\\})+"
        );
        m = p.matcher(line);
        if (m.find()) {
            parseChangelistLineReal(line, result, false);
        }

        return result;
    }

    protected void parseChangelistLineReal(final @NotNull String line, final @NotNull GdbMiResult result, final boolean includeValue) {
        String regex = "(?:\\{name=\"([^\"]+)\",";

        if (includeValue) {
            regex += "value=\"(.*?)\",";
        }

        regex += "in_scope=\"([^\"]+?)\"," +
            "type_changed=\"([^\"].+?)\"," +
            "(?:new_type=\"([^\"]+?)\")?,?" +
            "(?:new_num_children=\"([^\"]+?)\")?,?" +
            "has_more=\"([^\"]+?)\"\\})";

        final Pattern p = Pattern.compile(regex);
        final Matcher m = p.matcher(line);

        while (m.find()) {
            Integer matchGroup = 0;
            final GdbMiValue changeVal = new GdbMiValue(GdbMiValue.Type.Tuple);

            // name: "var5"
            final GdbMiResult nameVal = new GdbMiResult("name");
            nameVal.value.type = GdbMiValue.Type.String;
            nameVal.value.string = m.group(++matchGroup);
            changeVal.tuple.add(nameVal);

            if (includeValue) {
                // value: "3,3300000000000001"
                final GdbMiResult valueVal = new GdbMiResult("value");
                valueVal.value.type = GdbMiValue.Type.String;
                valueVal.value.string = m.group(++matchGroup);
                changeVal.tuple.add(valueVal);
            }

            // in_scope: "true"
            final GdbMiResult inScopeVal = new GdbMiResult("in_scope");
            inScopeVal.value.type = GdbMiValue.Type.String;
            inScopeVal.value.string = m.group(++matchGroup);
            changeVal.tuple.add(inScopeVal);

            // type_changed: "false"
            final GdbMiResult typeChangedVal = new GdbMiResult("type_changed");
            typeChangedVal.value.type = GdbMiValue.Type.String;
            typeChangedVal.value.string = m.group(++matchGroup);
            changeVal.tuple.add(typeChangedVal);

            if (m.group(++matchGroup) != null) {
                // new_type="error"
                final GdbMiResult newTypeVal = new GdbMiResult("new_type");
                newTypeVal.value.type = GdbMiValue.Type.String;
                newTypeVal.value.string = m.group(matchGroup);
                changeVal.tuple.add(newTypeVal);
            }

            if (m.group(++matchGroup) != null) {
                // new_num_children="2"
                final GdbMiResult newNumChildrenVal = new GdbMiResult("new_num_children");
                newNumChildrenVal.value.type = GdbMiValue.Type.String;
                newNumChildrenVal.value.string = m.group(matchGroup);
                changeVal.tuple.add(newNumChildrenVal);
            }

            // has_more: "0"
            final GdbMiResult hasMoreVal = new GdbMiResult("has_more");
            hasMoreVal.value.type = GdbMiValue.Type.String;
            hasMoreVal.value.string = m.group(++matchGroup);
            changeVal.tuple.add(hasMoreVal);

            if (result.value.list.values == null) {
                result.value.list.type = GdbMiList.Type.Values;
                result.value.list.values = new ArrayList<GdbMiValue>();
            }

            result.value.list.values.add(changeVal);
        }
    }

    @NotNull
    protected GdbMiResult parseMsgLine(final @NotNull String line) {
        // msg="No frames found."
        final GdbMiResult result = new GdbMiResult("msg");
        result.value.type = GdbMiValue.Type.String;
        result.value.string = line.substring(5, line.length() - 1);

        return result;
    }

    @NotNull
    protected GdbMiResult parseRunningThreadId(final @NotNull String line) {
        final Pattern p = Pattern.compile("(?:thread-id=\"([^\"]+)\")");
        final Matcher m = p.matcher(line);

        // thread-id="all"
        final GdbMiResult result = new GdbMiResult("thread-id");
        result.value.type = GdbMiValue.Type.String;

        if (m.find()) {
            result.value.string = m.group(1);
        }

        return result;
    }

    @NotNull
    protected GdbMiResult parseNumChildChildsLine(final @NotNull String line) {
        final GdbMiResult result = new GdbMiResult("children");
        result.value.type = GdbMiValue.Type.List;
        result.value.list = new GdbMiList();
        result.value.list.type = GdbMiList.Type.Results;
        result.value.list.results = new ArrayList<GdbMiResult>();

        Pattern p = Pattern.compile("thread-id");
        Matcher m = p.matcher(line);
        final Boolean hasThreadId = m.find();

        String pattern = "(?:child=\\{" +
            "(?:name=\"([^\"]+)\")," +
            "(?:exp=\"([^\"]+)\")," +
            "(?:numchild=\"(\\d+)\")," +
            "(?:value=\"(.*?)\")," +
            "(?:type=\"([^\"]+)\")";

        if (hasThreadId) {
            pattern += ",(?:thread-id=\"([^\"]+)\")";
        }

        pattern += "\\})";

        p = Pattern.compile(pattern);
        m = p.matcher(line);

        final Pattern stringP = Pattern.compile("0x\\w+\\s(?:<(?:[^>].+?)>\\s)?\\\\\"(.*)");
        Matcher stringM;

        while (m.find()) {
            final GdbMiResult childVal = new GdbMiResult("child");
            childVal.value.type = GdbMiValue.Type.Tuple;
            childVal.value.tuple = new ArrayList<GdbMiResult>();

            final GdbMiResult nameVal = new GdbMiResult("name");
            nameVal.value.type = GdbMiValue.Type.String;
            nameVal.value.string = m.group(1);
            childVal.value.tuple.add(nameVal);

            final GdbMiResult expVal = new GdbMiResult("exp");
            expVal.value.type = GdbMiValue.Type.String;
            expVal.value.string = m.group(2);
            childVal.value.tuple.add(expVal);

            final GdbMiResult numChildVal = new GdbMiResult("numchild");
            numChildVal.value.type = GdbMiValue.Type.String;
            numChildVal.value.string = m.group(3);
            childVal.value.tuple.add(numChildVal);

            final GdbMiResult valueVal = new GdbMiResult("value");
            valueVal.value.type = GdbMiValue.Type.String;
            valueVal.value.string = m.group(4);
            stringM = stringP.matcher(valueVal.value.string);
            if (stringM.find()) {
                valueVal.value.string = stringM.group(1).substring(0, stringM.group(1).length() - 2);
            }
            childVal.value.tuple.add(valueVal);

            final GdbMiResult typeVal = new GdbMiResult("type");
            typeVal.value.type = GdbMiValue.Type.String;
            typeVal.value.string = m.group(5);
            childVal.value.tuple.add(typeVal);

            final GdbMiResult threadIdVal = new GdbMiResult("thread-id");
            threadIdVal.value.type = GdbMiValue.Type.String;
            if (hasThreadId) {
                threadIdVal.value.string = m.group(6);
            } else {
                threadIdVal.value.string = "1";
            }
            childVal.value.tuple.add(threadIdVal);

            result.value.list.results.add(childVal);
        }

        return result;
    }

    protected GdbMiResult parseBreakpointHitLineFrameLine(@NotNull final String line) {
//        if (SystemInfo.isWindows) {
//            line = "{" + line + "}";
//        }
        final Collection<GdbMiResult> results = parseFrameLine(line);
        final GdbMiResult[] result = results.toArray(new GdbMiResult[results.size()]);
        return result[0];
    }

    @NotNull
    protected GdbMiResult parseStackListLine(final @NotNull String line) {
        final GdbMiResult subRes = new GdbMiResult("stack");
        final GdbMiValue stackListVal = new GdbMiValue(GdbMiValue.Type.List);

        stackListVal.list.results = new ArrayList<GdbMiResult>();
        stackListVal.list.type = GdbMiList.Type.Results;
        stackListVal.list.results.addAll(parseFrameLine(line));

        subRes.value = stackListVal;
        return subRes;
    }

    @NotNull
    protected Collection<GdbMiResult> parseFrameLine(final @NotNull String line) {
        final Collection<GdbMiResult> result = new ArrayList<GdbMiResult>();

        Pattern p = Pattern.compile("args=\\[");
        Matcher m = p.matcher(line);
        final Boolean hasArgs = m.find();

        String pattern = "\\{" +
            "(?:level=\"(\\d+)\")?,?" +
            "(?:addr=\"([^\"]+)\")?,?" +
            "(?:func=\"([^\"]+)\")?,?";

        if (hasArgs) {
            pattern += "(?:args=\\[(.*?)\\])?,?";
        }

        pattern += "(?:file=\"([^\"]+)\")?,?" +
            "(?:fullname=\"([^\"]+)\")?,?" +
            "(?:line=\"(\\d+)\")?,?" +
            "(?:from=\"([^\"]+)\")?,?" +
            "\\}";

        p = Pattern.compile(pattern);
        m = p.matcher(line);

        while (m.find()) {
            Integer matchGroup = 0;
            final GdbMiResult subRes = new GdbMiResult("frame");
            final GdbMiValue frameVal = new GdbMiValue(GdbMiValue.Type.Tuple);

            // level="0"
            if (m.group(++matchGroup) != null) {
                final GdbMiResult levelVal = new GdbMiResult("level");
                levelVal.value.type = GdbMiValue.Type.String;
                levelVal.value.string = m.group(matchGroup);
                frameVal.tuple.add(levelVal);
            }

            // addr="0x0000000000400c57"
            if (m.group(++matchGroup) != null) {
                final GdbMiResult addrVal = new GdbMiResult("addr");
                addrVal.value.type = GdbMiValue.Type.String;
                addrVal.value.string = m.group(matchGroup);
                frameVal.tuple.add(addrVal);
            }

            // func="main.main"
            if (m.group(++matchGroup) != null) {
                final GdbMiResult funcVal = new GdbMiResult("func");
                funcVal.value.type = GdbMiValue.Type.String;
                funcVal.value.string = m.group(matchGroup);
                frameVal.tuple.add(funcVal);
            }

            if (hasArgs && m.group(++matchGroup) != null) {
                frameVal.tuple.add(parseArgsLine(m.group(matchGroup)));
            }

            if (m.group(++matchGroup) != null) {
                final GdbMiResult fileVal = new GdbMiResult("file");
                fileVal.value.type = GdbMiValue.Type.String;
                fileVal.value.string = m.group(matchGroup);
                frameVal.tuple.add(fileVal);
            }

            if (m.group(++matchGroup) != null) {
                final GdbMiResult fullnameVal = new GdbMiResult("fullname");
                fullnameVal.value.type = GdbMiValue.Type.String;
                fullnameVal.value.string = m.group(matchGroup);
                frameVal.tuple.add(fullnameVal);
            }

            // line="17"
            if (m.group(++matchGroup) != null) {
                final GdbMiResult lineVal = new GdbMiResult("line");
                lineVal.value.type = GdbMiValue.Type.String;
                lineVal.value.string = m.group(matchGroup);
                frameVal.tuple.add(lineVal);
            }

            subRes.value = frameVal;
            result.add(subRes);
        }

        return result;
    }

    @NotNull
    protected GdbMiResult parseArgsLine(final @NotNull String line) {
        // args=[{name="i",value="0x0"}]

        final GdbMiResult result = new GdbMiResult("args");
        result.value.type = GdbMiValue.Type.List;
        result.value.list = new GdbMiList();
        result.value.list.type = GdbMiList.Type.Values;
        result.value.list.values = new ArrayList<GdbMiValue>();

        final Pattern p = Pattern.compile(
            "(?:\\{(?:name=\"([^\"]+)\")," +
                "(?:value=\"(.*?)\")" +
                "\\})+"
        );
        final Matcher m = p.matcher(line);

        while (m.find()) {
            final GdbMiValue varVal = new GdbMiValue(GdbMiValue.Type.Tuple);
            varVal.tuple = new ArrayList<GdbMiResult>();

            final GdbMiResult varNameVal = new GdbMiResult("name");
            varNameVal.value.type = GdbMiValue.Type.String;
            varNameVal.value.string = m.group(1);
            varVal.tuple.add(varNameVal);


            final GdbMiResult valueVal = new GdbMiResult("value");
            valueVal.value.type = GdbMiValue.Type.String;
            valueVal.value.string = m.group(2);
            varVal.tuple.add(valueVal);

            result.value.list.values.add(varVal);
        }

        return result;
    }

    /**
     * Returns a list of unprocessed records. The caller should erase items from this list as they
     * are processed.
     *
     * @return A list of unprocessed records.
     */
    @NotNull
    public List<GdbMiRecord> getRecords() {
        return m_records;
    }

    /**
     * Processes the given data.
     *
     * @param data Data read from the GDB process.
     */
    public void process(@NotNull final byte[] data) {
        process(data, data.length);
    }

    /**
     * Processes the given data.
     *
     * @param data   Data read from the GDB process.
     * @param length Number of bytes from data to process.
     */
    public void process(@NotNull final byte[] data, final int length) {
        // Run the data through the lexer first
        final String[] buffer = convertOutput(data);

        for (final @NotNull String line : buffer) {
            if (line.isEmpty() ||
                line.matches("@\u0000*")) {
                continue;
            }

            final GdbMiRecord parsedLine = parseLine(line);
            if (parsedLine == null) {
                continue;
            }

            m_records.add(parsedLine);
        }
    }

    protected String[] convertOutput(@NotNull final byte[] data) {
        final String buff;

        try {
            buff = new String(data, "UTF-8");
        } catch (@NotNull final UnsupportedEncodingException ignored) {
            return new String[]{};
        }

        final String[] lines = buff.split("\n");
        final List<String> result = new ArrayList<String>();

        final List<Pattern> p = new ArrayList<Pattern>();
        p.add(Pattern.compile("(~\"\\[(?:.*?)\\]\\\\n\")$"));
        p.add(Pattern.compile("(=breakpoint\\-modified(?:.*))$"));
        p.add(Pattern.compile("(=thread\\-exited(?:.*))$"));
        p.add(Pattern.compile("(=thread\\-created(?:.*))$"));
        p.add(Pattern.compile("(\\*stopped(?:.*))$"));
        p.add(Pattern.compile("(\\*running(?:.*))$"));
        Matcher m;
        Boolean additionalLineFound = false;
        for (String line : lines) {
            if (isGdbMiLine(line)) {
                result.add(line);
                continue;
            }

            line = "@" + line;

            for (final Pattern aP : p) {
                m = aP.matcher(line);
                if (m.find()) {
                    result.add(line.replaceAll(aP.pattern(), ""));
                    result.add(m.group(1));
                    additionalLineFound = true;
                    break;
                }
            }

            if (!additionalLineFound) {
                result.add(line);
            }
        }

        return result.toArray(new String[result.size()]);
    }

    protected Boolean isGdbMiLine(final @NotNull String line) {
        if (line.length() == 0)
            return false;
        if (START_TOKENS.contains(line.substring(0, 1))) {
            return true;
        }

        if (line.matches("\\d+\\^.*")) {
            return true;
        }

        return line.startsWith("(gdb)");

    }

    protected void printUnhandledLine(final @NotNull String line) {
        if (rawConsole != null) {
            rawConsole.print("[[[ d.gdb.internal ]]] " + line + "\n", ConsoleViewContentType.ERROR_OUTPUT);
        }
    }

    @Nullable
    protected GdbMiRecord parseLine(@NotNull String line) {
        if (rawConsole != null) {
            rawConsole.print(line + "\n", ConsoleViewContentType.SYSTEM_OUTPUT);
        }


//        if (SystemInfo.isWindows) {
//            if (line.charAt(0) == '@') {
//                line = line.substring(1);
//            }
//        }

        GdbMiRecord result;
        if (line.matches("\\d+\\^.*\\r?\\n?")) {
            currentToken = Long.parseLong(line.substring(0, line.indexOf('^')), 10);

            result = new GdbMiResultRecord(GdbMiRecord.Type.Immediate, currentToken);
            result = parseImmediateLine(line, (GdbMiResultRecord) result);
            return result;
        }

        // Skip boring lines
        if (line.startsWith("(gdb)")) {
            return null;
        }

        switch (line.charAt(0)) {
            case '*':
                result = new GdbMiResultRecord(GdbMiRecord.Type.Exec, currentToken);
                result = parseExecLine(line, (GdbMiResultRecord) result);
                currentToken = null;
                break;

            case '+':
                result = new GdbMiStreamRecord(GdbMiRecord.Type.Log, currentToken);
                ((GdbMiStreamRecord) result).message = line.concat("\n");
                currentToken = null;
                break;

            case '=':
                result = new GdbMiResultRecord(GdbMiRecord.Type.Notify, currentToken);
                result = parseNotifyLine(line, (GdbMiResultRecord) result);
                currentToken = null;
                break;

            case '~':
                result = new GdbMiStreamRecord(GdbMiRecord.Type.Console, currentToken);
                line = line.substring(2, line.length() - 1)
                    .replace("\\n", "\n")
                    .replace("\\t", "    ")
                    .replace("\\\"", "\"")
                    .replaceAll("<http(.*)>", "http$1");

                ((GdbMiStreamRecord) result).message = line;
                currentToken = null;
                break;

            case '@':
                result = new GdbMiStreamRecord(GdbMiRecord.Type.Target, currentToken);
                ((GdbMiStreamRecord) result).message = line.substring(1).concat("\n");
                currentToken = null;
                break;

            case '&':
                result = new GdbMiStreamRecord(GdbMiRecord.Type.Log, currentToken);
                line = line.substring(2, line.length() - 1)
                    .replace("\\n", "\n")
                    .replace("\\\"", "\"")
                    .replaceAll("<http(.*)>", "http$1");

                ((GdbMiStreamRecord) result).message = line;
                currentToken = null;
                break;

            default:
                result = new GdbMiStreamRecord(GdbMiRecord.Type.Log, currentToken);
                ((GdbMiStreamRecord) result).message = line.concat("\n");
        }

        return result;
    }

    @NotNull
    protected GdbMiResultRecord parseNotifyLine(@NotNull String line, final @NotNull GdbMiResultRecord result) {
        result.className = line.substring(1, line.indexOf(','));

        line = line.substring(line.indexOf(',') + 1);
        if (line.startsWith("bkpt")) {
            result.addResult(parseBreakpointLine(line));
            return result;
        }

        final Pattern p = Pattern.compile("([a-z-]+)=(?:\"([^\"]+?)\")+");
        final Matcher m = p.matcher(line);
        while (m.find()) {
            final GdbMiResult subRes = new GdbMiResult(m.group(1));
            subRes.value = new GdbMiValue(GdbMiValue.Type.String);
            subRes.value.string = m.group(2).replace("\\\\t", "    ");
            result.addResult(subRes);
        }

        return result;
    }

    @NotNull
    protected GdbMiResultRecord parseExecLine(@NotNull String line, final @NotNull GdbMiResultRecord result) {
        if (line.indexOf(',') < 0) {
            result.className = line.substring(1);
            return result;
        }

        result.className = line.substring(1, line.indexOf(','));

        line = line.substring(line.indexOf(',') + 1);
        if (result.className.equals("stopped")) {
            if (line.startsWith("reason=\"breakpoint-hit\"")) {
                result.addResults(parseBreakpointHitLine(line));
            } else if (line.startsWith("reason=\"end-stepping-range\"")) {
                result.addResults(parseEndSteppingRangeLine(line));
            } else if (line.startsWith("reason=\"signal-received\"")) {
                result.addResults(parseSignalReceivedLine(line));
            } else if (line.startsWith("reason=\"function-finished\"")) {
                result.addResults(parseFunctionFinishedLine(line));
            } else if (line.startsWith("reason=\"location-reached\"")) {
                result.addResults(parseLocationReachedLine(line));
            } else if (line.startsWith("reason=\"exited\"")) {
                result.addResults(parseStoppedExitedLine(line));
            } else if (line.startsWith("reason=\"exited-normally\"")) {
                final GdbMiResult reasonVal = new GdbMiResult("reason");
                reasonVal.value = new GdbMiValue(GdbMiValue.Type.String);
                reasonVal.value.string = "exited-normally";
                result.addResult(reasonVal);
            } else if (line.startsWith("frame=")) {
                result.addResults(parseStoppedFrameLine(line));
            } else {
                printUnhandledLine(line);
            }

            return result;
        }

        if (result.className.equals("running")) {
            if (line.startsWith("thread-id")) {
                result.addResult(parseRunningThreadId(line));
            } else {
                printUnhandledLine(line);
            }

            return result;
        }

        printUnhandledLine(line);
        return result;
    }

    @NotNull
    protected GdbMiResultRecord parseImmediateLine(@NotNull String line, final @NotNull GdbMiResultRecord result) {
        if (line.indexOf(',') < 0) {
            result.className = line.substring(line.indexOf('^') + 1);
            return result;
        }

        result.className = line.substring(line.indexOf('^') + 1, line.indexOf(','));
        line = line.substring(line.indexOf(',') + 1);

        // Check for breakpoint
        if (line.startsWith("bkpt=")) {
            result.addResult(parseBreakpointLine(line));
            return result;
        } else if (line.startsWith("stack=")) {
            result.addResult(parseStackListLine(line));
            return result;
        } else if (line.startsWith("variables=")) {
            result.addResult(parseStackListVariablesLine(line));
            return result;
        } else if (line.startsWith("name=\"var")) {
            result.addResults(parseVarCreateLine(line));
            return result;
        } else if (line.startsWith("changelist=")) {
            result.addResult(parseChangelistLine(line));
            return result;
        } else if (line.startsWith("msg=")) {
            result.addResult(parseMsgLine(line));
            return result;
        } else if (line.startsWith("numchild=")) {
            result.addResults(parseNumChildLine(line));
            return result;
        } else if (line.startsWith("features=")) {
            result.addResult(parseFeaturesLine(line));
            return result;
        } else if (line.startsWith("value=")) {
            final GdbMiResult valueVal = new GdbMiResult("value");
            valueVal.value = new GdbMiValue(GdbMiValue.Type.String);
            valueVal.value.string = line.substring(7, line.length() - 1);
            result.addResult(valueVal);
            return result;
        } else if (line.startsWith("thread-ids=")) {
            result.addResults(parseThreadIdsLine(line));
            return result;
        } else if (line.startsWith("new-thread-id=")) {
            result.addResults(parseNewThreadIdLine(line));
            return result;
        } else if (line.startsWith("threads=")) {
            result.addResults(parseThreadsLine(line));
            return result;
        }

        printUnhandledLine(line);
        return result;
    }

    @NotNull
    protected GdbMiResult parseBreakpointLine(final @NotNull String line) {

        Pattern p = Pattern.compile("addr=\"<PENDING>\"");
        Matcher m = p.matcher(line);
        if (m.find()) {
            return parsePendingBreakpoint(line);
        }

        p = Pattern.compile("addr=\"<MULTIPLE>\"");
        m = p.matcher(line);
        if (m.find()) {
            return parseMultipleBreakpointLine(line);
        }

        final GdbMiResult subRes = new GdbMiResult("bkpt");
        final GdbMiValue bkptVal = new GdbMiValue(GdbMiValue.Type.Tuple);

        p = Pattern.compile("thread-groups=");
        m = p.matcher(line);
        final Boolean hasThreadGroups = m.find();

        String pattern = "(?:number=\"([^\"]+)\")," +
            "(?:type=\"([^\"]+)\")," +
            "(?:disp=\"([^\"]+)\")," +
            "(?:enabled=\"([^\"]+)\")," +
            "(?:addr=\"([^\"]+)\")?,?" +
            "(?:func=\"([^\"]+)\")?,?" +
            "(?:file=\"([^\"]+)\")," +
            "(?:fullname=\"([^\"]+)\")," +
            "(?:line=\"([^\"]+)\")";

        if (hasThreadGroups) {
            pattern += ",(?:thread-groups=\\[\"([^\"]+)\"\\])";
        }

        pattern += ",(?:times=\"(\\d+)\")," +
            "(?:original-location=\"([^\"]+)\")";

        p = Pattern.compile(pattern);
        m = p.matcher(line);

        if (!m.find()) {
            printUnhandledLine(line);
            return subRes;
        }

        Integer matchGroup = 0;

        // number="1"
        final GdbMiResult numVal = new GdbMiResult("number");
        numVal.value.type = GdbMiValue.Type.String;
        numVal.value.string = m.group(++matchGroup);
        bkptVal.tuple.add(numVal);

        // type="breakpoint"
        final GdbMiResult typeVal = new GdbMiResult("type");
        typeVal.value.type = GdbMiValue.Type.String;
        typeVal.value.string = m.group(++matchGroup);
        bkptVal.tuple.add(typeVal);

        // disp="keep"
        final GdbMiResult dispVal = new GdbMiResult("disp");
        dispVal.value.type = GdbMiValue.Type.String;
        dispVal.value.string = m.group(++matchGroup);
        bkptVal.tuple.add(dispVal);

        // enabled="y"
        final GdbMiResult enabledVal = new GdbMiResult("enabled");
        enabledVal.value.type = GdbMiValue.Type.String;
        enabledVal.value.string = m.group(++matchGroup);
        bkptVal.tuple.add(enabledVal);

        // addr="0x0000000000400c57"
        final GdbMiResult addrVal = new GdbMiResult("addr");
        addrVal.value.type = GdbMiValue.Type.String;
        addrVal.value.string = m.group(++matchGroup);
        bkptVal.tuple.add(addrVal);

        // func="main.main"
        final GdbMiResult funcVal = new GdbMiResult("func");
        funcVal.value.type = GdbMiValue.Type.String;
        funcVal.value.string = m.group(++matchGroup);
        bkptVal.tuple.add(funcVal);

        final GdbMiResult fileVal = new GdbMiResult("file");
        fileVal.value.type = GdbMiValue.Type.String;
        fileVal.value.string = m.group(++matchGroup);
        bkptVal.tuple.add(fileVal);

        final GdbMiResult fullnameVal = new GdbMiResult("fullname");
        fullnameVal.value.type = GdbMiValue.Type.String;
        fullnameVal.value.string = m.group(++matchGroup);
        bkptVal.tuple.add(fullnameVal);

        // line="17"
        final GdbMiResult lineVal = new GdbMiResult("line");
        lineVal.value.type = GdbMiValue.Type.String;
        lineVal.value.string = m.group(++matchGroup);
        bkptVal.tuple.add(lineVal);

        // thread-groups=["i1"]
        final GdbMiResult threadGroupVal = new GdbMiResult("thread-groups");
        threadGroupVal.value.type = GdbMiValue.Type.List;
        threadGroupVal.value.list = new GdbMiList();
        threadGroupVal.value.list.type = GdbMiList.Type.Values;
        threadGroupVal.value.list.values = new ArrayList<GdbMiValue>();

        if (hasThreadGroups) {
            final String[] threadGroupIds = m.group(++matchGroup).split(",");
            for (final String threadGroupId : threadGroupIds) {
                final GdbMiValue tgiVal = new GdbMiValue(GdbMiValue.Type.String);
                tgiVal.string = threadGroupId;
                threadGroupVal.value.list.values.add(tgiVal);
            }
        } else {
            final GdbMiValue tgiVal = new GdbMiValue(GdbMiValue.Type.String);
            tgiVal.string = "i1";
            threadGroupVal.value.list.values.add(tgiVal);
        }
        bkptVal.tuple.add(threadGroupVal);

        // times="0"
        final GdbMiResult timesVal = new GdbMiResult("times");
        timesVal.value.type = GdbMiValue.Type.String;
        timesVal.value.string = m.group(++matchGroup);
        bkptVal.tuple.add(timesVal);

        final GdbMiResult originalLocationVal = new GdbMiResult("original-location");
        originalLocationVal.value.type = GdbMiValue.Type.String;
        originalLocationVal.value.string = m.group(++matchGroup);
        bkptVal.tuple.add(originalLocationVal);

        subRes.value = bkptVal;
        return subRes;
    }

    @NotNull
    protected GdbMiResult parsePendingBreakpoint(final @NotNull String line) {
        final GdbMiResult subRes = new GdbMiResult("bkpt");
        final GdbMiValue bkptVal = new GdbMiValue(GdbMiValue.Type.Tuple);

        final String numberPattern = "(?:number=\"([^\"]+)\"),";
        final String typePattern = "(?:type=\"([^\"]+)\"),";
        final String dispPattern = "(?:disp=\"([^\"]+)\"),";
        final String enabledPattern = "(?:enabled=\"([^\"]+)\"),";
        final String addrPattern = "(?:addr=\"([^\"]+)\"),";
        final String pendingPattern = "(?:pending=\"([^\"]+)\"),";
        final String timesPattern = "(?:times=\"(\\d+)\"),";
        final String locationPattern = "(?:original-location=\"([^\"]+)\")";

        final Pattern p = Pattern.compile(numberPattern);
        Matcher m = p.matcher(line);
        if (!m.find()) {
            printUnhandledLine(line);
            return subRes;
        }
        // number="1"
        final GdbMiResult numVal = new GdbMiResult("number");
        numVal.value.type = GdbMiValue.Type.String;
        numVal.value.string = m.group(1);
        bkptVal.tuple.add(numVal);

        // type="breakpoint"
        try {
            m = Pattern.compile(typePattern).matcher(line);
            if (!m.find()) {
                printUnhandledLine(line);
                return subRes;
            }
            final GdbMiResult typeVal = new GdbMiResult("type");
            typeVal.value.type = GdbMiValue.Type.String;
            typeVal.value.string = m.group(1);
            bkptVal.tuple.add(typeVal);
        } catch (@NotNull final IllegalStateException ignored) {
            //we do not care whatsoever
        }

        // disp="keep"
        m = Pattern.compile(dispPattern).matcher(line);
        if (!m.find()) {
            printUnhandledLine(line);
            return subRes;
        }
        final GdbMiResult dispVal = new GdbMiResult("disp");
        dispVal.value.type = GdbMiValue.Type.String;
        dispVal.value.string = m.group(1);
        bkptVal.tuple.add(dispVal);

        // enabled="y"
        m = Pattern.compile(enabledPattern).matcher(line);
        if (!m.find()) {
            printUnhandledLine(line);
            return subRes;
        }
        final GdbMiResult enabledVal = new GdbMiResult("enabled");
        enabledVal.value.type = GdbMiValue.Type.String;
        enabledVal.value.string = m.group(1);
        bkptVal.tuple.add(enabledVal);

        // addr="0x0000000000400c57"
        m = Pattern.compile(addrPattern).matcher(line);
        if (!m.find()) {
            printUnhandledLine(line);
            return subRes;
        }
        final GdbMiResult addrVal = new GdbMiResult("addr");
        addrVal.value.type = GdbMiValue.Type.String;
        addrVal.value.string = m.group(1);
        bkptVal.tuple.add(addrVal);

        m = Pattern.compile(pendingPattern).matcher(line);
        if (!m.find()) {
            printUnhandledLine(line);
            return subRes;
        }
        final GdbMiResult pendingVal = new GdbMiResult("pending");
        pendingVal.value.type = GdbMiValue.Type.String;
        pendingVal.value.string = m.group(1);
        bkptVal.tuple.add(pendingVal);

        // times="0"
        m = Pattern.compile(timesPattern).matcher(line);
        if (!m.find()) {
            printUnhandledLine(line);
            return subRes;
        }
        final GdbMiResult timesVal = new GdbMiResult("times");
        timesVal.value.type = GdbMiValue.Type.String;
        timesVal.value.string = m.group(1);
        bkptVal.tuple.add(timesVal);

        m = Pattern.compile(locationPattern).matcher(line);
        if (!m.find()) {
            printUnhandledLine(line);
            return subRes;
        }
        final GdbMiResult originalLocationVal = new GdbMiResult("original-location");
        originalLocationVal.value.type = GdbMiValue.Type.String;
        originalLocationVal.value.string = m.group(1);
        bkptVal.tuple.add(originalLocationVal);

        subRes.value = bkptVal;
        return subRes;
    }

    @NotNull
    protected GdbMiResult parseMultipleBreakpointLine(@NotNull String line) {
        final GdbMiResult subRes = new GdbMiResult("bkpt");
        subRes.value.type = GdbMiValue.Type.List;
        subRes.value.list = new GdbMiList();
        subRes.value.list.type = GdbMiList.Type.Results;
        subRes.value.list.results = new ArrayList<GdbMiResult>();

        String pattern = "(?:number=\"(\\d+)\")," +
            "(?:type=\"([^\"]+)\")," +
            "(?:disp=\"([^\"]+)\")," +
            "(?:enabled=\"([^\"]+)\")," +
            "(?:addr=\"([^\"]+)\")," +
            "(?:times=\"(\\d+)\")," +
            "(?:original-location=\"([^\"]+)\")";

        Pattern p = Pattern.compile(pattern);
        Matcher m = p.matcher(line);

        if (!m.find()) {
            printUnhandledLine(line);
            return subRes;
        }

        Integer matchGroup = 0;

        GdbMiValue bkptVal = new GdbMiValue(GdbMiValue.Type.Tuple);

        // number="1"
        GdbMiResult numVal = new GdbMiResult("number");
        numVal.value.type = GdbMiValue.Type.String;
        numVal.value.string = m.group(++matchGroup);
        bkptVal.tuple.add(numVal);

        // type="breakpoint"
        GdbMiResult typeVal = new GdbMiResult("type");
        typeVal.value.type = GdbMiValue.Type.String;
        typeVal.value.string = m.group(++matchGroup);
        bkptVal.tuple.add(typeVal);

        // disp="keep"
        final GdbMiResult dispVal = new GdbMiResult("disp");
        dispVal.value.type = GdbMiValue.Type.String;
        dispVal.value.string = m.group(++matchGroup);
        bkptVal.tuple.add(dispVal);

        // enabled="y"
        GdbMiResult enabledVal = new GdbMiResult("enabled");
        enabledVal.value.type = GdbMiValue.Type.String;
        enabledVal.value.string = m.group(++matchGroup);
        bkptVal.tuple.add(enabledVal);

        // addr="0x0000000000400c57"
        GdbMiResult addrVal = new GdbMiResult("addr");
        addrVal.value.type = GdbMiValue.Type.String;
        addrVal.value.string = m.group(++matchGroup);
        bkptVal.tuple.add(addrVal);

        // times="0"
        final GdbMiResult timesVal = new GdbMiResult("times");
        timesVal.value.type = GdbMiValue.Type.String;
        timesVal.value.string = m.group(++matchGroup);
        bkptVal.tuple.add(timesVal);

        final GdbMiResult originalLocationVal = new GdbMiResult("original-location");
        originalLocationVal.value.type = GdbMiValue.Type.String;
        originalLocationVal.value.string = m.group(++matchGroup);
        bkptVal.tuple.add(originalLocationVal);

        GdbMiResult smallSubRes = new GdbMiResult("bkpt");
        smallSubRes.value = bkptVal;
        subRes.value.list.results.add(smallSubRes);

        // bkpt={
        //      number="5.1",
        //      enabled="y",
        //      addr="0x0000000000400f2a",
        //      func="main.main",
        //      line="44",
        //      thread-groups=["i1"]
        // }

        line = line.substring(line.indexOf("},{") + 2);

        p = Pattern.compile("thread-groups=");
        m = p.matcher(line);
        final Boolean hasThreadGroups = m.find();

        pattern = "\\{(?:number=\"([^\"]+)\")," +
            "(?:enabled=\"([^\"]+)\")," +
            "(?:addr=\"([^\"]+)\")," +
            "(?:func=\"([^\"]+)\")," +
            "(?:file=\"([^\"]+)\")," +
            "(?:fullname=\"([^\"]+)\")," +
            "(?:line=\"([^\"]+)\")";

        if (hasThreadGroups) {
            pattern += ",(?:thread-groups=\\[\"([^\"]+)\"\\])";
        }

        pattern += "\\}";

        p = Pattern.compile(pattern);
        m = p.matcher(line);

        if (!m.find()) {
            printUnhandledLine(line);
            return subRes;
        }

        m.reset();
        while (m.find()) {
            matchGroup = 0;
            bkptVal = new GdbMiValue(GdbMiValue.Type.Tuple);

            // number="1"
            numVal = new GdbMiResult("number");
            numVal.value.type = GdbMiValue.Type.String;
            numVal.value.string = m.group(++matchGroup);
            bkptVal.tuple.add(numVal);

            // type="breakpoint"
            typeVal = new GdbMiResult("type");
            typeVal.value.type = GdbMiValue.Type.String;
            typeVal.value.string = "breakpoint";
            bkptVal.tuple.add(typeVal);

            // enabled="y"
            enabledVal = new GdbMiResult("enabled");
            enabledVal.value.type = GdbMiValue.Type.String;
            enabledVal.value.string = m.group(++matchGroup);
            bkptVal.tuple.add(enabledVal);

            // addr="0x0000000000400c57"
            addrVal = new GdbMiResult("addr");
            addrVal.value.type = GdbMiValue.Type.String;
            addrVal.value.string = m.group(++matchGroup);
            bkptVal.tuple.add(addrVal);

            // func="main.main"
            final GdbMiResult funcVal = new GdbMiResult("func");
            funcVal.value.type = GdbMiValue.Type.String;
            funcVal.value.string = m.group(++matchGroup);
            bkptVal.tuple.add(funcVal);

            final GdbMiResult fileVal = new GdbMiResult("file");
            fileVal.value.type = GdbMiValue.Type.String;
            fileVal.value.string = m.group(++matchGroup);
            bkptVal.tuple.add(fileVal);

            final GdbMiResult fullnameVal = new GdbMiResult("fullname");
            fullnameVal.value.type = GdbMiValue.Type.String;
            fullnameVal.value.string = m.group(++matchGroup);
            bkptVal.tuple.add(fullnameVal);

            // line="17"
            final GdbMiResult lineVal = new GdbMiResult("line");
            lineVal.value.type = GdbMiValue.Type.String;
            lineVal.value.string = m.group(++matchGroup);
            bkptVal.tuple.add(lineVal);

            // thread-groups=["i1"]
            final GdbMiResult threadGroupVal = new GdbMiResult("thread-groups");
            threadGroupVal.value.type = GdbMiValue.Type.List;
            threadGroupVal.value.list = new GdbMiList();
            threadGroupVal.value.list.type = GdbMiList.Type.Values;
            threadGroupVal.value.list.values = new ArrayList<GdbMiValue>();

            if (hasThreadGroups) {
                final String[] threadGroupIds = m.group(++matchGroup).split(",");
                for (final String threadGroupId : threadGroupIds) {
                    final GdbMiValue tgiVal = new GdbMiValue(GdbMiValue.Type.String);
                    tgiVal.string = threadGroupId;
                    threadGroupVal.value.list.values.add(tgiVal);
                }
            } else {
                final GdbMiValue tgiVal = new GdbMiValue(GdbMiValue.Type.String);
                tgiVal.string = "i1";
                threadGroupVal.value.list.values.add(tgiVal);
            }
            bkptVal.tuple.add(threadGroupVal);

            smallSubRes = new GdbMiResult("bkpt");
            smallSubRes.value = bkptVal;
            subRes.value.list.results.add(smallSubRes);
        }

        return subRes;
    }

    @NotNull
    protected Collection<GdbMiResult> parseBreakpointHitLine(final @NotNull String line) {
        final Collection<GdbMiResult> result = new ArrayList<GdbMiResult>();

        Pattern p = Pattern.compile("(?:core=\"(\\d+)\")");
        Matcher m = p.matcher(line);
        final Boolean hasCore = m.find();

        p = Pattern.compile("(?:bkptno=\"(\\d+)\")");
        m = p.matcher(line);
        final boolean hasBkptno = m.find();

        p = Pattern.compile("(?:disp=\"([^\"]+)\"),");
        m = p.matcher(line);
        final boolean hasDisp = m.find();

        String pattern = "(?:reason=\"([^\"]+)\")," +
            (hasDisp ? "(?:disp=\"([^\"]+)\")," : "") +
            (hasBkptno ? "(?:bkptno=\"(\\d+)\")," : "") +
            "(?:frame=\\{([^\\}].+)\\})," +
            "(?:thread-id=\"([^\"]+)\")," +
            "(?:stopped-threads=\"([^\"]+)\")";

        if (hasCore) {
            pattern += ",(?:core=\"(\\d+)\")";
        }

        p = Pattern.compile(pattern);
        m = p.matcher(line);

        if (!m.find()) {
            printUnhandledLine(line);
            return result;
        }

        int currentGroup = 1;

        // reason="breakpoint-hit"
        final GdbMiResult reasonVal = new GdbMiResult("reason");
        reasonVal.value.type = GdbMiValue.Type.String;
        reasonVal.value.string = m.group(currentGroup);
        result.add(reasonVal);
        currentGroup++;

        // disp="keep"
        if (hasDisp) {
            final GdbMiResult dispVal = new GdbMiResult("disp");
            dispVal.value.type = GdbMiValue.Type.String;
            dispVal.value.string = m.group(currentGroup);
            result.add(dispVal);
            currentGroup++;
        }

        // bkptno="1"
        if (hasBkptno) {
            final GdbMiResult bkptNoVal = new GdbMiResult("bkptno");
            bkptNoVal.value.type = GdbMiValue.Type.String;
            bkptNoVal.value.string = m.group(currentGroup);
            result.add(bkptNoVal);
            currentGroup++;
        }

        // frame={*}
        result.add(parseBreakpointHitLineFrameLine(m.group(currentGroup)));
        currentGroup++;

        // thread-id="1"
        final GdbMiResult threadIdVal = new GdbMiResult("thread-id");
        threadIdVal.value.type = GdbMiValue.Type.String;
        threadIdVal.value.string = m.group(currentGroup);
        result.add(threadIdVal);
        currentGroup++;

        // stopped-threads="all"
        final GdbMiResult stoppedThreadsVal = new GdbMiResult("stopped-threads");
        stoppedThreadsVal.value.type = GdbMiValue.Type.String;
        stoppedThreadsVal.value.string = m.group(currentGroup);
        result.add(stoppedThreadsVal);
        currentGroup++;

        // core="6"
        final GdbMiResult coreVal = new GdbMiResult("core");
        coreVal.value.type = GdbMiValue.Type.String;
        if (hasCore) {
            coreVal.value.string = m.group(currentGroup);
        } else {
            coreVal.value.string = "1";
        }
        result.add(coreVal);

        return result;
    }

    @NotNull
    protected Collection<GdbMiResult> parseVarCreateLine(final @NotNull String line) {
        final Collection<GdbMiResult> result = new ArrayList<GdbMiResult>();

        Pattern p = Pattern.compile("(?:thread-id=\"([^\"]+)\"),");
        Matcher m = p.matcher(line);
        final Boolean hasThreadId = m.find();

        String pattern;
//        if (SystemInfo.isWindows) {
//            pattern =
//                "(?:name=\"([^\"]+)\")," +
//                    "(?:type=\"([^\"]+)\")," +
//                    "(?:value=\"(.*?)\")," +
//                    "(?:numchild=\"([^\"]+)\")";
//        } else {
            pattern = "(?:name=\"([^\"]+)\")," +
                "(?:numchild=\"([^\"]+)\")," +
                "(?:value=\"(.*?)\")," +
                "(?:type=\"([^\"]+)\"),";
//        }

        if (hasThreadId) {
            pattern += "(?:thread-id=\"([^\"]+)\"),";
        }

//        if (!SystemInfo.isWindows) {
            pattern += "(?:has_more=\"([^\"]+)\")";
//        }

        p = Pattern.compile(pattern);
        m = p.matcher(line);

        if (!m.find()) {
            printUnhandledLine(line);
            return result;
        }

        Integer matchGroup = 0;

        // name="var1"
        final GdbMiResult nameVal = new GdbMiResult("name");
        nameVal.value.type = GdbMiValue.Type.String;
        nameVal.value.string = m.group(++matchGroup);
        result.add(nameVal);

        // numchild="0"
        final GdbMiResult numChildVal = new GdbMiResult("numchild");
        numChildVal.value.type = GdbMiValue.Type.String;
        numChildVal.value.string = m.group(++matchGroup);
        result.add(numChildVal);

        // value="false"
        final GdbMiResult valueVal = new GdbMiResult("value");
        valueVal.value.type = GdbMiValue.Type.String;
        valueVal.value.string = m.group(++matchGroup);
        result.add(valueVal);

        // type="bool"
        final GdbMiResult typeVal = new GdbMiResult("type");
        typeVal.value.type = GdbMiValue.Type.String;
        typeVal.value.string = m.group(++matchGroup);
        result.add(typeVal);

        if (hasThreadId) {
            // thread-id="1"
            final GdbMiResult threadIdVal = new GdbMiResult("thread-id");
            threadIdVal.value.type = GdbMiValue.Type.String;
            threadIdVal.value.string = m.group(++matchGroup);
            result.add(threadIdVal);
        }

        // has_more="0"
//        if (!SystemInfo.isWindows) {
            final GdbMiResult hasMoreVal = new GdbMiResult("has_more");
            hasMoreVal.value.type = GdbMiValue.Type.String;
            hasMoreVal.value.string = m.group(++matchGroup);
            result.add(hasMoreVal);
//        }

        return result;
    }

    @NotNull
    protected Collection<GdbMiResult> parseEndSteppingRangeLine(final @NotNull String line) {
        final Collection<GdbMiResult> result = new ArrayList<GdbMiResult>();

        Pattern p = Pattern.compile("(?:core=\"(\\d+)\")");
        Matcher m = p.matcher(line);
        final Boolean hasCore = m.find();
        String pattern = "(?:reason=\"([^\"]+)\")," +
            "(?:frame=\\{([^\\}].+)\\})," +
            "(?:thread-id=\"([^\"]+)\")," +
            "(?:stopped-threads=\"([^\"]+)\")";

        if (hasCore) {
            pattern += ",(?:core=\"(\\d+)\")";
        }

        p = Pattern.compile(pattern);
        m = p.matcher(line);

        if (!m.find()) {
            printUnhandledLine(line);
            return result;
        }

        // reason="end-stepping-range"
        final GdbMiResult reasonVal = new GdbMiResult("reason");
        reasonVal.value.type = GdbMiValue.Type.String;
        reasonVal.value.string = m.group(1);
        result.add(reasonVal);

        // frame={*}
        result.add(parseBreakpointHitLineFrameLine(m.group(2)));

        // thread-id="1"
        final GdbMiResult threadIdVal = new GdbMiResult("thread-id");
        threadIdVal.value.type = GdbMiValue.Type.String;
        threadIdVal.value.string = m.group(3);
        result.add(threadIdVal);

        // stopped-threads="all"
        final GdbMiResult stoppedThreadsVal = new GdbMiResult("stopped-threads");
        stoppedThreadsVal.value.type = GdbMiValue.Type.String;
        stoppedThreadsVal.value.string = m.group(4);
        result.add(stoppedThreadsVal);

        // core="6"
        final GdbMiResult coreVal = new GdbMiResult("core");
        coreVal.value.type = GdbMiValue.Type.String;
        if (hasCore) {
            coreVal.value.string = m.group(5);
        } else {
            coreVal.value.string = "1";
        }
        result.add(coreVal);

        return result;
    }

    @NotNull
    protected Collection<GdbMiResult> parseNumChildLine(final @NotNull String line) {
        final Collection<GdbMiResult> result = new ArrayList<GdbMiResult>();

        final Pattern p = Pattern.compile(
            "(?:numchild=\"([^\"]+)\")," +
                "(?:children=\\[((?!\\],has_more).+?)\\])," +
                "(?:has_more=\"([^\"]+)\")"
        );
        final Matcher m = p.matcher(line);

        // numchild="2"
        if (!m.find()) {
            printUnhandledLine(line);
            return result;
        }

        final GdbMiResult numChildVal = new GdbMiResult("numchild");
        numChildVal.value.type = GdbMiValue.Type.String;
        numChildVal.value.string = m.group(1);
        result.add(numChildVal);

        result.add(parseNumChildChildsLine(m.group(2)));

        // has_more="0"
        final GdbMiResult hasMoreVal = new GdbMiResult("has_more");
        hasMoreVal.value.type = GdbMiValue.Type.String;
        hasMoreVal.value.string = m.group(3);
        result.add(hasMoreVal);

        return result;
    }

    @NotNull
    protected Collection<GdbMiResult> parseSignalReceivedLine(final @NotNull String line) {
        final Collection<GdbMiResult> result = new ArrayList<GdbMiResult>();

        final Pattern p = Pattern.compile(
            "(?:reason=\"([^\"]+)\")," +
                "(?:signal-name=\"([^\"]+)\")," +
                "(?:signal-meaning=\"([^\"]+)\")," +
                "(?:frame=\\{([^\\}].+?)\\})," +
                "(?:thread-id=\"([^\"]+)\")," +
                "(?:stopped-threads=\"([^\"]+)\")," +
                "(?:core=\"(\\d+)\")"
        );
        final Matcher m = p.matcher(line);

        if (!m.find()) {
            printUnhandledLine(line);
            return result;
        }

        // reason="signal-received",
        final GdbMiResult reasonVal = new GdbMiResult("reason");
        reasonVal.value.type = GdbMiValue.Type.String;
        reasonVal.value.string = m.group(1);
        result.add(reasonVal);

        // signal-name="SIGSEGV",
        final GdbMiResult signalNameVal = new GdbMiResult("signal-name");
        signalNameVal.value.type = GdbMiValue.Type.String;
        signalNameVal.value.string = m.group(2);
        result.add(signalNameVal);

        // signal-meaning="Segmentation fault",
        final GdbMiResult signalMeaningVal = new GdbMiResult("signal-meaning");
        signalMeaningVal.value.type = GdbMiValue.Type.String;
        signalMeaningVal.value.string = m.group(3);
        result.add(signalMeaningVal);

        // frame={*}
        result.add(parseBreakpointHitLineFrameLine(m.group(4)));

        // thread-id="1",
        final GdbMiResult threadIdVal = new GdbMiResult("thread-id");
        threadIdVal.value.type = GdbMiValue.Type.String;
        threadIdVal.value.string = m.group(5);
        result.add(threadIdVal);

        // stopped-threads="all",
        final GdbMiResult stoppedThreadsVal = new GdbMiResult("stopped-threads");
        stoppedThreadsVal.value.type = GdbMiValue.Type.String;
        stoppedThreadsVal.value.string = m.group(6);
        result.add(stoppedThreadsVal);

        // core="1"
        final GdbMiResult coreVal = new GdbMiResult("core");
        coreVal.value.type = GdbMiValue.Type.String;
        coreVal.value.string = m.group(7);
        result.add(coreVal);

        return result;
    }

    @NotNull
    protected Collection<GdbMiResult> parseFunctionFinishedLine(final @NotNull String line) {
        return parseEndSteppingRangeLine(line);
    }

    @NotNull
    protected Collection<GdbMiResult> parseLocationReachedLine(final @NotNull String line) {
        return parseEndSteppingRangeLine(line);
    }

    @NotNull
    protected Collection<GdbMiResult> parseStoppedFrameLine(final @NotNull String line) {
        final Collection<GdbMiResult> result = new ArrayList<GdbMiResult>();

        final Pattern p = Pattern.compile(
            "(?:frame=\\{([^\\}].+?)\\})," +
                "(?:thread-id=\"([^\"]+)\")," +
                "(?:stopped-threads=\"([^\"]+)\")," +
                "(?:core=\"(\\d+)\")"
        );
        final Matcher m = p.matcher(line);

        if (!m.find()) {
            printUnhandledLine(line);
            return result;
        }

        // frame={*}
        result.add(parseBreakpointHitLineFrameLine(m.group(1)));

        // thread-id="1",
        final GdbMiResult threadIdVal = new GdbMiResult("thread-id");
        threadIdVal.value.type = GdbMiValue.Type.String;
        threadIdVal.value.string = m.group(2);
        result.add(threadIdVal);

        // stopped-threads="all",
        final GdbMiResult stoppedThreadsVal = new GdbMiResult("stopped-threads");
        stoppedThreadsVal.value.type = GdbMiValue.Type.String;
        stoppedThreadsVal.value.string = m.group(3);
        result.add(stoppedThreadsVal);

        // core="1"
        final GdbMiResult coreVal = new GdbMiResult("core");
        coreVal.value.type = GdbMiValue.Type.String;
        coreVal.value.string = m.group(4);
        result.add(coreVal);

        return result;
    }

    @NotNull
    protected Collection<GdbMiResult> parseStoppedExitedLine(final @NotNull String line) {
        final Collection<GdbMiResult> result = new ArrayList<GdbMiResult>();

        final Pattern p = Pattern.compile(
            "(?:reason=\"([^\"]+)\")," +
                "(?:exit-code=\"([^\"]+)\")"
        );
        final Matcher m = p.matcher(line);

        if (!m.find()) {
            printUnhandledLine(line);
            return result;
        }

        // reason="exited",
        final GdbMiResult reasonVal = new GdbMiResult("reason");
        reasonVal.value.type = GdbMiValue.Type.String;
        reasonVal.value.string = m.group(1);
        result.add(reasonVal);

        // exit-code="02",
        final GdbMiResult exitCodeVal = new GdbMiResult("exit-code");
        exitCodeVal.value.type = GdbMiValue.Type.String;
        exitCodeVal.value.string = m.group(2);
        result.add(exitCodeVal);

        return result;
    }

    @NotNull
    protected GdbMiResult parseFeaturesLine(final @NotNull String line) {
        final GdbMiResult result = new GdbMiResult("features");
        result.value.type = GdbMiValue.Type.List;
        result.value.list = new GdbMiList();
        result.value.list.type = GdbMiList.Type.Values;
        result.value.list.values = new ArrayList<GdbMiValue>();

        // features=["frozen-varobjs","pending-breakpoints","thread-info","data-read-memory-bytes","breakpoint-notifications","ada-task-info","python"]
        final Pattern p = Pattern.compile("(?:\"([^\"]+)\")");
        final Matcher m = p.matcher(line);

        while (m.find()) {
            final GdbMiValue varVal = new GdbMiValue(GdbMiValue.Type.String);
            varVal.string = m.group(1);
            result.value.list.values.add(varVal);
        }

        return result;
    }

    @NotNull
    protected Collection<GdbMiResult> parseThreadIdsLine(final @NotNull String line) {
        //thread-ids={thread-id="4",thread-id="3",thread-id="2",thread-id="1"},current-thread-id="1",number-of-threads="4"
        final Collection<GdbMiResult> result = new ArrayList<GdbMiResult>();

        Pattern p = Pattern.compile("thread-id=\"(\\d+)\"");
        Matcher m = p.matcher(line);

        final GdbMiResult threadIds = new GdbMiResult("thread-ids");
        threadIds.value.type = GdbMiValue.Type.Tuple;
        threadIds.value.tuple = new ArrayList<GdbMiResult>();

        while (m.find()) {
            final GdbMiResult threadId = new GdbMiResult("thread-id");
            threadId.value.type = GdbMiValue.Type.String;
            threadId.value.string = m.group(1);
            threadIds.value.tuple.add(threadId);
        }
        result.add(threadIds);

        p = Pattern.compile("current-thread-id=\"(\\d+)\",number-of-threads=\"(\\d+)\"");
        m = p.matcher(line);

        if (m.find()) {
            final GdbMiResult currentThreadId = new GdbMiResult("current-thread-id");
            currentThreadId.value.type = GdbMiValue.Type.String;
            currentThreadId.value.string = m.group(1);
            result.add(currentThreadId);

            final GdbMiResult numberOfThreads = new GdbMiResult("number-of-threads");
            numberOfThreads.value.type = GdbMiValue.Type.String;
            numberOfThreads.value.string = m.group(2);
            result.add(numberOfThreads);
        }

        return result;
    }

    @NotNull
    protected Collection<GdbMiResult> parseNewThreadIdLine(final @NotNull String line) {
        // new-thread-id="4",frame={level="0",addr="0x00007ffff7bc3cd0",func="__GI___nptl_create_event",args=[],file="events.c",fullname="/build/buildd/eglibc-2.17/nptl/events.c",line="25"}
        final Collection<GdbMiResult> result = new ArrayList<GdbMiResult>();

        final Pattern p = Pattern.compile("new-thread-id=\"(\\d+)\",(?:frame=\\{([^\\}].+)\\})");
        final Matcher m = p.matcher(line);

        if (!m.find()) {
            printUnhandledLine(line);
            return result;
        }

        final GdbMiResult newThreadId = new GdbMiResult("new-thread-id");
        newThreadId.value.type = GdbMiValue.Type.String;
        newThreadId.value.string = m.group(1);
        result.add(newThreadId);

        // frame={*}
        result.add(parseBreakpointHitLineFrameLine(m.group(2)));

        return result;
    }

    @NotNull
    protected Collection<GdbMiResult> parseThreadsLine(final @NotNull String line) {
        final Collection<GdbMiResult> result = new ArrayList<GdbMiResult>();

        if (line.equals("threads=[]")) {
            return result;
        }

        Pattern p = Pattern.compile(
            "\\{(?:id=\"(\\d+)\")?,?" +
                "(?:target-id=\"([^\"]+)\")?,?" +
                "(?:name=\"([^\"]+)\")?,?" +
                "(?:frame=\\{([^\\}].+?)\\})?,?" +
                "(?:state=\"([^\"]+)\")?,?" +
                "(?:core=\"(\\d+)\")?" +
                "\\}"
        );
        Matcher m = p.matcher(line);

        if (!m.find()) {
            printUnhandledLine(line);
            return result;
        }

        final GdbMiResult threads = new GdbMiResult("threads");
        threads.value.type = GdbMiValue.Type.List;
        threads.value.list = new GdbMiList();
        threads.value.list.type = GdbMiList.Type.Results;
        threads.value.list.results = new ArrayList<GdbMiResult>();

        m.reset();
        while (m.find()) {
            final GdbMiResult thread = new GdbMiResult("thread");
            final GdbMiValue threadVal = new GdbMiValue(GdbMiValue.Type.Tuple);

            // id="4"
            final GdbMiResult idVal = new GdbMiResult("id");
            idVal.value.type = GdbMiValue.Type.String;
            idVal.value.string = m.group(1);
            threadVal.tuple.add(idVal);

            // target-id="Thread 0x7fffe67e7700 (LWP 25320)"
            final GdbMiResult targetId = new GdbMiResult("target-id");
            targetId.value.type = GdbMiValue.Type.String;
            targetId.value.string = m.group(2);
            threadVal.tuple.add(targetId);

            // name="seraph"
            final GdbMiResult nameVal = new GdbMiResult("name");
            nameVal.value.type = GdbMiValue.Type.String;
            nameVal.value.string = m.group(3);
            threadVal.tuple.add(nameVal);

            // frame={*}
            threadVal.tuple.add(parseBreakpointHitLineFrameLine(m.group(4)));

            // state="stopped"
            final GdbMiResult stateVal = new GdbMiResult("state");
            stateVal.value.type = GdbMiValue.Type.String;
            stateVal.value.string = m.group(5);
            threadVal.tuple.add(stateVal);

            // core="7"
            final GdbMiResult coreVal = new GdbMiResult("core");
            coreVal.value.type = GdbMiValue.Type.String;
            coreVal.value.string = m.group(6);
            threadVal.tuple.add(coreVal);

            thread.value = threadVal;
            threads.value.list.results.add(thread);
        }

        result.add(threads);

        p = Pattern.compile("current-thread-id=\"(\\d+)\"");
        m = p.matcher(line);

        if (m.find()) {
            final GdbMiResult currentThreadId = new GdbMiResult("current-thread-id");
            currentThreadId.value.type = GdbMiValue.Type.String;
            currentThreadId.value.string = m.group(1);
            result.add(currentThreadId);
        }

        return result;
    }

}
