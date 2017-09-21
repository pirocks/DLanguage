package io.github.intellij.dlanguage.run;

import com.intellij.execution.ExecutionException;
import com.intellij.execution.Executor;
import com.intellij.execution.configurations.RunProfile;
import com.intellij.execution.configurations.RunProfileState;
import com.intellij.execution.executors.DefaultDebugExecutor;
import com.intellij.execution.executors.DefaultRunExecutor;
import com.intellij.execution.runners.DefaultProgramRunner;
import com.intellij.execution.runners.ExecutionEnvironment;
import com.intellij.execution.ui.RunContentDescriptor;
import com.intellij.openapi.diagnostic.Logger;
import com.intellij.openapi.project.Project;
import io.github.intellij.dlanguage.run.exception.ModuleNotFoundException;
import io.github.intellij.dlanguage.run.exception.NoValidDlangSdkFound;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class RunAppRunner extends DefaultProgramRunner {
    @NotNull
    @Override
    public String getRunnerId() {
        return getClass().getSimpleName();
    }

    @Override
    public boolean canRun(@NotNull final String executorId, @NotNull final RunProfile profile) {
        return (DefaultDebugExecutor.EXECUTOR_ID.equals(executorId) || DefaultRunExecutor.EXECUTOR_ID.equals(executorId)) &&
            profile instanceof DlangRunAppConfiguration;
    }

    @Nullable
    @Override
    protected RunContentDescriptor doExecute(final RunProfileState state, final ExecutionEnvironment env) throws ExecutionException {
        if (env.getExecutor().getActionName().equals(DefaultDebugExecutor.EXECUTOR_ID)) {
            final Project project = env.getProject();

            final Executor executor = env.getExecutor();
            final Logger logger = Logger.getInstance(this.getClass());
            try {
                return RunUtil.startDebugger(this, state, env, project, executor, ((DlangRunAppState) state).getExecutableCommandLine(((DlangRunAppState) state).getConfig()).getExePath());//todo this is yucky
            } catch (final ModuleNotFoundException e) {
                e.printStackTrace();
                logger.error(e.toString());
            } catch (final NoValidDlangSdkFound NoValidDlangSdkFound) {
                NoValidDlangSdkFound.printStackTrace();
                logger.error(NoValidDlangSdkFound.toString());
            }
        }
        return super.doExecute(state, env);
    }
}
