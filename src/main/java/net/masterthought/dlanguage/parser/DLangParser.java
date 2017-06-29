package net.masterthought.dlanguage.parser;

import com.intellij.lang.ASTNode;
import com.intellij.lang.LightPsiParser;
import com.intellij.lang.PsiBuilder;
import com.intellij.lang.PsiParser;
import com.intellij.psi.tree.IElementType;
import net.masterthought.dlanguage.psi.DLanguageTypes;
import org.jetbrains.annotations.NotNull;

import static com.intellij.lang.parser.GeneratedParserUtilBase.*;
import static net.masterthought.dlanguage.parser.DLanguageParser.item_recover_parser_;
import static net.masterthought.dlanguage.parser.SkipUtil.skipAttributes;
import static net.masterthought.dlanguage.parser.TokenConstants.TOKmodule;
import static net.masterthought.dlanguage.psi.DLanguageTypes.*;

/**
 * Created by francis on 6/24/2017.
 */
public class DLangParser implements PsiParser, LightPsiParser {

    /**
     * if (udas)
     * {
     * auto a = new Dsymbols();
     * auto udad = new UserAttributeDeclaration(udas, a);
     * mod.userAttribDecl = udad;
     * }
     * <p>
     * // ModuleDeclation leads off
     * if (token.value == TOKmodule)
     * {
     * const loc = token.loc;
     * <p>
     * nextToken();
     * if (token.value != TOKidentifier)
     * {
     * error("identifier expected following module");
     * goto Lerr;
     * }
     * else
     * {
     * Identifiers* a = null;
     * Identifier id = token.ident;
     * <p>
     * while (nextToken() == TOKdot)
     * {
     * if (!a)
     * a = new Identifiers();
     * a.push(id);
     * nextToken();
     * if (token.value != TOKidentifier)
     * {
     * error("identifier expected following package");
     * goto Lerr;
     * }
     * id = token.ident;
     * }
     * <p>
     * md = new ModuleDeclaration(loc, a, id, msg, isdeprecated);
     * <p>
     * if (token.value != TOKsemicolon)
     * error("';' expected following module declaration instead of %s", token.toChars());
     * nextToken();
     * addComment(mod, comment);
     * }
     * }
     * <p>
     * decldefs = parseDeclDefs(0, &lastDecl);
     * if (token.value != TOKeof)
     * {
     * error(token.loc, "unrecognized declaration");
     * goto Lerr;
     * }
     * return decldefs;
     * <p>
     * Lerr:
     * while (token.value != TOKsemicolon && token.value != TOKeof)
     * nextToken();
     * nextToken();
     * return new Dsymbols();
     * }
     * <p>
     * Dsymbols* parseDeclDefs(int once, Dsymbol* pLastDecl = null, PrefixAttributes* pAttrs = null)
     * {
     * Dsymbol lastDecl = null; // used to link unittest to its previous declaration
     * if (!pLastDecl)
     * pLastDecl = &lastDecl;
     * <p>
     * const linksave = linkage; // save global state
     * <p>
     * //printf("Parser::parseDeclDefs()\n");
     * auto decldefs = new Dsymbols();
     * do
     * {
     * // parse result
     * Dsymbol s = null;
     * Dsymbols* a = null;
     * <p>
     * PrefixAttributes attrs;
     * if (!once || !pAttrs)
     * {
     * pAttrs = &attrs;
     * pAttrs.comment = token.blockComment;
     * }
     * PROTKIND prot;
     * StorageClass stc;
     * Condition condition;
     * <p>
     * linkage = linksave;
     * <p>
     * switch (token.value)
     * {
     * case TOKenum:
     * {
     * /* Determine if this is a manifest constant declaration,
     * or a conventional enum.
     * +/
     * Token* t = peek(&token);
     * if (t.value == TOKlcurly || t.value == TOKcolon)
     * s = parseEnum();
     * else if (t.value != TOKidentifier)
     * goto Ldeclaration;
     * else
     * {
     * t = peek(t);
     * if (t.value == TOKlcurly || t.value == TOKcolon || t.value == TOKsemicolon)
     * s = parseEnum();
     * else
     * goto Ldeclaration;
     * }
     * break;
     * }
     * case TOKimport:
     * a = parseImport();
     * // keep pLastDecl
     * break;
     * <p>
     * case TOKtemplate:
     * s = cast(Dsymbol)parseTemplateDeclaration();
     * break;
     * <p>
     * case TOKmixin:
     * {
     * const loc = token.loc;
     * switch (peekNext())
     * {
     * case TOKlparen:
     * {
     * // mixin(string)
     * nextToken();
     * check(TOKlparen, "mixin");
     * Expression e = parseAssignExp();
     * check(TOKrparen);
     * check(TOKsemicolon);
     * s = new CompileDeclaration(loc, e);
     * break;
     * }
     * case TOKtemplate:
     * // mixin template
     * nextToken();
     * s = cast(Dsymbol)parseTemplateDeclaration(true);
     * break;
     * <p>
     * default:
     * s = parseMixin();
     * break;
     * }
     * break;
     * }
     * case TOKwchar:
     * case TOKdchar:
     * case TOKbool:
     * case TOKchar:
     * case TOKint8:
     * case TOKuns8:
     * case TOKint16:
     * case TOKuns16:
     * case TOKint32:
     * case TOKuns32:
     * case TOKint64:
     * case TOKuns64:
     * case TOKint128:
     * case TOKuns128:
     * case TOKfloat32:
     * case TOKfloat64:
     * case TOKfloat80:
     * case TOKimaginary32:
     * case TOKimaginary64:
     * case TOKimaginary80:
     * case TOKcomplex32:
     * case TOKcomplex64:
     * case TOKcomplex80:
     * case TOKvoid:
     * case TOKalias:
     * case TOKidentifier:
     * case TOKsuper:
     * case TOKtypeof:
     * case TOKdot:
     * case TOKvector:
     * case TOKstruct:
     * case TOKunion:
     * case TOKclass:
     * case TOKinterface:
     * Ldeclaration:
     * a = parseDeclarations(false, pAttrs, pAttrs.comment);
     * if (a && a.dim)
     * pLastDecl = (*a)[a.dim - 1];
     * break;
     * <p>
     * case TOKthis:
     * if (peekNext() == TOKdot)
     * goto Ldeclaration;
     * else
     * s = parseCtor(pAttrs);
     * break;
     * <p>
     * case TOKtilde:
     * s = parseDtor(pAttrs);
     * break;
     * <p>
     * case TOKinvariant:
     * {
     * Token* t = peek(&token);
     * if (t.value == TOKlparen && peek(t).value == TOKrparen || t.value == TOKlcurly)
     * {
     * // invariant {}
     * // invariant() {}
     * s = parseInvariant(pAttrs);
     * }
     * else
     * {
     * error("invariant body expected, not '%s'", token.toChars());
     * goto Lerror;
     * }
     * break;
     * }
     * case TOKunittest:
     * if (global.params.useUnitTests || global.params.doDocComments || global.params.doHdrGeneration)
     * {
     * s = parseUnitTest(pAttrs);
     * if (*pLastDecl)
     * (*pLastDecl).ddocUnittest = cast(UnitTestDeclaration)s;
     * }
     * else
     * {
     * // Skip over unittest block by counting { }
     * Loc loc = token.loc;
     * int braces = 0;
     * while (1)
     * {
     * nextToken();
     * switch (token.value)
     * {
     * case TOKlcurly:
     * ++braces;
     * continue;
     * <p>
     * case TOKrcurly:
     * if (--braces)
     * continue;
     * nextToken();
     * break;
     * <p>
     * case TOKeof:
     * /* { +/
     * error(loc, "closing } of unittest not found before end of file");
     * goto Lerror;
     * <p>
     * default:
     * continue;
     * }
     * break;
     * }
     * // Workaround 14894. Add an empty unittest declaration to keep
     * // the number of symbols in this scope independent of -unittest.
     * s = new UnitTestDeclaration(loc, token.loc, STCundefined, null);
     * }
     * break;
     * <p>
     * case TOKnew:
     * s = parseNew(pAttrs);
     * break;
     * <p>
     * case TOKdelete:
     * s = parseDelete(pAttrs);
     * break;
     * <p>
     * case TOKcolon:
     * case TOKlcurly:
     * error("declaration expected, not '%s'", token.toChars());
     * goto Lerror;
     * <p>
     * case TOKrcurly:
     * case TOKeof:
     * if (once)
     * error("declaration expected, not '%s'", token.toChars());
     * return decldefs;
     * <p>
     * case TOKstatic:
     * {
     * const next = peekNext();
     * if (next == TOKthis)
     * s = parseStaticCtor(pAttrs);
     * else if (next == TOKtilde)
     * s = parseStaticDtor(pAttrs);
     * else if (next == TOKassert)
     * s = parseStaticAssert();
     * else if (next == TOKif)
     * {
     * condition = parseStaticIfCondition();
     * Dsymbols* athen;
     * if (token.value == TOKcolon)
     * athen = parseBlock(pLastDecl);
     * else
     * {
     * const lookingForElseSave = lookingForElse;
     * lookingForElse = token.loc;
     * athen = parseBlock(pLastDecl);
     * lookingForElse = lookingForElseSave;
     * }
     * Dsymbols* aelse = null;
     * if (token.value == TOKelse)
     * {
     * const elseloc = token.loc;
     * nextToken();
     * aelse = parseBlock(pLastDecl);
     * checkDanglingElse(elseloc);
     * }
     * s = new StaticIfDeclaration(condition, athen, aelse);
     * }
     * else if (next == TOKimport)
     * {
     * a = parseImport();
     * // keep pLastDecl
     * }
     * else
     * {
     * stc = STCstatic;
     * goto Lstc;
     * }
     * break;
     * }
     * case TOKconst:
     * if (peekNext() == TOKlparen)
     * goto Ldeclaration;
     * stc = STCconst;
     * goto Lstc;
     * <p>
     * case TOKimmutable:
     * if (peekNext() == TOKlparen)
     * goto Ldeclaration;
     * stc = STCimmutable;
     * goto Lstc;
     * <p>
     * case TOKshared:
     * {
     * const next = peekNext();
     * if (next == TOKlparen)
     * goto Ldeclaration;
     * if (next == TOKstatic)
     * {
     * TOK next2 = peekNext2();
     * if (next2 == TOKthis)
     * {
     * s = parseSharedStaticCtor(pAttrs);
     * break;
     * }
     * if (next2 == TOKtilde)
     * {
     * s = parseSharedStaticDtor(pAttrs);
     * break;
     * }
     * }
     * stc = STCshared;
     * goto Lstc;
     * }
     * case TOKwild:
     * if (peekNext() == TOKlparen)
     * goto Ldeclaration;
     * stc = STCwild;
     * goto Lstc;
     * <p>
     * case TOKfinal:
     * stc = STCfinal;
     * goto Lstc;
     * <p>
     * case TOKauto:
     * stc = STCauto;
     * goto Lstc;
     * <p>
     * case TOKscope:
     * stc = STCscope;
     * goto Lstc;
     * <p>
     * case TOKoverride:
     * stc = STCoverride;
     * goto Lstc;
     * <p>
     * case TOKabstract:
     * stc = STCabstract;
     * goto Lstc;
     * <p>
     * case TOKsynchronized:
     * stc = STCsynchronized;
     * goto Lstc;
     * <p>
     * case TOKnothrow:
     * stc = STCnothrow;
     * goto Lstc;
     * <p>
     * case TOKpure:
     * stc = STCpure;
     * goto Lstc;
     * <p>
     * case TOKref:
     * stc = STCref;
     * goto Lstc;
     * <p>
     * case TOKgshared:
     * stc = STCgshared;
     * goto Lstc;
     * <p>
     * //case TOKmanifest:   stc = STCmanifest;     goto Lstc;
     * <p>
     * case TOKat:
     * {
     * Expressions* exps = null;
     * stc = parseAttribute(&exps);
     * if (stc)
     * goto Lstc; // it's a predefined attribute
     * // no redundant/conflicting check for UDAs
     * pAttrs.udas = UserAttributeDeclaration.concat(pAttrs.udas, exps);
     * goto Lautodecl;
     * }
     * Lstc:
     * pAttrs.storageClass = appendStorageClass(pAttrs.storageClass, stc);
     * nextToken();
     * <p>
     * Lautodecl:
     * Token* tk;
     * <p>
     * /* Look for auto initializers:
     * storage_class identifier = initializer;
     * storage_class identifier(...) = initializer;
     * +/
     * if (token.value == TOKidentifier && skipParensIf(peek(&token), &tk) && tk.value == TOKassign)
     * {
     * a = parseAutoDeclarations(getStorageClass(pAttrs), pAttrs.comment);
     * if (a && a.dim)
     * pLastDecl = (*a)[a.dim - 1];
     * if (pAttrs.udas)
     * {
     * s = new UserAttributeDeclaration(pAttrs.udas, a);
     * pAttrs.udas = null;
     * }
     * break;
     * }
     * <p>
     * /* Look for return type inference for template functions.
     * +/
     * if (token.value == TOKidentifier && skipParens(peek(&token), &tk) && skipAttributes(tk, &tk) && (tk.value == TOKlparen || tk.value == TOKlcurly || tk.value == TOKin || tk.value == TOKout || tk.value == TOKbody))
     * {
     * a = parseDeclarations(true, pAttrs, pAttrs.comment);
     * if (a && a.dim)
     * pLastDecl = (*a)[a.dim - 1];
     * if (pAttrs.udas)
     * {
     * s = new UserAttributeDeclaration(pAttrs.udas, a);
     * pAttrs.udas = null;
     * }
     * break;
     * }
     * <p>
     * a = parseBlock(pLastDecl, pAttrs);
     * auto stc2 = getStorageClass(pAttrs);
     * if (stc2 != STCundefined)
     * {
     * s = new StorageClassDeclaration(stc2, a);
     * }
     * if (pAttrs.udas)
     * {
     * if (s)
     * {
     * a = new Dsymbols();
     * a.push(s);
     * }
     * s = new UserAttributeDeclaration(pAttrs.udas, a);
     * pAttrs.udas = null;
     * }
     * break;
     * <p>
     * case TOKdeprecated:
     * {
     * if (peek(&token).value != TOKlparen)
     * {
     * stc = STCdeprecated;
     * goto Lstc;
     * }
     * nextToken();
     * check(TOKlparen);
     * Expression e = parseAssignExp();
     * check(TOKrparen);
     * if (pAttrs.depmsg)
     * {
     * error("conflicting storage class 'deprecated(%s)' and 'deprecated(%s)'", pAttrs.depmsg.toChars(), e.toChars());
     * }
     * pAttrs.depmsg = e;
     * a = parseBlock(pLastDecl, pAttrs);
     * if (pAttrs.depmsg)
     * {
     * s = new DeprecatedDeclaration(pAttrs.depmsg, a);
     * pAttrs.depmsg = null;
     * }
     * break;
     * }
     * case TOKlbracket:
     * {
     * if (peekNext() == TOKrbracket)
     * error("empty attribute list is not allowed");
     * error("use @(attributes) instead of [attributes]");
     * Expressions* exps = parseArguments();
     * // no redundant/conflicting check for UDAs
     * <p>
     * pAttrs.udas = UserAttributeDeclaration.concat(pAttrs.udas, exps);
     * a = parseBlock(pLastDecl, pAttrs);
     * if (pAttrs.udas)
     * {
     * s = new UserAttributeDeclaration(pAttrs.udas, a);
     * pAttrs.udas = null;
     * }
     * break;
     * }
     * case TOKextern:
     * {
     * if (peek(&token).value != TOKlparen)
     * {
     * stc = STCextern;
     * goto Lstc;
     * }
     * <p>
     * const linkLoc = token.loc;
     * Identifiers* idents = null;
     * CPPMANGLE cppmangle;
     * const link = parseLinkage(&idents, cppmangle);
     * if (pAttrs.link != LINKdefault)
     * {
     * if (pAttrs.link != link)
     * {
     * error("conflicting linkage extern (%s) and extern (%s)", linkageToChars(pAttrs.link), linkageToChars(link));
     * }
     * else if (idents)
     * {
     * // Allow:
     * //      extern(C++, foo) extern(C++, bar) void foo();
     * // to be equivalent with:
     * //      extern(C++, foo.bar) void foo();
     * }
     * else
     * error("redundant linkage extern (%s)", linkageToChars(pAttrs.link));
     * }
     * pAttrs.link = link;
     * this.linkage = link;
     * a = parseBlock(pLastDecl, pAttrs);
     * if (idents)
     * {
     * assert(link == LINKcpp);
     * assert(idents.dim);
     * for (size_t i = idents.dim; i;)
     * {
     * Identifier id = (*idents)[--i];
     * if (s)
     * {
     * a = new Dsymbols();
     * a.push(s);
     * }
     * s = new Nspace(linkLoc, id, a);
     * }
     * pAttrs.link = LINKdefault;
     * }
     * else if (cppmangle != CPPMANGLE.def)
     * {
     * assert(link == LINKcpp);
     * s = new CPPMangleDeclaration(cppmangle, a);
     * }
     * else if (pAttrs.link != LINKdefault)
     * {
     * s = new LinkDeclaration(pAttrs.link, a);
     * pAttrs.link = LINKdefault;
     * }
     * break;
     * }
     * <p>
     * case TOKprivate:
     * prot = PROTprivate;
     * goto Lprot;
     * <p>
     * case TOKpackage:
     * prot = PROTpackage;
     * goto Lprot;
     * <p>
     * case TOKprotected:
     * prot = PROTprotected;
     * goto Lprot;
     * <p>
     * case TOKpublic:
     * prot = PROTpublic;
     * goto Lprot;
     * <p>
     * case TOKexport:
     * prot = PROTexport;
     * goto Lprot;
     * Lprot:
     * {
     * if (pAttrs.protection.kind != PROTundefined)
     * {
     * if (pAttrs.protection.kind != prot)
     * error("conflicting protection attribute '%s' and '%s'", protectionToChars(pAttrs.protection.kind), protectionToChars(prot));
     * else
     * error("redundant protection attribute '%s'", protectionToChars(prot));
     * }
     * pAttrs.protection.kind = prot;
     * <p>
     * nextToken();
     * <p>
     * // optional qualified package identifier to bind
     * // protection to
     * Identifiers* pkg_prot_idents = null;
     * if (pAttrs.protection.kind == PROTpackage && token.value == TOKlparen)
     * {
     * pkg_prot_idents = parseQualifiedIdentifier("protection package");
     * if (pkg_prot_idents)
     * check(TOKrparen);
     * else
     * {
     * while (token.value != TOKsemicolon && token.value != TOKeof)
     * nextToken();
     * nextToken();
     * break;
     * }
     * }
     * <p>
     * const attrloc = token.loc;
     * a = parseBlock(pLastDecl, pAttrs);
     * if (pAttrs.protection.kind != PROTundefined)
     * {
     * if (pAttrs.protection.kind == PROTpackage && pkg_prot_idents)
     * s = new ProtDeclaration(attrloc, pkg_prot_idents, a);
     * else
     * s = new ProtDeclaration(attrloc, pAttrs.protection, a);
     * <p>
     * pAttrs.protection = Prot(PROTundefined);
     * }
     * break;
     * }
     * case TOKalign:
     * {
     * const attrLoc = token.loc;
     * <p>
     * nextToken();
     * <p>
     * Expression e = null; // default
     * if (token.value == TOKlparen)
     * {
     * nextToken();
     * e = parseAssignExp();
     * check(TOKrparen);
     * }
     * <p>
     * if (pAttrs.setAlignment)
     * {
     * if (e)
     * error("redundant alignment attribute align(%s)", e.toChars());
     * else
     * error("redundant alignment attribute align");
     * }
     * <p>
     * pAttrs.setAlignment = true;
     * pAttrs.ealign = e;
     * a = parseBlock(pLastDecl, pAttrs);
     * if (pAttrs.setAlignment)
     * {
     * s = new AlignDeclaration(attrLoc, pAttrs.ealign, a);
     * pAttrs.setAlignment = false;
     * pAttrs.ealign = null;
     * }
     * break;
     * }
     * case TOKpragma:
     * {
     * Expressions* args = null;
     * const loc = token.loc;
     * <p>
     * nextToken();
     * check(TOKlparen);
     * if (token.value != TOKidentifier)
     * {
     * error("pragma(identifier) expected");
     * goto Lerror;
     * }
     * Identifier ident = token.ident;
     * nextToken();
     * if (token.value == TOKcomma && peekNext() != TOKrparen)
     * args = parseArguments(); // pragma(identifier, args...)
     * else
     * check(TOKrparen); // pragma(identifier)
     * <p>
     * Dsymbols* a2 = null;
     * if (token.value == TOKsemicolon)
     * {
     * /* Bugzilla 2354: Accept single semicolon as an empty
     * DeclarationBlock following attribute.
     * <p>
     * Attribute DeclarationBlock
     * Pragma    DeclDef
     * ;
     * +/
     * nextToken();
     * }
     * else
     * a2 = parseBlock(pLastDecl);
     * s = new PragmaDeclaration(loc, ident, args, a2);
     * break;
     * }
     * case TOKdebug:
     * nextToken();
     * if (token.value == TOKassign)
     * {
     * nextToken();
     * if (token.value == TOKidentifier)
     * s = new DebugSymbol(token.loc, token.ident);
     * else if (token.value == TOKint32v || token.value == TOKint64v)
     * s = new DebugSymbol(token.loc, cast(uint)token.uns64value);
     * else
     * {
     * error("identifier or integer expected, not %s", token.toChars());
     * s = null;
     * }
     * nextToken();
     * if (token.value != TOKsemicolon)
     * error("semicolon expected");
     * nextToken();
     * break;
     * }
     * <p>
     * condition = parseDebugCondition();
     * goto Lcondition;
     * <p>
     * case TOKversion:
     * nextToken();
     * if (token.value == TOKassign)
     * {
     * nextToken();
     * if (token.value == TOKidentifier)
     * s = new VersionSymbol(token.loc, token.ident);
     * else if (token.value == TOKint32v || token.value == TOKint64v)
     * s = new VersionSymbol(token.loc, cast(uint)token.uns64value);
     * else
     * {
     * error("identifier or integer expected, not %s", token.toChars());
     * s = null;
     * }
     * nextToken();
     * if (token.value != TOKsemicolon)
     * error("semicolon expected");
     * nextToken();
     * break;
     * }
     * condition = parseVersionCondition();
     * goto Lcondition;
     * <p>
     * Lcondition:
     * {
     * Dsymbols* athen;
     * if (token.value == TOKcolon)
     * athen = parseBlock(pLastDecl);
     * else
     * {
     * const lookingForElseSave = lookingForElse;
     * lookingForElse = token.loc;
     * athen = parseBlock(pLastDecl);
     * lookingForElse = lookingForElseSave;
     * }
     * Dsymbols* aelse = null;
     * if (token.value == TOKelse)
     * {
     * const elseloc = token.loc;
     * nextToken();
     * aelse = parseBlock(pLastDecl);
     * checkDanglingElse(elseloc);
     * }
     * s = new ConditionalDeclaration(condition, athen, aelse);
     * break;
     * }
     * case TOKsemicolon:
     * // empty declaration
     * //error("empty declaration");
     * nextToken();
     * continue;
     * <p>
     * default:
     * error("declaration expected, not '%s'", token.toChars());
     * Lerror:
     * while (token.value != TOKsemicolon && token.value != TOKeof)
     * nextToken();
     * nextToken();
     * s = null;
     * continue;
     * }
     * <p>
     * if (s)
     * {
     * if (!s.isAttribDeclaration())
     * pLastDecl = s;
     * decldefs.push(s);
     * addComment(s, pAttrs.comment);
     * }
     * else if (a && a.dim)
     * {
     * decldefs.append(a);
     * }
     * }
     * while (!once);
     * <p>
     * linkage = linksave;
     * <p>
     * return decldefs;
     * }
     */
    static boolean dFile(PsiBuilder builder, int l) {
        builder.setDebugMode(true);
        //if (!recursion_guard_(builder, l, "dFile")) return false;//I don't think a recursion guard for a file is necessary
        boolean isdeprecated = false;
        boolean udas = false;
        //Expression msg = null;
        //Expressions* udas = null;
        //List<> decldefs;
//        Dsymbol lastDecl = mod;

        if (skipAttributes(new Token(builder)) != null) {
            final Token token = skipAttributes(new Token(builder));
            if (token.value() == TOKmodule) {
                final PsiBuilder.Marker modulePrefix = enter_section_(builder);
                while (true) {
                    if (nextTokenIs(builder, KW_DEPRECATED)) {
                        final PsiBuilder.Marker deprecatedMarker = enter_section_(builder);
                        // deprecated (...) module ...
                        if (isdeprecated) {
                            MiscParser.error("there is only one deprecation attribute allowed for module declaration", builder);
                        } else {
                            isdeprecated = true;
                        }
                        consumeToken(builder, KW_DEPRECATED);
                        if (nextTokenIs(builder, OP_PAR_LEFT)) {
                            if (!consumeToken(builder, OP_PAR_LEFT)) {
                                MiscParser.error("you should never see this", builder);
                            }
                            if (!ExpressionParser.AssignExpression(builder, l)) {
                                MiscParser.error("expression expected", builder);
                            }
                            if (!consumeToken(builder, OP_PAR_RIGHT)) {
                                MiscParser.error("unclosed deprecated statement", builder);
                            }
                        }
                        exit_section_(builder, deprecatedMarker, DLanguageTypes.DEPRECATED_ATTRIBUTE, true);
                    } else if (nextTokenIs(builder, OP_AT)) {
                        if (!AttributesParser.Attribute(builder, l + 1)) {
                            MiscParser.error("attribute for module declaration is not supported", builder);//todo this message could be better
                        }
                        //                final PsiBuilder.Marker userAttribute = enter_section_(builder);
                        //                consumeToken(builder, OP_AT);
                        //                //todo parse attribute instead of @
                        //                //todo add missing tokens
                        //                if (nextTokenIs(builder, "property") || nextTokenIs(builder, "nogc") || nextTokenIs(builder, "disable") || nextTokenIs(builder, "safe") || nextTokenIs(builder, "trusted") || nextTokenIs(builder, "system")) {
                        //                    error("attribute for module declaration is not supported", builder);
                        //                }
                        //                exit_section_(builder, userAttribute, DLanguageTypes.ATTRIBUTE, true);
                    } else if (nextTokenIs(builder, KW_MODULE)) {
                        exit_section_(builder, modulePrefix, DECL_DEF, true);
                        break;
                    } else {
                        MiscParser.error("'module' expected", builder);
                        exit_section_(builder, modulePrefix, DECL_DEF, true);
                        break;
                    }
                }
            }
        }

//        assert (nextTokenIs(builder, KW_MODULE));
        if (nextTokenIs(builder, KW_MODULE)) {
            final PsiBuilder.Marker moduleDeclaration = enter_section_(builder);
            consumeToken(builder, KW_MODULE);
            final PsiBuilder.Marker moduleName = enter_section_(builder);
            if (!nextTokenIs(builder, ID)) {
                MiscParser.error("identifier expected following module", builder);
                //goto error
            } else {
                BaseRulesParser.Identifier(builder, l + 3);

                while (nextTokenIs(builder, OP_DOT)) {
                    consumeToken(builder, OP_DOT);
                    if (!BaseRulesParser.Identifier(builder, l + 3)) {
                        MiscParser.error("identifier expected following package", builder);
//                        goto Lerr;
                    }
                }

                exit_section_(builder, moduleName, MODULE_FULLY_QUALIFIED_NAME, true);

                if (!nextTokenIs(builder, OP_SCOLON))
                    MiscParser.error("';' expected following module declaration instead of %s", builder);
                consumeToken(builder, OP_SCOLON);
                exit_section_(builder, moduleDeclaration, MODULE_DECLARATION, true);
            }
        }
        return DeclDefParser.parseDeclDefs(builder, l + 1);
        //todo do this with better error handling:
//        if (token.value != TOKeof)
//        {
//            error(token.loc, "unrecognized declaration");
//            goto Lerr;
//        }
//        return decldefs;

    }

