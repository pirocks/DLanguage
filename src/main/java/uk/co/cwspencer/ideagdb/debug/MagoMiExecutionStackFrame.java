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

import com.intellij.openapi.diagnostic.Logger;
import com.intellij.xdebugger.frame.XCompositeNode;
import com.intellij.xdebugger.frame.XValueChildrenList;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import uk.co.cwspencer.gdb.Gdb;
import uk.co.cwspencer.gdb.messages.GdbEvent;
import uk.co.cwspencer.gdb.messages.GdbStackFrame;
import uk.co.cwspencer.gdb.messages.GdbVariableObject;
import uk.co.cwspencer.gdb.messages.GdbVariableObjects;


/**
 * Created by francis on 10/1/2017.
 */
public class MagoMiExecutionStackFrame extends GdbExecutionStackFrame {
    private final Logger m_log = Logger.getInstance("uk.co.cwspencer.ideagdb.debug.MagoMiExecutionStackFrame");

    public MagoMiExecutionStackFrame(final Gdb gdb, final Integer id, final GdbStackFrame frame) {
        super(gdb, id, frame);
    }

    @Nullable
    @Override
    public Object getEqualityObject() {
        return getClass();
    }

    @Override
    protected void onGdbVariablesReady(final GdbEvent event, @NotNull final XCompositeNode node) {
        if (gdbVariablesReadyParamsCheck(event, node)) return;

        // Inspect the data
        final GdbVariableObjects variables = (GdbVariableObjects) event;
        if (variables.objects == null || variables.objects.isEmpty()) {
            // No data
            node.addChildren(XValueChildrenList.EMPTY, true);
        }

        // Build a XValueChildrenList
        final XValueChildrenList children = new XValueChildrenList(variables.objects.size());
        for (final GdbVariableObject variable : variables.objects) {
            children.add(variable.expression, new GdbValue(m_gdb, variable));
        }
        node.addChildren(children, true);
    }

}
