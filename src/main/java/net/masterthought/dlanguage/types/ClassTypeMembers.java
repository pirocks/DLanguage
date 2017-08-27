package net.masterthought.dlanguage.types;

import com.google.common.collect.Sets;
import net.masterthought.dlanguage.psi.DLanguageBaseClass;
import net.masterthought.dlanguage.psi.DLanguageClassDeclaration;
import net.masterthought.dlanguage.psi.DLanguageInterfaceOrClass;
import net.masterthought.dlanguage.psi.interfaces.DNamedElement;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Created by francis on 8/24/2017.
 */
public class ClassTypeMembers implements TypeMembers {
    private final DLanguageInterfaceOrClass psi;

    public ClassTypeMembers(final DLanguageInterfaceOrClass psi) {
        this.psi = psi;
    }

    private final Set<String> builtinMembers = Sets.newHashSet("sizeof", "alignof", "mangleof", "tupleof", "this", "super", "classinfo", "vptr", "monitor", "outer");

    /**
     * @return all member decls, in theory without psi loading beyond stub backed psi
     */
    @Override
    public Set<DNamedElement> getMemberDeclarations() {
        final HashMap<String, DNamedElement> res = new HashMap<>();
        for (final DNamedElement element : psi.getMembers()) {
            res.putIfAbsent(element.getName(), element);
        }

        if (psi.getBaseClassList() != null) {
            for (final DLanguageBaseClass baseClass : psi.getBaseClassList().getBaseClasss()) {
                //todo obviously this is not fast.
                for (final DNamedElement element : DTypeUtilsKt.from(baseClass.getType(), true).getTypeMembersProvider().getMemberDeclarations()) {
                    res.putIfAbsent(element.getName(), element);//todo don't recursively include super
                }

            }
        }
        //todo mixins, alias this
        final HashSet<DNamedElement> elements = Sets.newHashSet(res.values());
        elements.add(this.psi);
        return elements;

    }

    @Override
    public Set<DNamedElement> searchMemberDeclarations(final String name) {
        if (name.equals("this")) {
            return Sets.newHashSet(this.psi);
        }
        if (name.equals("super")) {
            return getSuper();
        }
        return getMemberDeclarations().stream().filter(dNamedElement -> dNamedElement.getName().equals(name)).collect(Collectors.toSet());
    }

    @NotNull
    public Set<DNamedElement> getSuper() {
        if (this.psi.getBaseClassList() != null) {
            final DType type = DTypeUtilsKt.from(this.psi.getBaseClassList().getBaseClasss().get(0).getType(), true);
            if (type instanceof DTypeClass) {
                final DLanguageInterfaceOrClass class_ = ((DTypeClass) type).getInterfaceOrClass();
                if (class_.getParent() instanceof DLanguageClassDeclaration) {
                    return Sets.newHashSet(class_);
                }
            }
        }
        return Sets.newHashSet();
    }

    @Override
    public boolean hasMemberDeclarationOfName(final String name) {
        if (builtinMembers.contains(name))
            return true;
        return getMemberDeclarations().stream().anyMatch(dNamedElement -> dNamedElement.getName().equals(name));
    }

