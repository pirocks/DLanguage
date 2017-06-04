package net.masterthought.dlanguage.psi.interfaces;

import com.intellij.psi.PsiElement;
import com.intellij.psi.util.PsiTreeUtil;
import net.masterthought.dlanguage.psi.DLangTemplateAliasParameter;
import net.masterthought.dlanguage.psi.DLangTemplateParameter;
import net.masterthought.dlanguage.psi.DLangTemplateThisParameter;
import net.masterthought.dlanguage.psi.DLangTemplateTypeParameter;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Created by francis on 3/4/2017.
 */
public interface HasTemplateArguments extends PsiElement {
    default List<DLangTemplateParameter> getTemplateParametersList() {
        final Collection<DLangTemplateParameter> childrenOfType = PsiTreeUtil.findChildrenOfType(this, DLangTemplateParameter.class);
        return new ArrayList<>(childrenOfType);
    }

    default List<DLangTemplateTypeParameter> getTypeParameters() {
        List<DLangTemplateTypeParameter> res = new ArrayList<>();
        for (DLangTemplateParameter parameter : getTemplateParametersList()) {
            if (parameter.getTemplateTypeParameter() != null) {
                res.add(parameter.getTemplateTypeParameter());
            }
        }
        return res;
    }

    default List<DLangTemplateAliasParameter> getTemplateAliasParameters() {
        List<DLangTemplateAliasParameter> res = new ArrayList<>();
        for (DLangTemplateParameter parameter : getTemplateParametersList()) {
            if (parameter.getTemplateAliasParameter() != null) {
                res.add(parameter.getTemplateAliasParameter());
            }
        }
        return res;
    }

    default List<DLangTemplateThisParameter> getTemplateThisParameters() {
        List<DLangTemplateThisParameter> res = new ArrayList<>();
        for (DLangTemplateParameter parameter : getTemplateParametersList()) {
            if (parameter.getTemplateThisParameter() != null) {
                res.add(parameter.getTemplateThisParameter());
            }
        }
        return res;
    }
}
