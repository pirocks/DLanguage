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

package uk.co.cwspencer.ideagdb.debug;

import com.intellij.util.SystemIndependent;
import org.jetbrains.annotations.NotNull;
import uk.co.cwspencer.gdb.Gdb;
import uk.co.cwspencer.gdb.messages.GdbErrorEvent;
import uk.co.cwspencer.gdb.messages.GdbEvent;
import uk.co.cwspencer.gdb.messages.GdbVariableObject;
import uk.co.cwspencer.gdb.messages.GdbVariables;

import java.util.HashSet;

/**
 * Created by francis on 9/24/2017.
 */
public class MagoMi extends Gdb {
    public MagoMi(final String gdbDebuggerPath, @SystemIndependent final String basePath, final GdbDebugProcess gdbDebugProcess) {
        super(gdbDebuggerPath, basePath, gdbDebugProcess);
    }

    @Override
    protected void onGdbVariablesReady(final GdbEvent event, final int thread, final int frame, @NotNull final GdbEventCallback callback) {
        if (event instanceof GdbErrorEvent) {
            callback.onGdbCommandCompleted(event);
            return;
        }
        if (!(event instanceof GdbVariables)) {
            final GdbErrorEvent errorEvent = new GdbErrorEvent();
            errorEvent.message = "Unexpected data received from GDB";
            callback.onGdbCommandCompleted(errorEvent);
            m_log.warn("Unexpected event " + event + " received from -stack-list-variables " +
                "request");
            return;
        }

        // Create variable objects for each of the variables if we haven't done so already
        final GdbVariables variables = (GdbVariables) event;
        for (final String variable : variables.variables.keySet()) {
            final GdbVariableObject variableObject = m_variableObjectsByExpression.get(variable);
            if (variableObject == null) {
                final String command = "-var-create --thread " + thread + " --frame " + frame + " - @ " +
                    formatVarName(variable);
                sendCommand(command, new GdbEventCallback() {
                    @Override
                    public void onGdbCommandCompleted(final GdbEvent event) {
                        onGdbNewVariableObjectReady(event, variable, callback);
                        sendCommand("-var-update --thread " + thread + " --frame " + frame + " - " + ((GdbVariableObject) event).name,
                            new GdbEventCallback() {
                                @Override
                                public void onGdbCommandCompleted(final GdbEvent event) {
                                    onGdbVariableObjectsUpdated(event, variables.variables.keySet(), callback);
                                }
                            });
                    }
                });
            }
        }

    }

    @Override
    public void evaluateExpression(final int thread, final int frame, final String expression, @NotNull final GdbEventCallback callback) {
        // TODO: Make this more efficient

        // Create a new variable object if necessary
        final GdbVariableObject variableObject = m_variableObjectsByExpression.get(expression);
        if (variableObject == null) {
            final String command = "-var-create --thread " + thread + " --frame " + frame + " - @ " +
                formatVarName(expression);
            sendCommand(command, new GdbEventCallback() {
                @Override
                public void onGdbCommandCompleted(final GdbEvent event) {
                    onGdbNewVariableObjectReady(event, expression, callback);
                    sendCommand("-var-update --thread " + thread + " --frame " + frame + " - " + ((GdbVariableObject) event).name,
                        new GdbEventCallback() {
                            @Override
                            public void onGdbCommandCompleted(final GdbEvent event) {
                                final HashSet<String> expressions = new HashSet<String>();
                                expressions.add(expression);
                                onGdbVariableObjectsUpdated(event, expressions, callback);
                            }
                        });
                }
            });
        }

        // Update existing variable objects


    }

}
