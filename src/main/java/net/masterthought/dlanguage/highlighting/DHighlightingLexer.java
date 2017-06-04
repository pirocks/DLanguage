package net.masterthought.dlanguage.highlighting;

import com.intellij.lexer.FlexAdapter;
import net.masterthought.dlanguage.DLangHighlightingLexer;

public class DHighlightingLexer extends FlexAdapter {
    public DHighlightingLexer() {
        super(new DLangHighlightingLexer(null));
    }
}
