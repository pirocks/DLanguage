package net.masterthought.dlanguage.psi.interfaces;

import com.intellij.extapi.psi.StubBasedPsiElementBase;
import com.intellij.psi.StubBasedPsiElement;
import com.intellij.psi.stubs.NamedStubBase;
import com.intellij.psi.stubs.StubElement;
import net.masterthought.dlanguage.stubs.interfaces.HasMembersStub;

import java.util.Set;

/**
 * Created by francis on 8/5/2017.
 */
public interface HasMembers<T extends StubElement> extends StubBasedPsiElement<T>, DNamedElement {
    default Set<NamedStubBase> getMembers() {
        return ((HasMembersStub) (((StubBasedPsiElementBase) this).getGreenStub())).getMembers();

    }


}
