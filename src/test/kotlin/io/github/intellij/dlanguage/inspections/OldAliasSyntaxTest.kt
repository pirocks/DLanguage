package io.github.intellij.dlanguage.inspections

import org.junit.runner.RunWith

import org.junit.Assert.*


class OldAliasSyntaxTest : DInspectionAndQuickFixTestBase() {


    fun testOldAliasSyntax() = doTest("Old Alias Syntax", true)

    override fun getBasePath(): String = "gold/inspections/testOldAliasSyntax"
}
