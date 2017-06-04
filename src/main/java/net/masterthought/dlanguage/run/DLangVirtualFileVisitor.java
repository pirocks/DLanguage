package net.masterthought.dlanguage.run;

import com.intellij.openapi.vfs.VfsUtilCore;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.openapi.vfs.VirtualFileVisitor;
import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class DLangVirtualFileVisitor extends VirtualFileVisitor {
    private final char separator = File.separatorChar;
    private List<String> dLangSources;
    private VirtualFile sourcesRoot;
    private VirtualFile[] excludedRoots;


    public DLangVirtualFileVisitor(VirtualFile sourcesRoot, VirtualFile[] excludedRoots) {
        dLangSources = new ArrayList<String>();
        this.sourcesRoot = sourcesRoot;
        this.excludedRoots = excludedRoots;
    }

    @Override
    public boolean visitFile(@NotNull VirtualFile file) {
        if (!file.isDirectory() && "d".equals(file.getExtension()) && !isExcluded(file)) {
            dLangSources.add(VfsUtilCore.getRelativePath(file, sourcesRoot, separator));
        }
        return super.visitFile(file);
    }

    private boolean isExcluded(VirtualFile srcFile) {
        for (VirtualFile excludeDir : excludedRoots) {
            if (VfsUtilCore.isAncestor(excludeDir, srcFile, false)) {
                return true;
            }
        }
        return false;
    }

    public List<String> getdLangSources() {
        return dLangSources;
    }
}
