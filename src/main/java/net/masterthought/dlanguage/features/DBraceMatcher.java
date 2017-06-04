package net.masterthought.dlanguage.features;

import com.intellij.lang.BracePair;
import com.intellij.lang.PairedBraceMatcher;
import com.intellij.psi.PsiFile;
import com.intellij.psi.tree.IElementType;
import com.intellij.psi.tree.TokenSet;
import net.masterthought.dlanguage.psi.DLangTypes;
import net.masterthought.dlanguage.psi.DTokenSets;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class DBraceMatcher implements PairedBraceMatcher {
    private static final BracePair[] PAIRS = new BracePair[]{
        new BracePair(DLangTypes.OP_PAR_LEFT, DLangTypes.OP_PAR_RIGHT, false),
        new BracePair(DLangTypes.OP_BRACES_LEFT, DLangTypes.OP_BRACES_RIGHT, true),
        new BracePair(DLangTypes.OP_BRACKET_LEFT, DLangTypes.OP_BRACKET_RIGHT, false),
    };

    private static final TokenSet alwaysMatch = TokenSet.create(DLangTypes.OP_BRACES_LEFT);

    @Override
    public BracePair[] getPairs() {
        return PAIRS;
    }

    @Override
    public boolean isPairedBracesAllowedBeforeType(@NotNull IElementType lbraceType, @Nullable IElementType contextType) {
        return alwaysMatch.contains(lbraceType)
                || DTokenSets.WHITESPACES.contains(contextType)
                || DTokenSets.LINE_COMMENTS.contains(contextType)
                || DTokenSets.BLOCK_COMMENTS.contains(contextType)
            || DLangTypes.OP_PAR_RIGHT == contextType
                || null == contextType;
    }

    @Override
    public int getCodeConstructStart(PsiFile file, int openingBraceOffset) {
        return openingBraceOffset;
    }
}