    /* ********************************************************** */


    /**
     * @param b the psibuilder in question
     * @param l current recursion depth
     * @return bool if match
     * Monster sized dmd code for this:
     */

    /*
Dsymbols* parseModule()
    {
        const comment = token.blockComment;
        bool isdeprecated = false;
        Expression msg = null;
        Expressions* udas = null;
        Dsymbols* decldefs;
        Dsymbol lastDecl = mod; // for attaching ddoc unittests to module decl

        Token* tk;
        if (skipAttributes(&token, &tk) && tk.value == TOKmodule)
        {
            while (token.value != TOKmodule)
            {
                switch (token.value)
                {
                case TOKdeprecated:
                    {
                        // deprecated (...) module ...
                        if (isdeprecated)
                        {
                            error("there is only one deprecation attribute allowed for module declaration");
                        }
                        else
                        {
                            isdeprecated = true;
                        }
                        nextToken();
                        if (token.value == TOKlparen)
                        {
                            check(TOKlparen);
                            msg = parseAssignExp();
                            check(TOKrparen);
                        }
                        break;
                    }
                case TOKat:
                    {
                        Expressions* exps = null;
                        const stc = parseAttribute(&exps);
                        if (stc == STCproperty || stc == STCnogc || stc == STCdisable || stc == STCsafe || stc == STCtrusted || stc == STCsystem)
                        {
                            error("@%s attribute for module declaration is not supported", token.toChars());
                        }
                        else
                        {
                            udas = UserAttributeDeclaration.concat(udas, exps);
                        }
                        if (stc)
                            nextToken();
                        break;
                    }
                default:
                    {
                        error("'module' expected instead of %s", token.toChars());
                        nextToken();
                        break;
                    }
                }
            }
        }

    */
    static boolean item_(PsiBuilder b, int l) {
        if (!recursion_guard_(b, l, "item_")) return false;
        boolean r;
        PsiBuilder.Marker m = enter_section_(b, l, _NONE_);
        r = DeclDefParser.ModuleDeclaration(b, l + 1);
        if (!r) r = DeclDefParser.parseDeclDefs(b, l + 1);
        if (!r) r = StatementParser.Statement(b, l + 1);
        if (!r) r = consumeToken(b, SHEBANG);
        exit_section_(b, l, m, r, false, item_recover_parser_);
        return r;
    }

