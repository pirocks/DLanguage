package net.masterthought.dlanguage.parser;

import com.intellij.lang.PsiBuilder;
import net.masterthought.dlanguage.psi.DLanguageTypes;

/**
 * Created by francis on 6/27/2017.
 * mimics the token class in dmd to minimize the changes from original dmd source
 */
public class Token {
    PsiBuilder builder;
    int i;

    private Token(PsiBuilder builder, int i) {
        this.builder = builder;
        this.i = i;
    }

    public Token(PsiBuilder builder) {
        this.builder = builder;
        i = 0;
    }

    public static Token peek(Token token) {
        return new Token(token.builder, token.i + 1);
    }

    public short value() {
        return builder.lookAhead(i).getIndex();
    }

    public boolean ident() {
        return builder.lookAhead(i).getIndex() == DLanguageTypes.ID.getIndex();
    }

}
