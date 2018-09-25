package io.github.intellij.dlanguage.debugger

import com.intellij.execution.DefaultExecutionResult
import com.intellij.execution.ExecutionException
import com.intellij.execution.ExecutionResult
import com.intellij.execution.runners.ExecutionEnvironment
import com.intellij.openapi.module.Module
import com.intellij.openapi.project.Project
import com.intellij.psi.PsiFile
import com.intellij.testFramework.LightPlatformTestCase
import com.intellij.testFramework.fixtures.HeavyIdeaTestFixture
import com.intellij.xdebugger.XDebugProcess
import com.intellij.xdebugger.XDebugProcessStarter
import com.intellij.xdebugger.XDebugSession
import com.intellij.xdebugger.XDebuggerManager
import io.github.intellij.dlanguage.run.RunUtil
import org.junit.Test
import uk.co.cwspencer.gdb.Gdb
import uk.co.cwspencer.gdb.messages.GdbEvent
import uk.co.cwspencer.ideagdb.debug.GdbDebugProcess

class DebuggerTest : LightPlatformTestCase(){
    override fun setUp() {
        super.setUp()
        val project = getProject()!!;

        val executableFilePath = "/usr/bin/gdb";//todo
        val env:ExecutionEnvironment = ExecutionEnvironment();
        val result: ExecutionResult = DefaultExecutionResult();
        val debugSession = XDebuggerManager.getInstance(project).startSession(env,
            object : XDebugProcessStarter() {
                @Throws(ExecutionException::class)
                override fun start(session: XDebugSession): XDebugProcess {
                    return GdbDebugProcess(project, session, result, executableFilePath)
                }
            })

        val debugProcess = debugSession.debugProcess as GdbDebugProcess

        val gdbProcess = debugProcess.m_gdb

        // Queue startup commands
        gdbProcess.sendCommand("-list-features") { event -> gdbProcess.onGdbCapabilitiesReady(event) }

        // Send startup commands
        val commandsArray = mutableListOf<String>()//configuration.STARTUP_COMMANDS.split("\\r?\\n");
        for (command in commandsArray) {
            val trimmedCommand = command.trim { it <= ' ' }
            if (!command.isEmpty()) {
                gdbProcess.sendCommand(command)
            }
        }
        gdbProcess.sendCommand("-exec-run")
    }


    @Test
    fun test(){

    }


}
