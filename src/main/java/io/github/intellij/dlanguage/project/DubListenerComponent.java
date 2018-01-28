package io.github.intellij.dlanguage.project;

import static io.github.intellij.dlanguage.project.DubConfigFileListener.addProcessDLibsListener;
import static io.github.intellij.dlanguage.project.DubConfigFileListener.getDubFileFromModule;

import com.intellij.openapi.application.Application;
import com.intellij.openapi.application.ApplicationManager;
import com.intellij.openapi.components.ApplicationComponent;
import com.intellij.openapi.module.Module;
import com.intellij.openapi.module.ModuleComponent;
import com.intellij.openapi.module.ModuleManager;
import com.intellij.openapi.project.ModuleListener;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.project.impl.ProjectLifecycleListener;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.util.messages.MessageBus;
import com.intellij.util.messages.MessageBusConnection;
import com.intellij.util.messages.Topic;
import org.jetbrains.annotations.NotNull;

/**
 * Created by francis on 1/27/2018.
 */
public class DubListenerComponent implements ProjectLifecycleListener, ApplicationComponent {

    @Override
    public void initComponent() {
        final MessageBus bus = ApplicationManager.getApplication().getMessageBus();
        final MessageBusConnection connect = bus.connect();
        connect.subscribe(ProjectLifecycleListener.TOPIC, new DubListenerComponent());
    }

    @Override
    public void projectComponentsInitialized(@NotNull final Project project) {
        for (final Module module : ModuleManager.getInstance(project).getModules()) {
            final VirtualFile dubFile = getDubFileFromModule(module);
            if (dubFile != null) {
                addProcessDLibsListener(dubFile, project, module);
            }
        }
    }
}
