package net.masterthought.dlanguage.psi.interfaces;

import com.intellij.extapi.psi.StubBasedPsiElementBase;
import com.intellij.psi.StubBasedPsiElement;
import com.intellij.psi.stubs.StubElement;
import net.masterthought.dlanguage.stubs.interfaces.HasMembersStub;

import java.util.Set;
import java.util.stream.Collectors;

/**
 * Created by francis on 8/5/2017.
 */
public interface HasMembers<T extends StubElement> extends StubBasedPsiElement<T>, DNamedElement {
    default Set<DNamedElement> getMembers() {
        return ((HasMembersStub) (((StubBasedPsiElementBase) this).getGreenStub())).getMembers().stream().map(dNamedStubBase -> (DNamedElement) dNamedStubBase.getPsi()).collect(Collectors.toSet());
    }


}
