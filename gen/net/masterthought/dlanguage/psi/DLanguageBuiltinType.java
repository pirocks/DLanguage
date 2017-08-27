package net.masterthought.dlanguage.psi;

import com.intellij.psi.PsiElement;
import org.jetbrains.annotations.Nullable;


public interface DLanguageBuiltinType extends PsiElement {
    @Nullable
    public PsiElement getKW_BOOL();

    @Nullable
    public PsiElement getKW_BYTE();

    @Nullable
    public PsiElement getKW_UBYTE();

    @Nullable
    public PsiElement getKW_SHORT();

    @Nullable
    public PsiElement getKW_USHORT();

    @Nullable
    public PsiElement getKW_INT();

    @Nullable
    public PsiElement getKW_UINT();

    @Nullable
    public PsiElement getKW_LONG();

    @Nullable
    public PsiElement getKW_ULONG();

    @Nullable
    public PsiElement getKW_CHAR();

    @Nullable
    public PsiElement getKW_WCHAR();

    @Nullable
    public PsiElement getKW_DCHAR();

    @Nullable
    public PsiElement getKW_FLOAT();

    @Nullable
    public PsiElement getKW_DOUBLE();

    @Nullable
    public PsiElement getKW_REAL();

    @Nullable
    public PsiElement getKW_IFLOAT();

    @Nullable
    public PsiElement getKW_IDOUBLE();

    @Nullable
    public PsiElement getKW_IREAL();

    @Nullable
    public PsiElement getKW_CFLOAT();

    @Nullable
    public PsiElement getKW_CDOUBLE();

    @Nullable
    public PsiElement getKW_CREAL();

    @Nullable
    public PsiElement getKW_VOID();

}