    /* ********************************************************** */
    // ModuleDeclaration | DeclDefs | Statement | SHEBANG


    @Override
    public void parseLight(IElementType root, PsiBuilder builder) {
        boolean r;
        PsiBuilder b = adapt_builder_(root, builder, this, null);
        b.setDebugMode(true);
        PsiBuilder.Marker m = enter_section_(b, 0, _COLLAPSE_, null);
        if (root == ADD_EXPRESSION_) {
            r = ExpressionParser.AddExpression_(b, 0);
        } else if (root == AGGREGATE_BODY) {
            r = DeclarationParser.AggregateBody(b, 0);
        } else if (root == AGGREGATE_DECLARATION) {
            r = DeclarationParser.AggregateDeclaration(b, 0);
        } else if (root == ALIAS_DECLARATION) {
            r = VariableDeclarationParser.AliasDeclaration(b, 0);
        } else if (root == ALIAS_DECLARATION_SINGLE) {
            r = VariableDeclarationParser.AliasDeclarationSingle(b, 0);
        } else if (root == ALIAS_DECLARATION_X) {
            r = VariableDeclarationParser.AliasDeclarationX(b, 0);
        } else if (root == ALIAS_DECLARATION_Y) {
            r = VariableDeclarationParser.AliasDeclarationY(b, 0);
        } else if (root == ALIAS_THIS) {
            r = DeclDefParser.AliasThis(b, 0);
        } else if (root == ALIGN_ATTRIBUTE) {
            r = AttributesParser.AlignAttribute(b, 0);
        } else if (root == ALLOCATOR) {
            r = DeclDefParser.Allocator(b, 0);
        } else if (root == ALLOCATOR_ARGUMENTS) {
            r = DeclDefParser.AllocatorArguments(b, 0);
        } else if (root == ALT_DECLARATOR) {
            r = VariableDeclarationParser.AltDeclarator(b, 0);
        } else if (root == ALT_DECLARATOR_IDENTIFIER) {
            r = VariableDeclarationParser.AltDeclaratorIdentifier(b, 0);
        } else if (root == ALT_DECLARATOR_SUFFIX) {
            r = VariableDeclarationParser.AltDeclaratorSuffix(b, 0);
        } else if (root == ALT_DECLARATOR_SUFFIXES) {
            r = VariableDeclarationParser.AltDeclaratorSuffixes(b, 0);
        } else if (root == ALT_DECLARATOR_X) {
            r = VariableDeclarationParser.AltDeclaratorX(b, 0);
        } else if (root == ALT_FUNC_DECLARATOR_SUFFIX) {
            r = VariableDeclarationParser.AltFuncDeclaratorSuffix(b, 0);
        } else if (root == AND_EXXPRESSION_) {
            r = ExpressionParser.AndExxpression_(b, 0);
        } else if (root == ANONYMOUS_ENUM_DECLARATION) {
            r = DeclarationParser.AnonymousEnumDeclaration(b, 0);
        } else if (root == ARGUMENT_LIST) {
            r = ArgumentParser.ArgumentList(b, 0);
        } else if (root == ARRAY_INITIALIZER) {
            r = MiscParser.ArrayInitializer(b, 0);
        } else if (root == ARRAY_LITERAL) {
            r = ExpressionParser.ArrayLiteral(b, 0);
        } else if (root == ARRAY_MEMBER_INITIALIZATION) {
            r = MiscParser.ArrayMemberInitialization(b, 0);
        } else if (root == ARRAY_MEMBER_INITIALIZATIONS) {
            r = MiscParser.ArrayMemberInitializations(b, 0);
        } else if (root == ASM_ADD_EXP) {
            r = AsmParser.AsmAddExp(b, 0);
        } else if (root == ASM_AND_EXP) {
            r = AsmParser.AsmAndExp(b, 0);
        } else if (root == ASM_BR_EXP) {
            r = AsmParser.AsmBrExp(b, 0);
        } else if (root == ASM_EQUAL_EXP) {
            r = AsmParser.AsmEqualExp(b, 0);
        } else if (root == ASM_EXP) {
            r = AsmParser.AsmExp(b, 0);
        } else if (root == ASM_INSTRUCTION) {
            r = AsmParser.AsmInstruction(b, 0);
        } else if (root == ASM_INSTRUCTION_LIST) {
            r = AsmParser.AsmInstructionList(b, 0);
        } else if (root == ASM_LOG_AND_EXP) {
            r = AsmParser.AsmLogAndExp(b, 0);
        } else if (root == ASM_LOG_OR_EXP) {
            r = AsmParser.AsmLogOrExp(b, 0);
        } else if (root == ASM_MUL_EXP) {
            r = AsmParser.AsmMulExp(b, 0);
        } else if (root == ASM_OR_EXP) {
            r = AsmParser.AsmOrExp(b, 0);
        } else if (root == ASM_PRIMARY_EXP) {
            r = AsmParser.AsmPrimaryExp(b, 0);
        } else if (root == ASM_REL_EXP) {
            r = AsmParser.AsmRelExp(b, 0);
        } else if (root == ASM_SHIFT_EXP) {
            r = AsmParser.AsmShiftExp(b, 0);
        } else if (root == ASM_STATEMENT) {
            r = StatementParser.AsmStatement(b, 0);
        } else if (root == ASM_TYPE_PREFIX) {
            r = AsmParser.AsmTypePrefix(b, 0);
        } else if (root == ASM_UNA_EXP) {
            r = AsmParser.AsmUnaExp(b, 0);
        } else if (root == ASM_XOR_EXP) {
            r = AsmParser.AsmXorExp(b, 0);
        } else if (root == ASSERT_EXPRESSION) {
            r = ExpressionParser.AssertExpression(b, 0);
        } else if (root == ASSIGN_EXPRESSION) {
            r = ExpressionParser.AssignExpression(b, 0);
        } else if (root == ASSOC_ARRAY_LITERAL) {
            r = ExpressionParser.AssocArrayLiteral(b, 0);
        } else if (root == ATTRIBUTE) {
            r = AttributesParser.Attribute(b, 0);
        } else if (root == ATTRIBUTE_SPECIFIER) {
            r = AttributesParser.AttributeSpecifier(b, 0);
        } else if (root == AUTO_DECLARATION) {
            r = VariableDeclarationParser.AutoDeclaration(b, 0);
        } else if (root == AUTO_DECLARATION_X) {
            r = VariableDeclarationParser.AutoDeclarationX(b, 0);
        } else if (root == AUTO_DECLARATION_Y) {
            r = VariableDeclarationParser.AutoDeclarationY(b, 0);
        } else if (root == BASE_CLASS_LIST) {
            r = DeclarationParser.BaseClassList(b, 0);
        } else if (root == BASE_INTERFACE_LIST) {
            r = DeclarationParser.BaseInterfaceList(b, 0);
        } else if (root == BASIC_TYPE) {
            r = TypeParser.BasicType(b, 0);
        } else if (root == BASIC_TYPE_2) {
            r = TypeParser.BasicType2(b, 0);
        } else if (root == BASIC_TYPE_2_X) {
            r = TypeParser.BasicType2X(b, 0);
        } else if (root == BASIC_TYPE_X) {
            r = TypeParser.BasicTypeX(b, 0);
        } else if (root == BLOCK_STATEMENT) {
            r = StatementParser.BlockStatement(b, 0);
        } else if (root == BODY_STATEMENT) {
            r = StatementParser.BodyStatement(b, 0);
        } else if (root == BREAK_STATEMENT) {
            r = StatementParser.BreakStatement(b, 0);
        } else if (root == CASE_RANGE_STATEMENT) {
            r = StatementParser.CaseRangeStatement(b, 0);
        } else if (root == CASE_STATEMENT) {
            r = StatementParser.CaseStatement(b, 0);
        } else if (root == CAST_EXPRESSION) {
            r = ExpressionParser.CastExpression(b, 0);
        } else if (root == CATCH) {
            r = StatementParser.Catch(b, 0);
        } else if (root == CATCH_PARAMETER) {
            r = StatementParser.CatchParameter(b, 0);
        } else if (root == CATCHES) {
            r = StatementParser.Catches(b, 0);
        } else if (root == CLASS_ARGUMENTS) {
            r = DeclarationParser.ClassArguments(b, 0);
        } else if (root == CLASS_DECLARATION) {
            r = DeclarationParser.ClassDeclaration(b, 0);
        } else if (root == COMMA_EXPRESSION) {
            r = ExpressionParser.CommaExpression(b, 0);
        } else if (root == CONDITION) {
            r = DeclDefParser.Condition(b, 0);
        } else if (root == CONDITION_VARIABLE_DECLARATION) {
            r = DeclDefParser.ConditionVariableDeclaration(b, 0);
        } else if (root == CONDITIONAL_DECLARATION) {
            r = DeclDefParser.ConditionalDeclaration(b, 0);
        } else if (root == CONDITIONAL_EXPRESSION_) {
            r = ExpressionParser.ConditionalExpression_(b, 0);
        } else if (root == CONDITIONAL_STATEMENT) {
            r = StatementParser.ConditionalStatement(b, 0);
        } else if (root == CONSTRAINT) {
            r = DeclDefParser.Constraint(b, 0);
        } else if (root == CONSTRUCTOR) {
            r = DeclDefParser.Constructor(b, 0);
        } else if (root == CONTINUE_STATEMENT) {
            r = StatementParser.ContinueStatement(b, 0);
        } else if (root == DEALLOCATOR) {
            r = DeclDefParser.Deallocator(b, 0);
        } else if (root == DEBUG_CONDITION) {
            r = DeclDefParser.DebugCondition(b, 0);
        } else if (root == DEBUG_SPECIFICATION) {
            r = DeclDefParser.DebugSpecification(b, 0);
        } else if (root == DECL_DEF) {
            r = DeclDefParser.DeclDef(b, 0);
        } else if (root == DECL_DEFS) {
            r = DeclDefParser.parseDeclDefs(b, 0);
        } else if (root == DECLARATION) {
            r = DeclarationParser.Declaration(b, 0);
        } else if (root == DECLARATION_BLOCK) {
            r = DeclarationParser.DeclarationBlock(b, 0);
        } else if (root == DECLARATION_STATEMENT) {
            r = StatementParser.DeclarationStatement(b, 0);
        } else if (root == DECLARATOR) {
            r = VariableDeclarationParser.Declarator(b, 0);
        } else if (root == DECLARATOR_IDENTIFIER) {
            r = VariableDeclarationParser.DeclaratorIdentifier(b, 0);
        } else if (root == DECLARATOR_IDENTIFIER_LIST) {
            r = VariableDeclarationParser.DeclaratorIdentifierList(b, 0);
        } else if (root == DECLARATOR_INITIALIZER) {
            r = VariableDeclarationParser.DeclaratorInitializer(b, 0);
        } else if (root == DECLARATORS) {
            r = VariableDeclarationParser.Declarators(b, 0);
        } else if (root == DEFAULT_STATEMENT) {
            r = StatementParser.DefaultStatement(b, 0);
        } else if (root == DELETE_EXPRESSION) {
            r = ExpressionParser.DeleteExpression(b, 0);
        } else if (root == DEPRECATED_ATTRIBUTE) {
            r = AttributesParser.DeprecatedAttribute(b, 0);
        } else if (root == DESTRUCTOR) {
            r = DeclDefParser.Destructor(b, 0);
        } else if (root == DO_STATEMENT) {
            r = StatementParser.DoStatement(b, 0);
        } else if (root == DOT_IDENTIFIER) {
            r = MiscParser.DotIdentifier(b, 0);
        } else if (root == ELSE_STATEMENT) {
            r = StatementParser.ElseStatement(b, 0);
        } else if (root == ENUM_BASE_TYPE) {
            r = DeclarationParser.EnumBaseType(b, 0);
        } else if (root == ENUM_BODY) {
            r = DeclarationParser.EnumBody(b, 0);
        } else if (root == ENUM_DECLARATION) {
            r = DeclarationParser.EnumDeclaration(b, 0);
        } else if (root == ENUM_MEMBER) {
            r = DeclarationParser.EnumMember(b, 0);
        } else if (root == ENUM_MEMBERS) {
            r = DeclarationParser.EnumMembers(b, 0);
        } else if (root == EQUAL_EXPRESSION) {
            r = ExpressionParser.EqualExpression(b, 0);
        } else if (root == EXPRESSION_STATEMENT) {
            r = ExpressionParser.ExpressionStatement(b, 0);
        } else if (root == FINAL_SWITCH_STATEMENT) {
            r = StatementParser.FinalSwitchStatement(b, 0);
        } else if (root == FINALLY_STATEMENT) {
            r = StatementParser.FinallyStatement(b, 0);
        } else if (root == FIRST_EXP) {
            r = MiscParser.FirstExp(b, 0);
        } else if (root == FOR_STATEMENT) {
            r = StatementParser.ForStatement(b, 0);
        } else if (root == FOREACH) {
            r = StatementParser.Foreach(b, 0);
        } else if (root == FOREACH_AGGREGATE) {
            r = StatementParser.ForeachAggregate(b, 0);
        } else if (root == FOREACH_RANGE_STATEMENT) {
            r = StatementParser.ForeachRangeStatement(b, 0);
        } else if (root == FOREACH_STATEMENT) {
            r = StatementParser.ForeachStatement(b, 0);
        } else if (root == FOREACH_TYPE) {
            r = StatementParser.ForeachType(b, 0);
        } else if (root == FOREACH_TYPE_ATTRIBUTE) {
            r = StatementParser.ForeachTypeAttribute(b, 0);
        } else if (root == FOREACH_TYPE_ATTRIBUTES) {
            r = StatementParser.ForeachTypeAttributes(b, 0);
        } else if (root == FOREACH_TYPE_LIST) {
            r = StatementParser.ForeachTypeList(b, 0);
        } else if (root == FUNC_DECLARATION) {
            r = DeclarationParser.FuncDeclaration(b, 0);
        } else if (root == FUNCTION_ATTRIBUTE) {
            r = DeclarationParser.FunctionAttribute(b, 0);
        } else if (root == FUNCTION_ATTRIBUTES) {
            r = DeclarationParser.FunctionAttributes(b, 0);
        } else if (root == FUNCTION_BODY) {
            r = DeclarationParser.FunctionBody(b, 0);
        } else if (root == FUNCTION_CONTRACTS) {
            r = DeclarationParser.FunctionContracts(b, 0);
        } else if (root == FUNCTION_LITERAL) {
            r = ExpressionParser.FunctionLiteral(b, 0);
        } else if (root == FUNCTION_LITERAL_BODY) {
            r = ExpressionParser.FunctionLiteralBody(b, 0);
        } else if (root == GOTO_STATEMENT) {
            r = StatementParser.GotoStatement(b, 0);
        } else if (root == IDENTIFIER) {
            r = BaseRulesParser.Identifier(b, 0);
        } else if (root == IDENTIFIER_LIST) {
            r = IdentifierListsParser.IdentifierList(b, 0);
        } else if (root == IDENTITY_EXPRESSION) {
            r = ExpressionParser.IdentityExpression(b, 0);
        } else if (root == IF_CONDITION) {
            r = StatementParser.IfCondition(b, 0);
        } else if (root == IF_STATEMENT) {
            r = StatementParser.IfStatement(b, 0);
        } else if (root == IMPORT) {
            r = DeclDefParser.Import(b, 0);
        } else if (root == IMPORT_BIND) {
            r = DeclDefParser.ImportBind(b, 0);
        } else if (root == IMPORT_BIND_LIST) {
            r = DeclDefParser.ImportBindList(b, 0);
        } else if (root == IMPORT_DECLARATION) {
            r = DeclDefParser.ImportDeclaration(b, 0);
        } else if (root == IMPORT_EXPRESSION) {
            r = ExpressionParser.ImportExpression(b, 0);
        } else if (root == IMPORT_LIST) {
            r = DeclDefParser.ImportList(b, 0);
        } else if (root == IN_EXPRESSION) {
            r = ExpressionParser.InExpression(b, 0);
        } else if (root == IN_OUT) {
            r = VariableDeclarationParser.InOut(b, 0);
        } else if (root == IN_OUT_X) {
            r = VariableDeclarationParser.InOutX(b, 0);
        } else if (root == IN_STATEMENT) {
            r = StatementParser.InStatement(b, 0);
        } else if (root == INCREMENT) {
            r = MiscParser.Increment(b, 0);
        } else if (root == INDEX_EXPRESSION) {
            r = ExpressionParser.IndexExpression(b, 0);
        } else if (root == INITIALIZE) {
            r = MiscParser.Initialize(b, 0);
        } else if (root == INITIALIZER) {
            r = VariableDeclarationParser.Initializer(b, 0);
        } else if (root == INTEGER_EXPRESSION) {
            r = ExpressionParser.IntegerExpression(b, 0);
        } else if (root == INTERFACE) {
            r = MiscParser.Interface(b, 0);
        } else if (root == INTERFACE_DECLARATION) {
            r = DeclarationParser.InterfaceDeclaration(b, 0);
        } else if (root == INTERFACES) {
            r = DeclarationParser.Interfaces(b, 0);
        } else if (root == INVARIANT) {
            r = DeclDefParser.Invariant(b, 0);
        } else if (root == IS_EXPRESSION) {
            r = ExpressionParser.IsExpression(b, 0);
        } else if (root == KEY_VALUE_PAIR) {
            r = MiscParser.KeyValuePair(b, 0);
        } else if (root == KEY_VALUE_PAIRS) {
            r = MiscParser.KeyValuePairs(b, 0);
        } else if (root == LABELED_STATEMENT) {
            r = StatementParser.LabeledStatement(b, 0);
        } else if (root == LAMBDA) {
            r = ExpressionParser.Lambda(b, 0);
        } else if (root == LAST_CATCH) {
            r = StatementParser.LastCatch(b, 0);
        } else if (root == LAST_EXP) {
            r = StatementParser.LastExp(b, 0);
        } else if (root == LINKAGE_ATTRIBUTE) {
            r = AttributesParser.LinkageAttribute(b, 0);
        } else if (root == LINKAGE_TYPE) {
            r = AttributesParser.LinkageType(b, 0);
        } else if (root == LWR_EXPRESSION) {
            r = ExpressionParser.LwrExpression(b, 0);
        } else if (root == MEMBER_FUNCTION_ATTRIBUTE) {
            r = DeclarationParser.MemberFunctionAttribute(b, 0);
        } else if (root == MEMBER_FUNCTION_ATTRIBUTES) {
            r = DeclarationParser.MemberFunctionAttributes(b, 0);
        } else if (root == MIXIN_DECLARATION) {
            r = DeclDefParser.MixinDeclaration(b, 0);
        } else if (root == MIXIN_EXPRESSION) {
            r = ExpressionParser.MixinExpression(b, 0);
        } else if (root == MIXIN_STATEMENT) {
            r = StatementParser.MixinStatement(b, 0);
        } else if (root == MIXIN_TEMPLATE_NAME) {
            r = DeclDefParser.MixinTemplateName(b, 0);
        } else if (root == MODULE_DECLARATION) {
            r = DeclDefParser.ModuleDeclaration(b, 0);
        } else if (root == MODULE_FULLY_QUALIFIED_NAME) {
            r = DeclDefParser.ModuleFullyQualifiedName(b, 0);
        } else if (root == MUL_EXPRESSION_) {
            r = ExpressionParser.MulExpression_(b, 0);
        } else if (root == MULTIPLE_ASSIGN) {
            r = ExpressionParser.MultipleAssign(b, 0);
        } else if (root == NEW_ANON_CLASS_EXPRESSION) {
            r = ExpressionParser.NewAnonClassExpression(b, 0);
        } else if (root == NEW_EXPRESSION) {
            r = ExpressionParser.NewExpression(b, 0);
        } else if (root == NEW_EXPRESSION_WITH_ARGS) {
            r = ExpressionParser.NewExpressionWithArgs(b, 0);
        } else if (root == NON_EMPTY_STATEMENT) {
            r = StatementParser.NonEmptyStatement(b, 0);
        } else if (root == NON_VOID_INITIALIZER) {
            r = VariableDeclarationParser.NonVoidInitializer(b, 0);
        } else if (root == OPCODE) {
            r = AsmParser.Opcode(b, 0);
        } else if (root == OPERAND) {
            r = AsmParser.Operand(b, 0);
        } else if (root == OPERANDS) {
            r = AsmParser.Operands(b, 0);
        } else if (root == OR_OR_EXPRESSION) {
            r = ExpressionParser.OrOrExpression(b, 0);
        } else if (root == OUT_STATEMENT) {
            r = StatementParser.OutStatement(b, 0);
        } else if (root == PARAMETER) {
            r = ParameterParser.Parameter(b, 0);
        } else if (root == PARAMETER_ATTRIBUTES) {
            r = ParameterParser.ParameterAttributes(b, 0);
        } else if (root == PARAMETER_LIST) {
            r = ParameterParser.ParameterList(b, 0);
        } else if (root == PARAMETER_MEMBER_ATTRIBUTES) {
            r = ParameterParser.ParameterMemberAttributes(b, 0);
        } else if (root == PARAMETERS) {
            r = ParameterParser.Parameters(b, 0);
        } else if (root == POSTBLIT) {
            r = DeclDefParser.Postblit(b, 0);
        } else if (root == POSTFIX_EXPRESSION) {
            r = ExpressionParser.PostfixExpression(b, 0);
        } else if (root == POW_EXPRESSION_) {
            r = ExpressionParser.PowExpression_(b, 0);
        } else if (root == PRAGMA) {
            r = StatementParser.Pragma(b, 0);
        } else if (root == PRAGMA_STATEMENT) {
            r = StatementParser.PragmaStatement(b, 0);
        } else if (root == PRIMARY_EXPRESSION) {
            r = ExpressionParser.PrimaryExpression(b, 0);
        } else if (root == PROPERTY) {
            r = AttributesParser.Property(b, 0);
        } else if (root == PROPERTY_IDENTIFIER) {
            r = AttributesParser.PropertyIdentifier(b, 0);
        } else if (root == PROTECTION_ATTRIBUTE) {
            r = AttributesParser.ProtectionAttribute(b, 0);
        } else if (root == QUALIFIED_IDENTIFIER_LIST) {
            r = IdentifierListsParser.QualifiedIdentifierList(b, 0);
        } else if (root == REGISTER) {
            r = RegisterParser.Register(b, 0);
        } else if (root == REGISTER_64) {
            r = RegisterParser.Register64(b, 0);
        } else if (root == REL_EXPRESSION) {
            r = ExpressionParser.RelExpression(b, 0);
        } else if (root == RETURN_STATEMENT) {
            r = StatementParser.ReturnStatement(b, 0);
        } else if (root == SCOPE_GUARD_STATEMENT) {
            r = StatementParser.ScopeGuardStatement(b, 0);
        } else if (root == SCOPE_STATEMENT) {
            r = StatementParser.ScopeStatement(b, 0);
        } else if (root == SCOPE_STATEMENT_LIST) {
            r = StatementParser.ScopeStatementList(b, 0);
        } else if (root == SHARED_STATIC_CONSTRUCTOR) {
            r = DeclDefParser.SharedStaticConstructor(b, 0);
        } else if (root == SHARED_STATIC_DESTRUCTOR) {
            r = DeclDefParser.SharedStaticDestructor(b, 0);
        } else if (root == SHIFT_EXPRESSION_) {
            r = ExpressionParser.ShiftExpression_(b, 0);
        } else if (root == SLICE_EXPRESSION) {
            r = ExpressionParser.SliceExpression(b, 0);
        } else if (root == SPECIAL_KEYWORD) {
            r = MiscParser.SpecialKeyword(b, 0);
        } else if (root == STATEMENT) {
            r = StatementParser.Statement(b, 0);
        } else if (root == STATEMENT_LIST) {
            r = StatementParser.StatementList(b, 0);
        } else if (root == STATEMENT_LIST_NO_CASE_NO_DEFAULT) {
            r = StatementParser.StatementListNoCaseNoDefault(b, 0);
        } else if (root == STATEMENT_NO_CASE_NO_DEFAULT) {
            r = StatementParser.StatementNoCaseNoDefault(b, 0);
        } else if (root == STATIC_ASSERT) {
            r = DeclDefParser.StaticAssert(b, 0);
        } else if (root == STATIC_CONSTRUCTOR) {
            r = DeclDefParser.StaticConstructor(b, 0);
        } else if (root == STATIC_DESTRUCTOR) {
            r = DeclDefParser.StaticDestructor(b, 0);
        } else if (root == STATIC_ELSE_CONDITION) {
            r = DeclDefParser.StaticElseCondition(b, 0);
        } else if (root == STATIC_IF_CONDITION) {
            r = DeclDefParser.StaticIfCondition(b, 0);
        } else if (root == STORAGE_CLASS) {
            r = VariableDeclarationParser.StorageClass(b, 0);
        } else if (root == STORAGE_CLASSES) {
            r = VariableDeclarationParser.StorageClasses(b, 0);
        } else if (root == STRING_LITERAL) {
            r = BaseRulesParser.StringLiteral(b, 0);
        } else if (root == STRING_LITERALS) {
            r = BaseRulesParser.StringLiterals(b, 0);
        } else if (root == STRUCT_DECLARATION) {
            r = DeclarationParser.StructDeclaration(b, 0);
        } else if (root == STRUCT_INITIALIZER) {
            r = VariableDeclarationParser.StructInitializer(b, 0);
        } else if (root == STRUCT_MEMBER_INITIALIZER) {
            r = VariableDeclarationParser.StructMemberInitializer(b, 0);
        } else if (root == STRUCT_MEMBER_INITIALIZERS) {
            r = VariableDeclarationParser.StructMemberInitializers(b, 0);
        } else if (root == SUPER_CLASS) {
            r = DeclarationParser.SuperClass(b, 0);
        } else if (root == SWITCH_STATEMENT) {
            r = StatementParser.SwitchStatement(b, 0);
        } else if (root == SYMBOL) {
            r = MiscParser.Symbol(b, 0);
        } else if (root == SYMBOL_TAIL) {
            r = MiscParser.SymbolTail(b, 0);
        } else if (root == SYNCHRONIZED_STATEMENT) {
            r = StatementParser.SynchronizedStatement(b, 0);
        } else if (root == TEMPLATE_ALIAS_PARAMETER) {
            r = TemplateParameterParser.TemplateAliasParameter(b, 0);
        } else if (root == TEMPLATE_ARGUMENT) {
            r = TemplateArgumentParser.TemplateArgument(b, 0);
        } else if (root == TEMPLATE_ARGUMENT_LIST) {
            r = TemplateArgumentParser.TemplateArgumentList(b, 0);
        } else if (root == TEMPLATE_ARGUMENTS) {
            r = TemplateArgumentParser.TemplateArguments(b, 0);
        } else if (root == TEMPLATE_DECLARATION) {
            r = DeclarationParser.TemplateDeclaration(b, 0);
        } else if (root == TEMPLATE_INSTANCE) {
            r = MiscParser.TemplateInstance(b, 0);
        } else if (root == TEMPLATE_MIXIN) {
            r = DeclDefParser.TemplateMixin(b, 0);
        } else if (root == TEMPLATE_MIXIN_DECLARATION) {
            r = DeclarationParser.TemplateMixinDeclaration(b, 0);
        } else if (root == TEMPLATE_PARAMETER) {
            r = TemplateParameterParser.TemplateParameter(b, 0);
        } else if (root == TEMPLATE_PARAMETER_LIST) {
            r = TemplateParameterParser.TemplateParameterList(b, 0);
        } else if (root == TEMPLATE_PARAMETERS) {
            r = TemplateParameterParser.TemplateParameters(b, 0);
        } else if (root == TEMPLATE_SINGLE_ARGUMENT) {
            r = TemplateArgumentParser.TemplateSingleArgument(b, 0);
        } else if (root == TEMPLATE_THIS_PARAMETER) {
            r = TemplateParameterParser.TemplateThisParameter(b, 0);
        } else if (root == TEMPLATE_TUPLE_PARAMETER) {
            r = TemplateParameterParser.TemplateTupleParameter(b, 0);
        } else if (root == TEMPLATE_TYPE_PARAMETER) {
            r = TemplateParameterParser.TemplateTypeParameter(b, 0);
        } else if (root == TEMPLATE_VALUE_PARAMETER) {
            r = TemplateParameterParser.TemplateValueParameter(b, 0);
        } else if (root == TEMPLATE_VALUE_PARAMETER_DEFAULT) {
            r = TemplateParameterParser.TemplateValueParameterDefault(b, 0);
        } else if (root == TEST) {
            r = MiscParser.Test(b, 0);
        } else if (root == THEN_STATEMENT) {
            r = StatementParser.ThenStatement(b, 0);
        } else if (root == THROW_STATEMENT) {
            r = StatementParser.ThrowStatement(b, 0);
        } else if (root == TRAITS_ARGUMENT) {
            r = ExpressionParser.TraitsArgument(b, 0);
        } else if (root == TRAITS_ARGUMENTS) {
            r = ExpressionParser.TraitsArguments(b, 0);
        } else if (root == TRAITS_EXPRESSION) {
            r = ExpressionParser.TraitsExpression(b, 0);
        } else if (root == TRAITS_KEYWORD) {
            r = ExpressionParser.TraitsKeyword(b, 0);
        } else if (root == TRY_STATEMENT) {
            r = StatementParser.TryStatement(b, 0);
        } else if (root == TYPE) {
            r = TypeParser.Type(b, 0);
        } else if (root == TYPE_CTOR) {
            r = TypeParser.TypeCtor(b, 0);
        } else if (root == TYPE_CTORS) {
            r = TypeParser.TypeCtors(b, 0);
        } else if (root == TYPE_SPECIALIZATION) {
            r = TypeParser.TypeSpecialization(b, 0);
        } else if (root == TYPE_VECTOR) {
            r = TypeParser.TypeVector(b, 0);
        } else if (root == TYPEID_EXPRESSION) {
            r = ExpressionParser.TypeidExpression(b, 0);
        } else if (root == TYPEOF) {
            r = TypeParser.Typeof(b, 0);
        } else if (root == UNION_DECLARATION) {
            r = DeclarationParser.UnionDeclaration(b, 0);
        } else if (root == UNIT_TESTING) {
            r = DeclDefParser.UnitTesting(b, 0);
        } else if (root == UPR_EXPRESSION) {
            r = ExpressionParser.UprExpression(b, 0);
        } else if (root == USER_DEFINED_ATTRIBUTE) {
            r = AttributesParser.UserDefinedAttribute(b, 0);
        } else if (root == VAR_DECLARATIONS) {
            r = VariableDeclarationParser.VarDeclarations(b, 0);
        } else if (root == VAR_DECLARATOR) {
            r = VariableDeclarationParser.VarDeclarator(b, 0);
        } else if (root == VAR_DECLARATOR_IDENTIFIER) {
            r = VariableDeclarationParser.VarDeclaratorIdentifier(b, 0);
        } else if (root == VAR_FUNC_DECLARATION) {
            r = VariableDeclarationParser.VarFuncDeclaration(b, 0);
        } else if (root == VERSION_CONDITION) {
            r = DeclDefParser.VersionCondition(b, 0);
        } else if (root == VERSION_SPECIFICATION) {
            r = DeclDefParser.VersionSpecification(b, 0);
        } else if (root == VOID_INITIALIZER) {
            r = MiscParser.VoidInitializer(b, 0);
        } else if (root == WHILE_STATEMENT) {
            r = StatementParser.WhileStatement(b, 0);
        } else if (root == WITH_STATEMENT) {
            r = StatementParser.WithStatement(b, 0);
        } else if (root == XOR_EXPRESSION_) {
            r = ExpressionParser.XorExpression_(b, 0);
        } else {
            r = parse_root_(root, b, 0);
        }
        exit_section_(b, 0, m, root, r, true, TRUE_CONDITION);
    }

    @NotNull
    @Override
    public ASTNode parse(@NotNull IElementType root, @NotNull PsiBuilder builder) {
        parseLight(root, builder);
        return builder.getTreeBuilt();
    }

    protected boolean parse_root_(IElementType t, PsiBuilder b, int l) {
        return dFile(b, l + 1);
    }

}
