package net.masterthought.dlanguage.parser;

import com.intellij.lang.PsiBuilder;
import com.intellij.psi.tree.IElementType;
import net.masterthought.dlanguage.psi.DLanguageTypes;

import static com.intellij.lang.parser.GeneratedParserUtilBase.*;
import static net.masterthought.dlanguage.parser.MiscParser.error;
import static net.masterthought.dlanguage.parser.SkipUtil.*;
import static net.masterthought.dlanguage.parser.Token.CurrentToken.peekNext;
import static net.masterthought.dlanguage.parser.Token.CurrentToken.peekNext2;
import static net.masterthought.dlanguage.parser.Token.peek;
import static net.masterthought.dlanguage.parser.TokenConstants.*;

/**
 * Created by francis on 6/28/2017.
 */
public class DeclDefs {
    /*
    Dsymbols* parseDeclDefs(int once, Dsymbol* pLastDecl = null, PrefixAttributes* pAttrs = null)
    {
        Dsymbol lastDecl = null; // used to link unittest to its previous declaration
        if (!pLastDecl)
            pLastDecl = &lastDecl;

        const linksave = linkage; // save global state

        //printf("Parser::parseDeclDefs()\n");
        auto decldefs = new Dsymbols();
        do
        {
            // parse result
            Dsymbol s = null;
            Dsymbols* a = null;

            PrefixAttributes attrs;
            if (!once || !pAttrs)
            {
                pAttrs = &attrs;
                pAttrs.comment = token.blockComment;
            }
            PROTKIND prot;
            StorageClass stc;
            Condition condition;

            linkage = linksave;

            switch (token.value)
            {
                case TOKenum:
                {
                    /* Determine if this is a manifest constant declaration,
                     * or a conventional enum.
                     +/
                    Token* t = peek(&token);
                    if (t.value == TOKlcurly || t.value == TOKcolon)
                        s = parseEnum();
                    else if (t.value != TOKidentifier)
                        goto Ldeclaration;
                    else
                    {
                        t = peek(t);
                        if (t.value == TOKlcurly || t.value == TOKcolon || t.value == TOKsemicolon)
                            s = parseEnum();
                        else
                            goto Ldeclaration;
                    }
                    break;
                }
                case TOKimport:
                    a = parseImport();
                    // keep pLastDecl
                    break;

                case TOKtemplate:
                    s = cast(Dsymbol)parseTemplateDeclaration();
                    break;

                case TOKmixin:
                {
                    const loc = token.loc;
                    switch (peekNext())
                    {
                        case TOKlparen:
                        {
                            // mixin(string)
                            nextToken();
                            check(TOKlparen, "mixin");
                            Expression e = parseAssignExp();
                            check(TOKrparen);
                            check(TOKsemicolon);
                            s = new CompileDeclaration(loc, e);
                            break;
                        }
                        case TOKtemplate:
                            // mixin template
                            nextToken();
                            s = cast(Dsymbol)parseTemplateDeclaration(true);
                            break;

                        default:
                            s = parseMixin();
                            break;
                    }
                    break;
                }
                case TOKwchar:
                case TOKdchar:
                case TOKbool:
                case TOKchar:
                case TOKint8:
                case TOKuns8:
                case TOKint16:
                case TOKuns16:
                case TOKint32:
                case TOKuns32:
                case TOKint64:
                case TOKuns64:
                case TOKint128:
                case TOKuns128:
                case TOKfloat32:
                case TOKfloat64:
                case TOKfloat80:
                case TOKimaginary32:
                case TOKimaginary64:
                case TOKimaginary80:
                case TOKcomplex32:
                case TOKcomplex64:
                case TOKcomplex80:
                case TOKvoid:
                case TOKalias:
                case TOKidentifier:
                case TOKsuper:
                case TOKtypeof:
                case TOKdot:
                case TOKvector:
                case TOKstruct:
                case TOKunion:
                case TOKclass:
                case TOKinterface:
                    Ldeclaration:
                    a = parseDeclarations(false, pAttrs, pAttrs.comment);
                    if (a && a.dim)
                    *pLastDecl = (*a)[a.dim - 1];
                    break;

                case TOKthis:
                    if (peekNext() == TOKdot)
                    goto Ldeclaration;
                else
                    s = parseCtor(pAttrs);
                    break;

                case TOKtilde:
                    s = parseDtor(pAttrs);
                    break;

                case TOKinvariant:
                {
                    Token* t = peek(&token);
                    if (t.value == TOKlparen && peek(t).value == TOKrparen || t.value == TOKlcurly)
                    {
                        // invariant {}
                        // invariant() {}
                        s = parseInvariant(pAttrs);
                    }
                    else
                    {
                        error("invariant body expected, not '%s'", token.toChars());
                        goto Lerror;
                    }
                    break;
                }
                case TOKunittest:
                    if (global.params.useUnitTests || global.params.doDocComments || global.params.doHdrGeneration)
                    {
                        s = parseUnitTest(pAttrs);
                        if (*pLastDecl)
                        (*pLastDecl).ddocUnittest = cast(UnitTestDeclaration)s;
                    }
                    else
                    {
                        // Skip over unittest block by counting { }
                        Loc loc = token.loc;
                        int braces = 0;
                        while (1)
                        {
                            nextToken();
                            switch (token.value)
                            {
                                case TOKlcurly:
                                    ++braces;
                                    continue;

                                case TOKrcurly:
                                    if (--braces)
                                        continue;
                                    nextToken();
                                    break;

                                case TOKeof:
                            /* { +/
                                    error(loc, "closing } of unittest not found before end of file");
                            goto Lerror;

                                default:
                                    continue;
                            }
                            break;
                        }
                        // Workaround 14894. Add an empty unittest declaration to keep
                        // the number of symbols in this scope independent of -unittest.
                        s = new UnitTestDeclaration(loc, token.loc, STCundefined, null);
                    }
                    break;

                case TOKnew:
                    s = parseNew(pAttrs);
                    break;

                case TOKdelete:
                    s = parseDelete(pAttrs);
                    break;

                case TOKcolon:
                case TOKlcurly:
                    error("declaration expected, not '%s'", token.toChars());
                goto Lerror;

                case TOKrcurly:
                case TOKeof:
                    if (once)
                        error("declaration expected, not '%s'", token.toChars());
                    return decldefs;

                case TOKstatic:
                {
                    const next = peekNext();
                    if (next == TOKthis)
                        s = parseStaticCtor(pAttrs);
                    else if (next == TOKtilde)
                        s = parseStaticDtor(pAttrs);
                    else if (next == TOKassert)
                        s = parseStaticAssert();
                    else if (next == TOKif)
                    {
                        condition = parseStaticIfCondition();
                        Dsymbols* athen;
                        if (token.value == TOKcolon)
                            athen = parseBlock(pLastDecl);
                        else
                        {
                            const lookingForElseSave = lookingForElse;
                            lookingForElse = token.loc;
                            athen = parseBlock(pLastDecl);
                            lookingForElse = lookingForElseSave;
                        }
                        Dsymbols* aelse = null;
                        if (token.value == TOKelse)
                        {
                            const elseloc = token.loc;
                            nextToken();
                            aelse = parseBlock(pLastDecl);
                            checkDanglingElse(elseloc);
                        }
                        s = new StaticIfDeclaration(condition, athen, aelse);
                    }
                    else if (next == TOKimport)
                    {
                        a = parseImport();
                        // keep pLastDecl
                    }
                    else
                    {
                        stc = STCstatic;
                        goto Lstc;
                    }
                    break;
                }
                case TOKconst:
                    if (peekNext() == TOKlparen)
                    goto Ldeclaration;
                    stc = STCconst;
                goto Lstc;

                case TOKimmutable:
                    if (peekNext() == TOKlparen)
                    goto Ldeclaration;
                    stc = STCimmutable;
                goto Lstc;

                case TOKshared:
                {
                    const next = peekNext();
                    if (next == TOKlparen)
                        goto Ldeclaration;
                    if (next == TOKstatic)
                    {
                        TOK next2 = peekNext2();
                        if (next2 == TOKthis)
                        {
                            s = parseSharedStaticCtor(pAttrs);
                            break;
                        }
                        if (next2 == TOKtilde)
                        {
                            s = parseSharedStaticDtor(pAttrs);
                            break;
                        }
                    }
                    stc = STCshared;
                    goto Lstc;
                }
                case TOKwild:
                    if (peekNext() == TOKlparen)
                    goto Ldeclaration;
                    stc = STCwild;
                goto Lstc;

                case TOKfinal:
                    stc = STCfinal;
                goto Lstc;

                case TOKauto:
                    stc = STCauto;
                goto Lstc;

                case TOKscope:
                    stc = STCscope;
                goto Lstc;

                case TOKoverride:
                    stc = STCoverride;
                goto Lstc;

                case TOKabstract:
                    stc = STCabstract;
                goto Lstc;

                case TOKsynchronized:
                    stc = STCsynchronized;
                goto Lstc;

                case TOKnothrow:
                    stc = STCnothrow;
                goto Lstc;

                case TOKpure:
                    stc = STCpure;
                goto Lstc;

                case TOKref:
                    stc = STCref;
                goto Lstc;

                case TOKgshared:
                    stc = STCgshared;
                goto Lstc;

                    //case TOKmanifest:   stc = STCmanifest;     goto Lstc;

                case TOKat:
                {
                    Expressions* exps = null;
                    stc = parseAttribute(&exps);
                    if (stc)
                        goto Lstc; // it's a predefined attribute
                    // no redundant/conflicting check for UDAs
                    pAttrs.udas = UserAttributeDeclaration.concat(pAttrs.udas, exps);
                    goto Lautodecl;
                }
                Lstc:
                pAttrs.storageClass = appendStorageClass(pAttrs.storageClass, stc);
                nextToken();

                Lautodecl:
                Token* tk;

                /* Look for auto initializers:
                 *      storage_class identifier = initializer;
                 *      storage_class identifier(...) = initializer;
                 +/
                if (token.value == TOKidentifier && skipParensIf(peek(&token), &tk) && tk.value == TOKassign)
                {
                    a = parseAutoDeclarations(getStorageClass(pAttrs), pAttrs.comment);
                    if (a && a.dim)
                        *pLastDecl = (*a)[a.dim - 1];
                    if (pAttrs.udas)
                    {
                        s = new UserAttributeDeclaration(pAttrs.udas, a);
                        pAttrs.udas = null;
                    }
                    break;
                }

                /* Look for return type inference for template functions.
                 +/
                if (token.value == TOKidentifier && skipParens(peek(&token), &tk) && skipAttributes(tk, &tk) && (tk.value == TOKlparen || tk.value == TOKlcurly || tk.value == TOKin || tk.value == TOKout || tk.value == TOKbody))
                {
                    a = parseDeclarations(true, pAttrs, pAttrs.comment);
                    if (a && a.dim)
                        *pLastDecl = (*a)[a.dim - 1];
                    if (pAttrs.udas)
                    {
                        s = new UserAttributeDeclaration(pAttrs.udas, a);
                        pAttrs.udas = null;
                    }
                    break;
                }

                a = parseBlock(pLastDecl, pAttrs);
                auto stc2 = getStorageClass(pAttrs);
                if (stc2 != STCundefined)
                {
                    s = new StorageClassDeclaration(stc2, a);
                }
                if (pAttrs.udas)
                {
                    if (s)
                    {
                        a = new Dsymbols();
                        a.push(s);
                    }
                    s = new UserAttributeDeclaration(pAttrs.udas, a);
                    pAttrs.udas = null;
                }
                break;

                case TOKdeprecated:
                {
                    if (peek(&token).value != TOKlparen)
                    {
                        stc = STCdeprecated;
                        goto Lstc;
                    }
                    nextToken();
                    check(TOKlparen);
                    Expression e = parseAssignExp();
                    check(TOKrparen);
                    if (pAttrs.depmsg)
                    {
                        error("conflicting storage class 'deprecated(%s)' and 'deprecated(%s)'", pAttrs.depmsg.toChars(), e.toChars());
                    }
                    pAttrs.depmsg = e;
                    a = parseBlock(pLastDecl, pAttrs);
                    if (pAttrs.depmsg)
                    {
                        s = new DeprecatedDeclaration(pAttrs.depmsg, a);
                        pAttrs.depmsg = null;
                    }
                    break;
                }
                case TOKlbracket:
                {
                    if (peekNext() == TOKrbracket)
                        error("empty attribute list is not allowed");
                    error("use @(attributes) instead of [attributes]");
                    Expressions* exps = parseArguments();
                    // no redundant/conflicting check for UDAs

                    pAttrs.udas = UserAttributeDeclaration.concat(pAttrs.udas, exps);
                    a = parseBlock(pLastDecl, pAttrs);
                    if (pAttrs.udas)
                    {
                        s = new UserAttributeDeclaration(pAttrs.udas, a);
                        pAttrs.udas = null;
                    }
                    break;
                }
                case TOKextern:
                {
                    if (peek(&token).value != TOKlparen)
                    {
                        stc = STCextern;
                        goto Lstc;
                    }

                    const linkLoc = token.loc;
                    Identifiers* idents = null;
                    CPPMANGLE cppmangle;
                    const link = parseLinkage(&idents, cppmangle);
                    if (pAttrs.link != LINKdefault)
                    {
                        if (pAttrs.link != link)
                        {
                            error("conflicting linkage extern (%s) and extern (%s)", linkageToChars(pAttrs.link), linkageToChars(link));
                        }
                        else if (idents)
                        {
                            // Allow:
                            //      extern(C++, foo) extern(C++, bar) void foo();
                            // to be equivalent with:
                            //      extern(C++, foo.bar) void foo();
                        }
                        else
                            error("redundant linkage extern (%s)", linkageToChars(pAttrs.link));
                    }
                    pAttrs.link = link;
                    this.linkage = link;
                    a = parseBlock(pLastDecl, pAttrs);
                    if (idents)
                    {
                        assert(link == LINKcpp);
                        assert(idents.dim);
                        for (size_t i = idents.dim; i;)
                        {
                            Identifier id = (*idents)[--i];
                            if (s)
                            {
                                a = new Dsymbols();
                                a.push(s);
                            }
                            s = new Nspace(linkLoc, id, a);
                        }
                        pAttrs.link = LINKdefault;
                    }
                    else if (cppmangle != CPPMANGLE.def)
                    {
                        assert(link == LINKcpp);
                        s = new CPPMangleDeclaration(cppmangle, a);
                    }
                    else if (pAttrs.link != LINKdefault)
                    {
                        s = new LinkDeclaration(pAttrs.link, a);
                        pAttrs.link = LINKdefault;
                    }
                    break;
                }

                case TOKprivate:
                    prot = PROTprivate;
                goto Lprot;

                case TOKpackage:
                    prot = PROTpackage;
                goto Lprot;

                case TOKprotected:
                    prot = PROTprotected;
                goto Lprot;

                case TOKpublic:
                    prot = PROTpublic;
                goto Lprot;

                case TOKexport:
                    prot = PROTexport;
                goto Lprot;
                    Lprot:
                    {
                        if (pAttrs.protection.kind != PROTundefined)
                        {
                            if (pAttrs.protection.kind != prot)
                                error("conflicting protection attribute '%s' and '%s'", protectionToChars(pAttrs.protection.kind), protectionToChars(prot));
                            else
                                error("redundant protection attribute '%s'", protectionToChars(prot));
                        }
                        pAttrs.protection.kind = prot;

                        nextToken();

                        // optional qualified package identifier to bind
                        // protection to
                        Identifiers* pkg_prot_idents = null;
                        if (pAttrs.protection.kind == PROTpackage && token.value == TOKlparen)
                        {
                            pkg_prot_idents = parseQualifiedIdentifier("protection package");
                            if (pkg_prot_idents)
                                check(TOKrparen);
                            else
                            {
                                while (token.value != TOKsemicolon && token.value != TOKeof)
                                    nextToken();
                                nextToken();
                                break;
                            }
                        }

                    const attrloc = token.loc;
                        a = parseBlock(pLastDecl, pAttrs);
                        if (pAttrs.protection.kind != PROTundefined)
                        {
                            if (pAttrs.protection.kind == PROTpackage && pkg_prot_idents)
                                s = new ProtDeclaration(attrloc, pkg_prot_idents, a);
                            else
                                s = new ProtDeclaration(attrloc, pAttrs.protection, a);

                            pAttrs.protection = Prot(PROTundefined);
                        }
                        break;
                    }
                case TOKalign:
                {
                    const attrLoc = token.loc;

                    nextToken();

                    Expression e = null; // default
                    if (token.value == TOKlparen)
                    {
                        nextToken();
                        e = parseAssignExp();
                        check(TOKrparen);
                    }

                    if (pAttrs.setAlignment)
                    {
                        if (e)
                            error("redundant alignment attribute align(%s)", e.toChars());
                        else
                            error("redundant alignment attribute align");
                    }

                    pAttrs.setAlignment = true;
                    pAttrs.ealign = e;
                    a = parseBlock(pLastDecl, pAttrs);
                    if (pAttrs.setAlignment)
                    {
                        s = new AlignDeclaration(attrLoc, pAttrs.ealign, a);
                        pAttrs.setAlignment = false;
                        pAttrs.ealign = null;
                    }
                    break;
                }
                case TOKpragma:
                {
                    Expressions* args = null;
                    const loc = token.loc;

                    nextToken();
                    check(TOKlparen);
                    if (token.value != TOKidentifier)
                    {
                        error("pragma(identifier) expected");
                        goto Lerror;
                    }
                    Identifier ident = token.ident;
                    nextToken();
                    if (token.value == TOKcomma && peekNext() != TOKrparen)
                        args = parseArguments(); // pragma(identifier, args...)
                    else
                        check(TOKrparen); // pragma(identifier)

                    Dsymbols* a2 = null;
                    if (token.value == TOKsemicolon)
                    {
                        /* Bugzilla 2354: Accept single semicolon as an empty
                         * DeclarationBlock following attribute.
                         *
                         * Attribute DeclarationBlock
                         * Pragma    DeclDef
                         *           ;
                         +/
                        nextToken();
                    }
                    else
                        a2 = parseBlock(pLastDecl);
                    s = new PragmaDeclaration(loc, ident, args, a2);
                    break;
                }
                case TOKdebug:
                    nextToken();
                    if (token.value == TOKassign)
                    {
                        nextToken();
                        if (token.value == TOKidentifier)
                            s = new DebugSymbol(token.loc, token.ident);
                        else if (token.value == TOKint32v || token.value == TOKint64v)
                            s = new DebugSymbol(token.loc, cast(uint)token.uns64value);
                        else
                        {
                            error("identifier or integer expected, not %s", token.toChars());
                            s = null;
                        }
                        nextToken();
                        if (token.value != TOKsemicolon)
                            error("semicolon expected");
                        nextToken();
                        break;
                    }

                    condition = parseDebugCondition();
                goto Lcondition;

                case TOKversion:
                    nextToken();
                    if (token.value == TOKassign)
                    {
                        nextToken();
                        if (token.value == TOKidentifier)
                            s = new VersionSymbol(token.loc, token.ident);
                        else if (token.value == TOKint32v || token.value == TOKint64v)
                            s = new VersionSymbol(token.loc, cast(uint)token.uns64value);
                        else
                        {
                            error("identifier or integer expected, not %s", token.toChars());
                            s = null;
                        }
                        nextToken();
                        if (token.value != TOKsemicolon)
                            error("semicolon expected");
                        nextToken();
                        break;
                    }
                    condition = parseVersionCondition();
                goto Lcondition;

                    Lcondition:
                    {
                        Dsymbols* athen;
                        if (token.value == TOKcolon)
                            athen = parseBlock(pLastDecl);
                        else
                        {
                        const lookingForElseSave = lookingForElse;
                            lookingForElse = token.loc;
                            athen = parseBlock(pLastDecl);
                            lookingForElse = lookingForElseSave;
                        }
                        Dsymbols* aelse = null;
                        if (token.value == TOKelse)
                        {
                        const elseloc = token.loc;
                            nextToken();
                            aelse = parseBlock(pLastDecl);
                            checkDanglingElse(elseloc);
                        }
                        s = new ConditionalDeclaration(condition, athen, aelse);
                        break;
                    }
                case TOKsemicolon:
                    // empty declaration
                    //error("empty declaration");
                    nextToken();
                    continue;

                default:
                    error("declaration expected, not '%s'", token.toChars());
                    Lerror:
                    while (token.value != TOKsemicolon && token.value != TOKeof)
                        nextToken();
                    nextToken();
                    s = null;
                    continue;
            }

            if (s)
            {
                if (!s.isAttribDeclaration())
                    *pLastDecl = s;
                decldefs.push(s);
                addComment(s, pAttrs.comment);
            }
            else if (a && a.dim)
            {
                decldefs.append(a);
            }
        }
        while (!once);

        linkage = linksave;

        return decldefs;
    }
    */
    static void check(Token token, IElementType type) {
        check(token, type.getIndex());
//        consumeToken(token.builder, type);
    }

