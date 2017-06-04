package net.masterthought.dlanguage.psi;


import com.intellij.psi.tree.IElementType;
import net.masterthought.dlanguage.DLanguage;
import org.jetbrains.annotations.NonNls;
import org.jetbrains.annotations.NotNull;

public class DLangTokenType extends IElementType {

    public DLangTokenType(@NotNull @NonNls String debugName) {
        super(debugName, DLanguage.INSTANCE);
    }

    @Override
    public String toString() {
        return "DLangTokenType." + super.toString();
    }

}
