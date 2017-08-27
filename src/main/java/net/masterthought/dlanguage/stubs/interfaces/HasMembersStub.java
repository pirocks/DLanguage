package net.masterthought.dlanguage.stubs.interfaces;

import com.intellij.extapi.psi.StubBasedPsiElementBase;
import com.intellij.psi.stubs.Stub;
import net.masterthought.dlanguage.psi.DLanguageSharedStaticConstructor;
import net.masterthought.dlanguage.psi.DLanguageSharedStaticDestructor;
import net.masterthought.dlanguage.psi.interfaces.DNamedElement;
import net.masterthought.dlanguage.stubs.*;
import org.jetbrains.annotations.NotNull;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Created by francis on 8/11/2017.
 */
public interface HasMembersStub {
    @NotNull
    String getName();

    default Set<DNamedStubBase> getMembers() {
        final Set<DNamedStubBase> res = new HashSet<>();
        getMembersImpl(((StubBasedPsiElementBase) this).getGreenStub(), res);
        return res;
    }

    default Set<DNamedElement> getMembersPsi() {
        final Set<DNamedStubBase> res = new HashSet<>();
        getMembersImpl(((StubBasedPsiElementBase) this).getGreenStub(), res);
        return res.stream().map(dNamedStubBase -> (DNamedElement) dNamedStubBase.getPsi()).collect(Collectors.toSet());
    }

    default void getMembersImpl(final Stub stub, final Set<DNamedStubBase> result) {
        for (final Stub childStub : stub.getChildrenStubs()) {
            if (childStub instanceof DNamedStubBase && !(childStub instanceof DLanguageIdentifierStub)) {
                result.add((DNamedStubBase) childStub);
            }
            if (childStub instanceof DLanguageUnittestStub || (childStub instanceof DLanguageDestructorStub) || (childStub instanceof DLanguageStaticDestructorStub) || (childStub instanceof DLanguageSharedStaticDestructor) || (childStub instanceof DLanguageConstructorStub) || (childStub instanceof DLanguageStaticConstructorStub) || (childStub instanceof DLanguageSharedStaticConstructor) || (childStub instanceof DLanguageFunctionDeclarationStub) || (childStub instanceof DLanguageIdentifierStub)) {
            } else {
                getMembersImpl(childStub, result);
            }
        }
    }
}