    static void check(Token token, short type) {
        if (token.value() != type)
            error(String.format("found '%s' when expecting '%s'", token.toString(), String.valueOf(type)), token.builder);
//        consumeToken(token.builder, type);
    }


    public static boolean parseDeclDefs(int once, PsiBuilder builder, int l) {

        if (!recursion_guard_(builder, l, "DeclDefs")) return false;
        final PsiBuilder.Marker marker = enter_section_(builder);
        final Token token = new Token.CurrentToken(builder);
//        auto decldefs = new Dsymbols();
        do {
            // parse result
//            Dsymbol s = null;
//            Dsymbols * a = null;

//            PrefixAttributes attrs;
//            if (!once || !pAttrs) {
//                pAttrs = &attrs;
//                pAttrs.comment = token.blockComment;
//            }
//            PROTKIND prot;
//            StorageClass stc;
//            Condition condition;
//
//            linkage = linksave;

            switch (token.value()) {
                case TOKenum: {
                    /* Determine if this is a manifest constant declaration,
                     * or a conventional enum.
                     */
                    Token t = peek(token);
                    if (t.value() == TOKlcurly || t.value() == TOKcolon) {
//                        s = parseEnum();
                        DeclarationParser.EnumDeclaration(builder, l + 1);
                    } else if (t.value() != TOKidentifier) {
                        parseDeclarations(builder, l);
                        break;
                    } else {
                        t = peek(t);
                        if (t.value() == TOKlcurly || t.value() == TOKcolon || t.value() == TOKsemicolon)
//                        s = parseEnum();
                            DeclarationParser.EnumDeclaration(builder, l + 1);
                        else {
                            parseDeclarations(builder, l);
                            break;
                        }
                    }
                    break;
                }
                case TOKimport:
//                    a = parseImport();
                    DeclDefParser.ImportDeclaration(builder, l + 1);
                    // keep pLastDecl
                    break;

                case TOKtemplate:
//                    s = cast(Dsymbol) parseTemplateDeclaration();
                    DeclarationParser.TemplateDeclaration(builder, l + 1);
                    break;

                case TOKmixin: {
                    switch (peek(token).value()) {
                        case TOKlparen: {
                            final PsiBuilder.Marker mixinDeclaration = enter_section_(builder);
                            // mixin(string)
//                            nextToken();
                            check(token, TOKlparen);
                            consumeToken(builder, DLanguageTypes.KW_MIXIN);
//                            Expression e = parseAssignExp();
                            ExpressionParser.AssignExpression(builder, l + 2);
                            check(token, TOKrparen);
                            check(token, TOKsemicolon);
                            exit_section_(builder, mixinDeclaration, DLanguageTypes.MIXIN_DECLARATION, true);
                            break;
                        }
                        case TOKtemplate:
                            // mixin template
//                            s = cast(Dsymbol) parseTemplateDeclaration(true);
                            DeclarationParser.TemplateMixinDeclaration(builder, l + 1);
                            break;

                        default:
//                            s = parseMixin();
                            DeclDefParser.MixinDeclaration(builder, l + 1);
                            break;
                    }
                    break;
                }
                case TOKwchar:
                case TOKdchar:
                case TOKbool:
                case TOKchar:
                case TOKint8:
                case TOKuns8:
                case TOKint16:
                case TOKuns16:
                case TOKint32:
                case TOKuns32:
                case TOKint64:
                case TOKuns64:
                case TOKint128:
                case TOKuns128:
                case TOKfloat32:
                case TOKfloat64:
                case TOKfloat80:
                case TOKimaginary32:
                case TOKimaginary64:
                case TOKimaginary80:
                case TOKcomplex32:
                case TOKcomplex64:
                case TOKcomplex80:
                case TOKvoid:
                case TOKalias:
                case TOKidentifier:
                case TOKsuper:
                case TOKtypeof:
                case TOKdot:
                case TOKvector:
                case TOKstruct:
                case TOKunion:
                case TOKclass:
                case TOKinterface:
                    parseDeclarations(builder, l + 1);
                    break;

                case TOKthis:
                    if (peek(token).value() == TOKdot) {
                        parseDeclarations(builder, l + 1);
                        break;
                    } else
                        DeclDefParser.Constructor(builder, l + 1);
                    break;

                case TOKtilde:
                    DeclDefParser.Destructor(builder, l + 1);
                    break;

                case TOKinvariant: {
                    Token t = peek(token);
                    if (t.value() == TOKlparen && peek(t).value() == TOKrparen || t.value() == TOKlcurly) {
                        // invariant {}
                        // invariant() {}
//                        s = parseInvariant(pAttrs);
                        DeclDefParser.Invariant(builder, l + 1);
                    } else {
                        error(String.format("invariant body expected, not '%s'", token.toString()), builder);
                        while (token.value() != TOKsemicolon && token.value() != TOKeof) {
                            builder.advanceLexer();
                        }
                        builder.advanceLexer();
                        continue;
                    }
                    break;
                }
                case TOKunittest:
                    DeclDefParser.UnitTesting(builder, l + 1);
                    break;

                case TOKnew:
                    ExpressionParser.NewExpression(builder, l + 1);
                    break;

                case TOKdelete:
                    ExpressionParser.DeleteExpression(builder, l + 1);
                    break;

                case TOKcolon:
                case TOKlcurly:
                    error(String.format("declaration expected, not '%s'", token.toString()), builder);
                    //todo
                    while (token.value() != TOKsemicolon && token.value() != TOKeof) {
                        builder.advanceLexer();
                    }
                    builder.advanceLexer();
                    continue;

                case TOKrcurly:
                case TOKeof:
                    if (once > 1)
                        error(String.format("declaration expected, not '%s'", token.toString()), builder);
                    return true;

                case TOKstatic: {
                    Token next = peek(token);
                    if (next.value() == TOKthis)
                        DeclDefParser.StaticConstructor(builder, l + 1);
                    else if (next.value() == TOKtilde)
                        DeclDefParser.StaticDestructor(builder, l + 1);
                    else if (next.value() == TOKassert)
                        DeclDefParser.StaticAssert(builder, l + 1);
                    else if (next.value() == TOKif) {
//                        condition = parseStaticIfCondition();
//                        Dsymbols * athen;
                        final PsiBuilder.Marker staticIf = enter_section_(builder);
                        DeclDefParser.StaticIfCondition(builder, l + 2);
                        if (token.value() == TOKcolon) {
                            DeclarationParser.DeclarationBlock(builder, l + 2);
                        } else {
//                            const lookingForElseSave = lookingForElse;
//                            lookingForElse = token.loc;
//                            athen = parseBlock(pLastDecl);
//                            lookingForElse = lookingForElseSave;
                            DeclarationParser.DeclarationBlock(builder, l + 2);
                        }
                        if (token.value() == TOKelse) {
                            consumeToken(builder, DLanguageTypes.KW_ELSE);
                            DeclarationParser.DeclarationBlock(builder, l + 2);
//                            checkDanglingElse(elseloc); todo
                        }
                        exit_section_(builder, staticIf, DLanguageTypes.CONDITIONAL_DECLARATION, true);
                    } else if (next.value() == TOKimport) {
                        DeclDefParser.ImportDeclaration(builder, l + 1);
                        // keep pLastDecl
                    } else {
                        stc = STCstatic;
                        goto Lstc;
                    }
                    break;
                }
                case TOKconst:
                    if (peek(token) == TOKlparen) {
                        goto Ldeclaration;
                    }
                    stc = STCconst;
                goto Lstc;

                case TOKimmutable:
                    if (peekNext(builder).value() == TOKlparen) {
                        goto Ldeclaration;
                    }
                    stc = STCimmutable;
                goto Lstc;

                case TOKshared: {
                    Token next = peekNext(builder);
                    if (next.value() == TOKlparen) {
                        goto Ldeclaration;
                    }
                    if (next.value() == TOKstatic) {
                        Token next2 = peekNext2(builder);
                        if (next2.value() == TOKthis) {
                            s = parseSharedStaticCtor(pAttrs);
                            break;
                        }
                        if (next2.value() == TOKtilde) {
                            s = parseSharedStaticDtor(pAttrs);
                            break;
                        }
                    }
                    stc = STCshared;
                    goto Lstc;
                }
                case TOKwild:
                    if (peekNext(builder).value() == TOKlparen) {
                    goto Ldeclaration;
                    }
                    consumeSTC(builder);
                    break;

                case TOKfinal:
                    consumeSTC(builder);
                    break;


                case TOKauto:
                    consumeSTC(builder);
                    break;


                case TOKscope:
                    consumeSTC(builder);
                    break;


                case TOKoverride:
                    consumeSTC(builder);
                    break;


                case TOKabstract:
                    consumeSTC(builder);
                    break;


                case TOKsynchronized:
                    consumeSTC(builder);
                    break;


                case TOKnothrow:
                    consumeSTC(builder);
                    break;


                case TOKpure:
                    consumeSTC(builder);
                    break;


                case TOKref:
                    consumeSTC(builder);
                    break;


                case TOKgshared:
                    consumeSTC(builder);
                    break;


                //case TOKmanifest:   stc = STCmanifest;     goto Lstc;

                case TOKat: {
                    AttributesParser.AttributeSpecifier(builder, l + 1);                    goto Lautodecl;
                }
//                Lstc:
//                pAttrs.storageClass = appendStorageClass(pAttrs.storageClass, stc);
//                nextToken();

//                Lautodecl:

                /* Look for auto initializers:
                 *      storage_class identifier = initializer;
                 *      storage_class identifier(...) = initializer;
                 */
                if (token.value() == TOKidentifier && skipParensIf(peek(token)) != null && skipParensIf(peek(token)).value() == TOKassign) {
                    VariableDeclarationParser.AutoDeclaration(builder, l + 1);
                    break;
                }

                /* Look for return type inference for template functions.
                 */
                if (token.value() == TOKidentifier && skipParens(peek(token)) != null) {
                    Token tk = skipParens(peek(token));
                    if (skipAttributes(tk) != null) {
                        tk = skipAttributes(tk);
                        if (tk.value() == TOKlparen || tk.value() == TOKlcurly || tk.value() == TOKin || tk.value() == TOKout || tk.value() == TOKbody) {
                            parseDeclarations(builder, l + 1);
//                            if (pAttrs.udas) {
//                                s = new UserAttributeDeclaration(pAttrs.udas, a);
//                                pAttrs.udas = null;
//                            }
                            break;
                        }
                    }
                }

                a = parseBlock(pLastDecl, pAttrs);
                auto stc2 = getStorageClass(pAttrs);
                if (stc2 != STCundefined) {
                    s = new StorageClassDeclaration(stc2, a);
                }
                if (pAttrs.udas) {
                    if (s) {
                        a = new Dsymbols();
                        a.push(s);
                    }
                    s = new UserAttributeDeclaration(pAttrs.udas, a);
                    pAttrs.udas = null;
                }
                break;

                case TOKdeprecated: {
                    if (peek(token).value() != TOKlparen) {
                        stc = STCdeprecated;
                        goto Lstc;
                    }
                    nextToken();
                    check(TOKlparen);
                    Expression e = parseAssignExp();
                    check(TOKrparen);
                    if (pAttrs.depmsg) {
                        error("conflicting storage class 'deprecated(%s)' and 'deprecated(%s)'", pAttrs.depmsg.toChars(), e.toChars());
                    }
                    pAttrs.depmsg = e;
                    a = parseBlock(pLastDecl, pAttrs);
                    if (pAttrs.depmsg) {
                        s = new DeprecatedDeclaration(pAttrs.depmsg, a);
                        pAttrs.depmsg = null;
                    }
                    break;
                }
                case TOKlbracket: {
                    if (peekNext(builder).value() == TOKrbracket)
                        error("empty attribute list is not allowed", builder);
                    error("use @(attributes) instead of [attributes]", builder);
                    Expressions * exps = parseArguments();
                    // no redundant/conflicting check for UDAs

                    pAttrs.udas = UserAttributeDeclaration.concat(pAttrs.udas, exps);
                    a = parseBlock(pLastDecl, pAttrs);
                    if (pAttrs.udas) {
                        s = new UserAttributeDeclaration(pAttrs.udas, a);
                        pAttrs.udas = null;
                    }
                    break;
                }
                case TOKextern: {
                    if (peek(token).value() != TOKlparen) {
                        stc = STCextern;
                        goto Lstc;
                    }

                    const linkLoc = token.loc;
                    Identifiers * idents = null;
                    CPPMANGLE cppmangle;
                    const link = parseLinkage( & idents, cppmangle);
                    if (pAttrs.link != LINKdefault) {
                        if (pAttrs.link != link) {
                            error("conflicting linkage", builder);
                        } else if (idents) {
                            // Allow:
                            //      extern(C++, foo) extern(C++, bar) void foo();
                            // to be equivalent with:
                            //      extern(C++, foo.bar) void foo();
                        } else
                            error("redundant linkage", builder);
                    }
                    pAttrs.link = link;
                    this.linkage = link;
                    a = parseBlock(pLastDecl, pAttrs);
                    if (idents) {
                        assert (link == LINKcpp);
                        assert (idents.dim);
                        for (size_t i = idents.dim; i; ) {
                            Identifier id = ( * idents)[--i];
                            if (s) {
                                a = new Dsymbols();
                                a.push(s);
                            }
                            s = new Nspace(linkLoc, id, a);
                        }
                        pAttrs.link = LINKdefault;
                    } else if (cppmangle != CPPMANGLE.def) {
                        assert (link == LINKcpp);
                        s = new CPPMangleDeclaration(cppmangle, a);
                    } else if (pAttrs.link != LINKdefault) {
                        s = new LinkDeclaration(pAttrs.link, a);
                        pAttrs.link = LINKdefault;
                    }
                    break;
                }

                case TOKprivate:
                    prot = PROTprivate;
                goto Lprot;

                case TOKpackage:
                    prot = PROTpackage;
                goto Lprot;

                case TOKprotected:
                    prot = PROTprotected;
                goto Lprot;

                case TOKpublic:
                    prot = PROTpublic;
                goto Lprot;

                case TOKexport:
                    prot = PROTexport;
                goto Lprot;
                    Lprot:
                    {
                        if (pAttrs.protection.kind != PROTundefined) {
                            if (pAttrs.protection.kind != prot)
                                error("conflicting protection attribute '%s' and '%s'", protectionToChars(pAttrs.protection.kind), protectionToChars(prot));
                            else
                                error("redundant protection attribute '%s'", protectionToChars(prot));
                        }
                        pAttrs.protection.kind = prot;

                        nextToken();

                        // optional qualified package identifier to bind
                        // protection to
                        Identifiers * pkg_prot_idents = null;
                        if (pAttrs.protection.kind == PROTpackage && token.value() == TOKlparen) {
                            pkg_prot_idents = parseQualifiedIdentifier("protection package");
                            if (pkg_prot_idents)
                                check(TOKrparen);
                            else {
                                while (token.value() != TOKsemicolon && token.value() != TOKeof)
                                    nextToken();
                                nextToken();
                                break;
                            }
                        }

                    const attrloc = token.loc;
                        a = parseBlock(pLastDecl, pAttrs);
                        if (pAttrs.protection.kind != PROTundefined) {
                            if (pAttrs.protection.kind == PROTpackage && pkg_prot_idents)
                                s = new ProtDeclaration(attrloc, pkg_prot_idents, a);
                            else
                                s = new ProtDeclaration(attrloc, pAttrs.protection, a);

                            pAttrs.protection = Prot(PROTundefined);
                        }
                        break;
                    }
                case TOKalign: {
                    const attrLoc = token.loc;

                    nextToken();

                    Expression e = null; // default
                    if (token.value() == TOKlparen) {
                        nextToken();
                        e = parseAssignExp();
                        check(TOKrparen);
                    }

                    if (pAttrs.setAlignment) {
                        if (e)
                            error("redundant alignment attribute", builder);
                        else
                            error("redundant alignment attribute align", builder);
                    }

                    pAttrs.setAlignment = true;
                    pAttrs.ealign = e;
                    a = parseBlock(pLastDecl, pAttrs);
                    if (pAttrs.setAlignment) {
                        s = new AlignDeclaration(attrLoc, pAttrs.ealign, a);
                        pAttrs.setAlignment = false;
                        pAttrs.ealign = null;
                    }
                    break;
                }
                case TOKpragma: {
                    Expressions * args = null;
                    const loc = token.loc;

                    nextToken();
                    check(TOKlparen);
                    if (token.value() != TOKidentifier) {
                        error("pragma(identifier) expected", builder);
                        //todo if you change this change it elsewhere:
                        while (token.value() != TOKsemicolon && token.value() != TOKeof) {
                            builder.advanceLexer();
                        }
                        builder.advanceLexer();
                        continue;
                    }
                    Identifier ident = token.ident;
                    nextToken();
                    if (token.value() == TOKcomma && peekNext() != TOKrparen)
                        args = parseArguments(); // pragma(identifier, args...)
                    else
                        check(TOKrparen); // pragma(identifier)

                    Dsymbols * a2 = null;
                    if (token.value() == TOKsemicolon) {
                        /* Bugzilla 2354: Accept single semicolon as an empty
                         * DeclarationBlock following attribute.
                         *
                         * Attribute DeclarationBlock
                         * Pragma    DeclDef
                         *           ;
                         */
                        nextToken();
                    } else
                        a2 = parseBlock(pLastDecl);
                    s = new PragmaDeclaration(loc, ident, args, a2);
                    break;
                }
                case TOKdebug:
                    nextToken();
                    if (token.value() == TOKassign) {
                        nextToken();
                        if (token.value() == TOKidentifier)
                            s = new DebugSymbol(token.loc, token.ident);
                        else if (token.value() == TOKint32v || token.value() == TOKint64v)
                            s = new DebugSymbol(token.loc, cast(uint)token.uns64value);
                        else {
                            error("identifier or integer expected, not %s", token.toChars());
                            s = null;
                        }
                        nextToken();
                        if (token.value() != TOKsemicolon)
                            error("semicolon expected", builder);
                        nextToken();
                        break;
                    }

                    condition = parseDebugCondition();
                goto Lcondition;

                case TOKversion:
                    nextToken();
                    if (token.value() == TOKassign) {
                        nextToken();
                        if (token.value() == TOKidentifier)
                            s = new VersionSymbol(token.loc, token.ident);
                        else if (token.value() == TOKint32v || token.value() == TOKint64v)
                            s = new VersionSymbol(token.loc, cast(uint)token.uns64value);
                        else {
                            error("identifier or integer expected, not %s", token.toChars());
                            s = null;
                        }
                        nextToken();
                        if (token.value() != TOKsemicolon)
                            error("semicolon expected", builder);
                        nextToken();
                        break;
                    }
                    condition = parseVersionCondition();
                goto Lcondition;

                    Lcondition:
                    {
                        Dsymbols * athen;
                        if (token.value() == TOKcolon)
                            athen = parseBlock(pLastDecl);
                        else {
                        const lookingForElseSave = lookingForElse;
                            lookingForElse = token.loc;
                            athen = parseBlock(pLastDecl);
                            lookingForElse = lookingForElseSave;
                        }
                        Dsymbols * aelse = null;
                        if (token.value() == TOKelse) {
                        const elseloc = token.loc;
                            nextToken();
                            aelse = parseBlock(pLastDecl);
                            checkDanglingElse(elseloc);
                        }
                        s = new ConditionalDeclaration(condition, athen, aelse);
                        break;
                    }
                case TOKsemicolon:
                    // empty declaration
                    //error("empty declaration");
                    consumeToken(builder, DLanguageTypes.OP_SCOLON);
                    continue;

                default:
                    error(String.format("declaration expected, not '%s'", token.toString()), builder);
//                    Lerror:
//                    while (token.value() != TOKsemicolon && token.value() != TOKeof) {
//                        builder.advanceLexer();
//                    }
//                    builder.advanceLexer();
//                    continue;
            }

            if (s) {
                if (!s.isAttribDeclaration())
                    *pLastDecl = s;
                decldefs.push(s);
                addComment(s, pAttrs.comment);
            } else if (a && a.dim) {
                decldefs.append(a);
            }
        }
        while (!once);

        linkage = linksave;

        return decldefs;
    }

    private static boolean consumeSTC(PsiBuilder builder) {
        final PsiBuilder.Marker stcMarker = enter_section_(builder);
        builder.advanceLexer();
        exit_section_(builder, stcMarker, DLanguageTypes.STORAGE_CLASS, true);
        return true;
    }

    private static boolean parseDeclarations(PsiBuilder builder, int l) {
        if (!recursion_guard_(builder, l, "parseDeclarations")) return false;
        int c = current_position_(builder);
        while (true) {
            if (!DeclarationParser.Declaration(builder, l + 1)) break;
            if (!empty_element_parsed_guard_(builder, "parseDeclarations", c)) break;
            c = current_position_(builder);
        }
        return true;
    }
}

}
