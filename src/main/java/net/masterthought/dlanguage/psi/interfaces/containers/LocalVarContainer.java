package net.masterthought.dlanguage.psi.interfaces.containers;

import net.masterthought.dlanguage.psi.DLangAutoDeclarationY;
import net.masterthought.dlanguage.psi.DLangDeclaratorInitializer;
import net.masterthought.dlanguage.psi.interfaces.VariableDeclaration;

/**
 * Created by francis on 2/28/2017.
 */
public interface LocalVarContainer extends Container {
    Class variableDeclarationClass = VariableDeclaration.class;
    Class autoDeclarationClass = DLangAutoDeclarationY.class;
    Class declaratorInitializer = DLangDeclaratorInitializer.class;

}
