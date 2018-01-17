package io.github.intellij.dlanguage.utils;

import com.intellij.execution.ExecutionException;
import com.intellij.execution.configurations.GeneralCommandLine;
import com.intellij.execution.process.CapturingProcessHandler;
import com.intellij.execution.process.ProcessOutput;
import com.intellij.openapi.diagnostic.Logger;
import com.intellij.openapi.module.Module;
import com.intellij.openapi.module.ModuleUtilCore;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.project.ProjectManager;
import com.intellij.openapi.util.SystemInfo;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.psi.PsiFile;
import com.intellij.util.containers.ContainerUtil;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.nio.charset.Charset;
import java.util.List;

/**
 * Helper class to perform execution related tasks, including locating programs.
 */
public class ExecUtil {
    // Messages go to the log available in Help -> Show log in finder.
    private final static Logger LOG = Logger.getInstance(ExecUtil.class);

    /**
     * Execute a command using the default shell.
     */
    @Nullable
    public static String exec(@NotNull final String command) {
        // Find some valid working directory, doesn't matter which one.
        final ProjectManager pm = ProjectManager.getInstance();
        final Project[] projects = pm == null ? null : pm.getOpenProjects();
        final String defaultWorkDir = ".";
        final String workDir;

        // Set the working directory if there is an open project.
        if (pm == null || projects.length == 0) {
            LOG.info("No open projects so cannot find a valid path. Using '.'.");
            workDir = defaultWorkDir;
        } else {
            if(projects[0].getBaseDir() == null){
                workDir = defaultWorkDir;//getBaseDir returns null for default project
            }else
            workDir = projects[0].getBaseDir().getCanonicalPath();
        }
        return exec(workDir == null ? defaultWorkDir : workDir, command);
    }

    /**
     * Execute a command using the default shell in a given work directory.
     */
    @Nullable
    public static String exec(@NotNull final String workDir, @NotNull final String command) {
        // Setup shell and the GeneralCommandLine.
        final GeneralCommandLine commandLine = new GeneralCommandLine();
        commandLine.setWorkDirectory(workDir);
        commandLine.withParentEnvironmentType(GeneralCommandLine.ParentEnvironmentType.CONSOLE);

        if (SystemInfo.isWindows) {
            commandLine.setExePath("cmd");
            commandLine.addParameter("/c");
        } else {
            // Default to UNIX if not Windows.
            commandLine.setExePath("/bin/sh");
            commandLine.addParameter("-c");
        }
        commandLine.addParameter(command);

        final ProcessOutput output;
        try {
            output = new CapturingProcessHandler(commandLine.createProcess(),
                Charset.defaultCharset(), commandLine.getCommandLineString()).runProcess();
        } catch (final ExecutionException e) {
            LOG.info("Failed executing " + command);
            LOG.info("Message: " + e.getMessage());
            return null;
        }

        if (output == null) {
            LOG.info("No output from " + command);
            return null;
        }

        final List<String> lines = output.getStdoutLines();
        final StringBuilder sb = new StringBuilder(100 * lines.size());
        for (final String line : lines) {
            sb.append(line);
        }
        return sb.toString();
    }

    /**
     * Tries to get the absolute path for a command in the PATH.
     */
    @Nullable
    public static String locateExecutable(@NotNull final String command) {
        final String whereCmd = (SystemInfo.isWindows ? "where" : "which") + ' ' + command;
        String res = exec(whereCmd);
        if (res != null && SystemInfo.isWindows && res.contains("C:\\")) {
            final String[] split = res.split("(?=C:\\\\)");
            res = split[0];//if there are multiple results defualt to first one.
        }
        if (res != null && res.isEmpty()) {
            LOG.info("Could not find " + command);
        }
        return res;
    }

    /**
     * Tries to get the absolute path for a command by defaulting to first
     * trying to locate the command in path, and falling back to trying likely
     * directories.
     */
    @Nullable
    public static String locateExecutableByGuessing(@NotNull final String command) {
        final String located = locateExecutable(command);
        if (located != null && !located.isEmpty()) {
            // Found it!
            return located;
        }

        final char sep = File.separatorChar;
        final List<String> paths = ContainerUtil.newArrayList();
        if (SystemInfo.isWindows) {
            paths.add(sep + "D" + sep + "dmd2" + sep + "windows" + sep + "bin");
        } else {
            final String homeDir = System.getProperty("user.home");
            paths.add(sep + "usr" + sep + "local" + sep + "bin");
            paths.add(sep + "usr" + sep + "bin");
            paths.add(homeDir + sep + "bin");
        }
        for (final String path : paths) {
            LOG.info(String.format("Looking for %s in %s", command, path));
            final String cmd = path + sep + command;
            //noinspection ObjectAllocationInLoop
            if (new File(cmd).canExecute()) return cmd;
        }
        return null;
    }

    @NotNull
    public static String guessWorkDir(@NotNull final Project project, @NotNull final VirtualFile file) {
        final Module module = ModuleUtilCore.findModuleForFile(file, project);
        return module == null ? project.getBasePath() : guessWorkDir(module);
    }

    @NotNull
    public static String guessWorkDir(@NotNull final PsiFile file) {
        return guessWorkDir(file.getProject(), file.getVirtualFile());
    }

    @NotNull
    public static String guessWorkDir(@NotNull final Module module) {
        final VirtualFile moduleFile = module.getModuleFile();
        final VirtualFile moduleDir = moduleFile == null ? null : moduleFile.getParent();
        return moduleDir == null ? module.getProject().getBasePath() : moduleDir.getPath();
    }

    /**
     * Executes commandLine, optionally piping input to stdin, and return stdout.
     */
    @Nullable
    public static String readCommandLine(@NotNull final GeneralCommandLine commandLine, @Nullable final String input) {
        String output = null;
        try {
            final Process process = commandLine.createProcess();
            if (input != null) {
                final BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(process.getOutputStream()));
                writer.write(input);
                writer.flush();
                writer.close();
            }

            output = new CapturingProcessHandler(process,
                Charset.defaultCharset(),
                commandLine.getCommandLineString()
            ).runProcess().getStdout();

        } catch (final ExecutionException e) {
            LOG.debug(e);
        } catch (final IOException e) {
            LOG.debug(e);
        }
        return output;
    }

    @Nullable
    public static String readCommandLine(@NotNull final GeneralCommandLine commandLine) {
        return readCommandLine(commandLine, null);
    }

    @Nullable
    public static String readCommandLine(@Nullable final String workingDirectory, @NotNull final String command, @NotNull final String[] params, @Nullable final String input) {
        final GeneralCommandLine commandLine = new GeneralCommandLine(command);
        if (workingDirectory != null) {
            commandLine.setWorkDirectory(workingDirectory);
        }
        commandLine.addParameters(params);
        return readCommandLine(commandLine, input);
    }

    @Nullable
    public static String readCommandLine(@Nullable final String workingDirectory, @NotNull final String command, @NotNull final String... params) {
        return readCommandLine(workingDirectory, command, params, null);
    }
}
