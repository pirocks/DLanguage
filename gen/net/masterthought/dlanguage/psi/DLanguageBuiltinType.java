package net.masterthought.dlanguage.psi;

import com.intellij.psi.PsiElement;
import org.jetbrains.annotations.Nullable;


public interface DLanguageBuiltinType extends PsiElement {
    @Nullable
    PsiElement getKW_BOOL();

    @Nullable
    PsiElement getKW_BYTE();

    @Nullable
    PsiElement getKW_UBYTE();

    @Nullable
    PsiElement getKW_SHORT();

    @Nullable
    PsiElement getKW_USHORT();

    @Nullable
    PsiElement getKW_INT();

    @Nullable
    PsiElement getKW_UINT();

    @Nullable
    PsiElement getKW_LONG();

    @Nullable
    PsiElement getKW_ULONG();

    @Nullable
    PsiElement getKW_CHAR();

    @Nullable
    PsiElement getKW_WCHAR();

    @Nullable
    PsiElement getKW_DCHAR();

    @Nullable
    PsiElement getKW_FLOAT();

    @Nullable
    PsiElement getKW_DOUBLE();

    @Nullable
    PsiElement getKW_REAL();

    @Nullable
    PsiElement getKW_IFLOAT();

    @Nullable
    PsiElement getKW_IDOUBLE();

    @Nullable
    PsiElement getKW_IREAL();

    @Nullable
    PsiElement getKW_CFLOAT();

    @Nullable
    PsiElement getKW_CDOUBLE();

    @Nullable
    PsiElement getKW_CREAL();

    @Nullable
    PsiElement getKW_VOID();

}
