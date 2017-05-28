package net.masterthought.dlanguage.stubs.index;

import com.intellij.psi.stubs.StringStubIndexExtension;
import com.intellij.psi.stubs.StubIndexKey;
import net.masterthought.dlanguage.psi.interfaces.Declaration;
import org.jetbrains.annotations.NotNull;

/**
 * Stub index to store all names defined in the project; specifically for the "go to symbol" feature.
 */
public class DTopLevelDeclarationIndex extends StringStubIndexExtension<Declaration> {
    public static final StubIndexKey<String, Declaration> KEY = StubIndexKey.createIndexKey("d.globally.accessible.name");
    public static final int VERSION = 1;

    @Override
    public int getVersion() {
        return super.getVersion() + VERSION;
    }

    @NotNull
    @Override
    public StubIndexKey<String, Declaration> getKey() {
        return KEY;
    }
}

