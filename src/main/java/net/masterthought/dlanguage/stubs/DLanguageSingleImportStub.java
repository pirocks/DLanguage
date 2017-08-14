package net.masterthought.dlanguage.stubs;

import com.intellij.psi.stubs.IStubElementType;
import com.intellij.psi.stubs.StubElement;
import com.intellij.util.io.StringRef;
import net.masterthought.dlanguage.attributes.DAttributes;
import net.masterthought.dlanguage.psi.DLanguageSingleImport;
import org.jetbrains.annotations.NotNull;

import java.util.HashSet;
import java.util.Set;

public class DLanguageSingleImportStub extends DNamedStubBase<DLanguageSingleImport> {
    private final boolean isPublic;
    private final int numBinds;
    private final Set<String> binds = new HashSet<>();
    private final @NotNull
    String importedModule;
    private final boolean hasName;
    private final String importName;

    public DLanguageSingleImportStub(final StubElement parent, final IStubElementType elementType, final String name, final boolean isPublic, final int numBinds, final Set<StringRef> binds, final @NotNull String importedModule, final boolean hasName, final String importName, final DAttributes attributes) {
        super(parent, elementType, name, attributes);
        this.isPublic = isPublic;
        this.numBinds = numBinds;
        for (final StringRef bind : binds) {
            this.binds.add(bind.getString());
        }
        this.importedModule = importedModule;
        this.hasName = hasName;
        this.importName = importName;
    }

    public DLanguageSingleImportStub(final StubElement parent, final IStubElementType elementType, final StringRef name, final boolean isPublic, final int numBinds, final Set<StringRef> binds, final @NotNull StringRef importedModule, final boolean hasName, final StringRef importName, final DAttributes attributes) {
        this(parent, elementType, name.getString(), isPublic, numBinds, binds, importedModule.getString(), hasName, importName.getString(), attributes);
    }

    public boolean isPublic() {
        return isPublic;
    }

    public int numBinds() {
        return numBinds;
    }

    public Set<String> getBinds() {
        return binds;
    }

    public @NotNull
    String getImportedModule() {
        return importedModule;
    }

    public boolean hasName() {
        return hasName;
    }

    public String getName() {
        return importName;
    }

    public Set<String> getApplicableImportBinds() {
        return binds;
    }
}
