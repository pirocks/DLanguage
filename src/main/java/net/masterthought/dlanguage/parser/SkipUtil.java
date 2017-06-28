package net.masterthought.dlanguage.parser;

import static net.masterthought.dlanguage.parser.Token.peek;
import static net.masterthought.dlanguage.parser.TokenConstants.*;

/**
 * Created by francis on 6/27/2017.
 */
public class SkipUtil {
    /*******************************************
     * original dmd comment
     * Skip parens, brackets.
     * Input:
     *      t is on opening $(LPAREN)
     * Output:
     *      *pt is set to closing token, which is '$(RPAREN)' on success
     * Returns:
     *      true    successful
     *      false   some parsing error
     *      todo javadoc
     */
    static Token skipParens(Token t) {

        if (t.value() != TOKlparen) {
            return null;
        }

        int parens = 0;

        while (true) {
            short i1 = t.value();
            if (i1 == TOKlparen) {
                parens++;

            } else if (i1 == TOKrparen) {
                parens--;
                if (parens < 0)
                    return null;
                if (parens == 0)
                    return peek(t);

            } else if (i1 == TOKeof) {
                return null;
            } else {
                return peek(t);
            }
            t = peek(t);
        }
    }

    /**
     * original dmd comment
     * * Skip attributes.
     * Input:
     * t is on a candidate attribute
     * Output:
     * *pt is set to first non-attribute token on success
     * Returns:
     * true    successful
     * false   some parsing error
     * <p>
     * changes made to the above is null is returned in place of false and a type is returned instead of true
     */
    static Token skipAttributes(Token t) {
        while (true) {
            short i = t.value();
            if (i == TOKconst || i == TOKimmutable || i == TOKshared || i == TOKwild || i == TOKfinal || i == TOKauto || i == TOKscope || i == TOKoverride || i == TOKabstract || i == TOKsynchronized) {

            } else if (i == TOKdeprecated) {
                if (peek(t).value() == TOKlparen) {
                    t = peek(t);
                    if (skipParens(t) == null)
                        return null;
                    t = skipParens(t);
                    // t is on the next of closing parenthesis
                    continue;
                }

            } else if (i == TOKnothrow || i == TOKpure || i == TOKref || i == TOKgshared || i == TOKreturn) {//case TOKmanifest:

            } else if (i == TOKat) {
                t = peek(t);
                if (t.value() == TOKidentifier) {
                    /* @identifier
                     * @identifier!arg
                     * @identifier!(arglist)
                     * any of the above followed by (arglist)
                     * @predefined_attribute
                     */
//                        if (t.ident() == Id.property || t.ident() == Id.nogc || t.ident() == Id.safe || t.ident() == Id.trusted || t.ident() == Id.system || t.ident() == Id.disable)
                    t = peek(t);
                    if (t.value() == TOKnot) {
                        t = peek(t);
                        if (t.value() == TOKlparen) {
                            // @identifier!(arglist)
                            if (skipParens(t) == null)
                                return null;
                            t = skipParens(t);
                            // t is on the next of closing parenthesis
                        } else {
                            // @identifier!arg
                            // Do low rent skipTemplateArgument
                            // no vector keyword todo
                            t = peek(t);
                        }
                    }
                    if (t.value() == TOKlparen) {
                        if (skipParens(t) == null)
                            return null;
                        t = skipParens(t);
                        // t is on the next of closing parenthesis
                        continue;
                    }
                    continue;
                }
                if (t.value() == TOKlparen) {
                    // @( ArgumentList )
                    if (skipParens(t) == null)
                        return null;
                    t = skipParens(t);
                    // t is on the next of closing parenthesis
                    continue;
                }
                return null;
            } else {
                return t;
            }
            t = peek(t);
        }

    }
}
