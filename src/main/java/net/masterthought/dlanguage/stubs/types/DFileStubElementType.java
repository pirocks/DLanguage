package net.masterthought.dlanguage.stubs.types;

import com.intellij.psi.PsiFile;
import com.intellij.psi.StubBuilder;
import com.intellij.psi.stubs.DefaultStubBuilder;
import com.intellij.psi.stubs.StubElement;
import com.intellij.psi.stubs.StubInputStream;
import com.intellij.psi.stubs.StubOutputStream;
import com.intellij.psi.tree.IStubFileElementType;
import net.masterthought.dlanguage.DLanguage;
import net.masterthought.dlanguage.psi.DLangFile;
import net.masterthought.dlanguage.stubs.DLangFileStub;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;

public class DFileStubElementType extends IStubFileElementType<DLangFileStub> {
    public static final int VERSION = 0;
    public static final DFileStubElementType INSTANCE = new DFileStubElementType();

    public DFileStubElementType() {
        super("FILE", DLanguage.INSTANCE);
    }

    @Override
    public StubBuilder getBuilder() {
        return new DefaultStubBuilder() {
            @NotNull
            @Override
            protected StubElement createStubForFile(@NotNull PsiFile file) {
                if (file instanceof DLangFile) {
                    return new DLangFileStub((DLangFile) file);
                }
                return super.createStubForFile(file);
            }
        };
    }

    @Override
    public int getStubVersion() {
        return VERSION;
    }

    @Override
    public void serialize(@NotNull DLangFileStub stub, @NotNull StubOutputStream dataStream) throws IOException {
        // TODO
    }

    @NotNull
    @Override
    public DLangFileStub deserialize(@NotNull StubInputStream dataStream, StubElement parentStub) throws IOException {
        return new DLangFileStub(null);
    }

    @NotNull
    @Override
    public String getExternalId() {
        return "d.FILE";
    }
}

