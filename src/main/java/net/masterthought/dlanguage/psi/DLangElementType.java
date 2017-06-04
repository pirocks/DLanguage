package net.masterthought.dlanguage.psi;

import com.intellij.psi.tree.IElementType;
import net.masterthought.dlanguage.DLanguage;
import org.jetbrains.annotations.NonNls;
import org.jetbrains.annotations.NotNull;

public class DLangElementType extends IElementType {

    public DLangElementType(@NotNull @NonNls String debugName) {
        super(debugName, DLanguage.INSTANCE);
    }
}
