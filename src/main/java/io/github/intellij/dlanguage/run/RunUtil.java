package io.github.intellij.dlanguage.run;

import com.intellij.execution.ExecutionException;
import com.intellij.execution.ExecutionResult;
import com.intellij.execution.Executor;
import com.intellij.execution.configurations.RunProfileState;
import com.intellij.execution.runners.DefaultProgramRunner;
import com.intellij.execution.runners.ExecutionEnvironment;
import com.intellij.execution.ui.RunContentDescriptor;
import com.intellij.openapi.project.Project;
import com.intellij.xdebugger.XDebugProcess;
import com.intellij.xdebugger.XDebugProcessStarter;
import com.intellij.xdebugger.XDebugSession;
import com.intellij.xdebugger.XDebuggerManager;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import uk.co.cwspencer.gdb.Gdb;
import uk.co.cwspencer.gdb.messages.GdbEvent;
import uk.co.cwspencer.ideagdb.debug.GdbDebugProcess;
import uk.co.cwspencer.ideagdb.debug.utils.SdkUtil;

public class RunUtil {
    @Nullable
    static RunContentDescriptor startDebugger(final DefaultProgramRunner buildRunner, final RunProfileState state, final ExecutionEnvironment env, final Project project, final Executor executor, String execName) throws ExecutionException {
        final ExecutionResult result = state.execute(executor, buildRunner);
        if (result == null) {
            return null;
        }

//        GdbRunConfiguration configuration = ((GdbExecutionResult) result).m_configuration;


        if (SdkUtil.isHostOsWindows()) {
            execName = execName.concat(".exe");
        }

        final XDebugSession debugSession = XDebuggerManager.getInstance(project).startSession(env,
            new XDebugProcessStarter() {
                @NotNull
                @Override
                public XDebugProcess start(@NotNull final XDebugSession session) throws ExecutionException {
                    return new GdbDebugProcess(project, session, result);
                }
            });

        final GdbDebugProcess debugProcess = ((GdbDebugProcess) debugSession.getDebugProcess());

        final Gdb gdbProcess = debugProcess.m_gdb;

        // Queue startup commands
        gdbProcess.sendCommand("-list-features", new Gdb.GdbEventCallback() {
            @Override
            public void onGdbCommandCompleted(final GdbEvent event) {
                gdbProcess.onGdbCapabilitiesReady(event);
            }
        });

        gdbProcess.sendCommand("-file-exec-and-symbols " + execName);

        // Send startup commands
        final String[] commandsArray = new String[0];//configuration.STARTUP_COMMANDS.split("\\r?\\n");
        for (String command : commandsArray) {
            command = command.trim();
            if (!command.isEmpty()) {
                gdbProcess.sendCommand(command);
            }
        }

//        if (configuration.autoStartGdb) {
        gdbProcess.sendCommand("run");
//        }

        return debugSession.getRunContentDescriptor();
    }
}