    /*
     *built in symbols, __sizeof, __xalignof, _mangleof, _tupleof,this,super,classinfo,__vptr,__monitor,outer
     *all members etc.
     *
     *
     * Dsymbol s;
        static if (LOGDOTEXP)
        {
            printf("TypeClass::dotExp(e = '%s', ident = '%s')\n", e.toChars(), ident.toChars());
        }
        assert(e.op != TOKdot);

        // Bugzilla 12543
        if (ident == Id.__sizeof || ident == Id.__xalignof || ident == Id._mangleof)
        {
            return Type.getProperty(e.loc, ident, 0);
        }

        /* If e.tupleof
         *\/
        if (ident == Id._tupleof)
        {
            /* Create a TupleExp
             *\/
            e = e.semantic(sc); // do this before turning on noaccesscheck

            sym.size(e.loc); // do semantic of type

            Expression e0;
            Expression ev = e.op == TOKtype ? null : e;
            if (ev)
                ev = extractSideEffect(sc, "__tup", e0, ev);

            auto exps = new Expressions();
            exps.reserve(sym.fields.dim);
            for (size_t i = 0; i < sym.fields.dim; i++)
            {
                VarDeclaration v = sym.fields[i];
                // Don't include hidden 'this' pointer
                if (v.isThisDeclaration())
                    continue;
                Expression ex;
                if (ev)
                    ex = new DotVarExp(e.loc, ev, v);
                else
                {
                    ex = new VarExp(e.loc, v);
                    ex.type = ex.type.addMod(e.type.mod);
                }
                exps.push(ex);
            }

            e = new TupleExp(e.loc, e0, exps);
            Scope* sc2 = sc.push();
            sc2.flags = sc.flags | SCOPEnoaccesscheck;
            e = e.semantic(sc2);
            sc2.pop();
            return e;
        }

        Dsymbol searchSym()
        {
            int flags = sc.flags & SCOPEignoresymbolvisibility ? IgnoreSymbolVisibility : 0;
            Dsymbol sold = void;
            if (global.params.bug10378 || global.params.check10378)
            {
                sold = sym.search(e.loc, ident, flags | IgnoreSymbolVisibility);
                if (!global.params.check10378)
                    return sold;
            }

            auto s = sym.search(e.loc, ident, flags | SearchLocalsOnly);
            if (!s && !(flags & IgnoreSymbolVisibility))
            {
                s = sym.search(e.loc, ident, flags | SearchLocalsOnly | IgnoreSymbolVisibility);
                if (s && !(flags & IgnoreErrors))
                    .deprecation(e.loc, "%s is not visible from class %s", s.toPrettyChars(), sym.toChars());
            }
            if (global.params.check10378)
            {
                alias snew = s;
                if (sold !is snew)
                    Scope.deprecation10378(e.loc, sold, snew);
                if (global.params.bug10378)
                    s = sold;
            }
            return s;
        }

        s = searchSym();
    L1:
        if (!s)
        {
            // See if it's 'this' class or a base class
            if (sym.ident == ident)
            {
                if (e.op == TOKtype)
                    return Type.getProperty(e.loc, ident, 0);
                e = new DotTypeExp(e.loc, e, sym);
                e = e.semantic(sc);
                return e;
            }
            if (auto cbase = sym.searchBase(ident))
            {
                if (e.op == TOKtype)
                    return Type.getProperty(e.loc, ident, 0);
                if (auto ifbase = cbase.isInterfaceDeclaration())
                    e = new CastExp(e.loc, e, ifbase.type);
                else
                    e = new DotTypeExp(e.loc, e, cbase);
                e = e.semantic(sc);
                return e;
            }

            if (ident == Id.classinfo)
            {
                assert(Type.typeinfoclass);
                Type t = Type.typeinfoclass.type;
                if (e.op == TOKtype || e.op == TOKdottype)
                {
                    /* For type.classinfo, we know the classinfo
                     * at compile time.
                     *\/
                    if (!sym.vclassinfo)
                        sym.vclassinfo = new TypeInfoClassDeclaration(sym.type);
                    e = new VarExp(e.loc, sym.vclassinfo);
                    e = e.addressOf();
                    e.type = t; // do this so we don't get redundant dereference
                }
                else
                {
                    /* For class objects, the classinfo reference is the first
                     * entry in the vtbl[]
                     *\/
                    e = new PtrExp(e.loc, e);
                    e.type = t.pointerTo();
                    if (sym.isInterfaceDeclaration())
                    {
                        if (sym.isCPPinterface())
                        {
                            /* C++ interface vtbl[]s are different in that the
                             * first entry is always pointer to the first virtual
                             * function, not classinfo.
                             * We can't get a .classinfo for it.
                             *\/
                            error(e.loc, "no .classinfo for C++ interface objects");
                        }
                        /* For an interface, the first entry in the vtbl[]
                         * is actually a pointer to an instance of struct Interface.
                         * The first member of Interface is the .classinfo,
                         * so add an extra pointer indirection.
                         *\/
                        e.type = e.type.pointerTo();
                        e = new PtrExp(e.loc, e);
                        e.type = t.pointerTo();
                    }
                    e = new PtrExp(e.loc, e, t);
                }
                return e;
            }

            if (ident == Id.__vptr)
            {
                /* The pointer to the vtbl[]
                 * *cast(immutable(void*)**)e
                 *\/
                e = e.castTo(sc, tvoidptr.immutableOf().pointerTo().pointerTo());
                e = new PtrExp(e.loc, e);
                e = e.semantic(sc);
                return e;
            }

            if (ident == Id.__monitor)
            {
                /* The handle to the monitor (call it a void*)
                 * *(cast(void**)e + 1)
                 *\/
                e = e.castTo(sc, tvoidptr.pointerTo());
                e = new AddExp(e.loc, e, new IntegerExp(1));
                e = new PtrExp(e.loc, e);
                e = e.semantic(sc);
                return e;
            }

            if (ident == Id.outer && sym.vthis)
            {
                if (sym.vthis._scope)
                    sym.vthis.semantic(null);

                if (auto cdp = sym.toParent2().isClassDeclaration())
                {
                    auto dve = new DotVarExp(e.loc, e, sym.vthis);
                    dve.type = cdp.type.addMod(e.type.mod);
                    return dve;
                }

                /* Bugzilla 15839: Find closest parent class through nested functions.
                 *\/
                for (auto p = sym.toParent2(); p; p = p.toParent2())
                {
                    auto fd = p.isFuncDeclaration();
                    if (!fd)
                        break;
                    if (fd.isNested())
                        continue;
                    auto ad = fd.isThis();
                    if (!ad)
                        break;
                    if (auto cdp = ad.isClassDeclaration())
                    {
                        auto ve = new ThisExp(e.loc);

                        ve.var = fd.vthis;
                        const nestedError = fd.vthis.checkNestedReference(sc, e.loc);
                        assert(!nestedError);

                        ve.type = fd.vthis.type.addMod(e.type.mod);
                        return ve;
                    }
                    break;
                }

                // Continue to show enclosing function's frame (stack or closure).
                auto dve = new DotVarExp(e.loc, e, sym.vthis);
                dve.type = sym.vthis.type.addMod(e.type.mod);
                return dve;
            }

            return noMember(sc, e, ident, flag & 1);
        }
        if (!(sc.flags & SCOPEignoresymbolvisibility) && !symbolIsVisible(sc, s))
        {
            .deprecation(e.loc, "%s is not visible from module %s", s.toPrettyChars(), sc._module.toPrettyChars());
            // return noMember(sc, e, ident, flag);
        }
        if (!s.isFuncDeclaration()) // because of overloading
            s.checkDeprecated(e.loc, sc);
        s = s.toAlias();

        if (auto em = s.isEnumMember())
        {
            return em.getVarExp(e.loc, sc);
        }
        if (auto v = s.isVarDeclaration())
        {
            if (!v.type ||
                !v.type.deco && v.inuse)
            {
                if (v.inuse) // Bugzilla 9494
                    e.error("circular reference to %s '%s'", v.kind(), v.toPrettyChars());
                else
                    e.error("forward reference to %s '%s'", v.kind(), v.toPrettyChars());
                return new ErrorExp();
            }
            if (v.type.ty == Terror)
                return new ErrorExp();

            if ((v.storage_class & STCmanifest) && v._init)
            {
                if (v.inuse)
                {
                    e.error("circular initialization of %s '%s'", v.kind(), v.toPrettyChars());
                    return new ErrorExp();
                }
                checkAccess(e.loc, sc, null, v);
                Expression ve = new VarExp(e.loc, v);
                ve = ve.semantic(sc);
                return ve;
            }
        }

        if (auto t = s.getType())
        {
            return (new TypeExp(e.loc, t)).semantic(sc);
        }

        TemplateMixin tm = s.isTemplateMixin();
        if (tm)
        {
            Expression de = new DotExp(e.loc, e, new ScopeExp(e.loc, tm));
            de.type = e.type;
            return de;
        }

        TemplateDeclaration td = s.isTemplateDeclaration();
        if (td)
        {
            if (e.op == TOKtype)
                e = new TemplateExp(e.loc, td);
            else
                e = new DotTemplateExp(e.loc, e, td);
            e = e.semantic(sc);
            return e;
        }

        TemplateInstance ti = s.isTemplateInstance();
        if (ti)
        {
            if (!ti.semanticRun)
            {
                ti.semantic(sc);
                if (!ti.inst || ti.errors) // if template failed to expand
                    return new ErrorExp();
            }
            s = ti.inst.toAlias();
            if (!s.isTemplateInstance())
                goto L1;
            if (e.op == TOKtype)
                e = new ScopeExp(e.loc, ti);
            else
                e = new DotExp(e.loc, e, new ScopeExp(e.loc, ti));
            return e.semantic(sc);
        }

        if (s.isImport() || s.isModule() || s.isPackage())
        {
            e = DsymbolExp.resolve(e.loc, sc, s, false);
            return e;
        }

        OverloadSet o = s.isOverloadSet();
        if (o)
        {
            auto oe = new OverExp(e.loc, o);
            if (e.op == TOKtype)
                return oe;
            return new DotExp(e.loc, e, oe);
        }

        Declaration d = s.isDeclaration();
        if (!d)
        {
            e.error("%s.%s is not a declaration", e.toChars(), ident.toChars());
            return new ErrorExp();
        }

        if (e.op == TOKtype)
        {
            /* It's:
             *    Class.d
             *\/
            if (TupleDeclaration tup = d.isTupleDeclaration())
            {
                e = new TupleExp(e.loc, tup);
                e = e.semantic(sc);
                return e;
            }
            if (d.needThis() && sc.intypeof != 1)
            {
                /* Rewrite as:
                 *  this.d
                 *\/
                if (hasThis(sc))
                {
                    // This is almost same as getRightThis() in expression.c
                    Expression e1 = new ThisExp(e.loc);
                    e1 = e1.semantic(sc);
                L2:
                    Type t = e1.type.toBasetype();
                    ClassDeclaration cd = e.type.isClassHandle();
                    ClassDeclaration tcd = t.isClassHandle();
                    if (cd && tcd && (tcd == cd || cd.isBaseOf(tcd, null)))
                    {
                        e = new DotTypeExp(e1.loc, e1, cd);
                        e = new DotVarExp(e.loc, e, d);
                        e = e.semantic(sc);
                        return e;
                    }
                    if (tcd && tcd.isNested())
                    {
                        /* e1 is the 'this' pointer for an inner class: tcd.
                         * Rewrite it as the 'this' pointer for the outer class.
                         *\/
                        e1 = new DotVarExp(e.loc, e1, tcd.vthis);
                        e1.type = tcd.vthis.type;
                        e1.type = e1.type.addMod(t.mod);
                        // Do not call checkNestedRef()
                        //e1 = e1->semantic(sc);

                        // Skip up over nested functions, and get the enclosing
                        // class type.
                        int n = 0;
                        for (s = tcd.toParent(); s && s.isFuncDeclaration(); s = s.toParent())
                        {
                            FuncDeclaration f = s.isFuncDeclaration();
                            if (f.vthis)
                            {
                                //printf("rewriting e1 to %s's this\n", f->toChars());
                                n++;
                                e1 = new VarExp(e.loc, f.vthis);
                            }
                            else
                            {
                                e = new VarExp(e.loc, d);
                                return e;
                            }
                        }
                        if (s && s.isClassDeclaration())
                        {
                            e1.type = s.isClassDeclaration().type;
                            e1.type = e1.type.addMod(t.mod);
                            if (n > 1)
                                e1 = e1.semantic(sc);
                        }
                        else
                            e1 = e1.semantic(sc);
                        goto L2;
                    }
                }
            }
            //printf("e = %s, d = %s\n", e->toChars(), d->toChars());
            if (d.semanticRun == PASSinit && d._scope)
                d.semantic(d._scope);
            checkAccess(e.loc, sc, e, d);
            auto ve = new VarExp(e.loc, d);
            if (d.isVarDeclaration() && d.needThis())
                ve.type = d.type.addMod(e.type.mod);
            return ve;
        }

        bool unreal = e.op == TOKvar && (cast(VarExp)e).var.isField();
        if (d.isDataseg() || unreal && d.isField())
        {
            // (e, d)
            checkAccess(e.loc, sc, e, d);
            Expression ve = new VarExp(e.loc, d);
            e = unreal ? ve : new CommaExp(e.loc, e, ve);
            e = e.semantic(sc);
            return e;
        }

        e = new DotVarExp(e.loc, e, d);
        e = e.semantic(sc);
        return e;
     */
}
