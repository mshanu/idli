package com.idli

import com.idli.datatypes.oracle.MySqlTypes
import com.idli.datatypes.oracle.OracleTypes
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test

internal class ArgumentsTest {
    @Test
    fun shouldParseAndReturnFileBasedOnThePath() {
        val strings = arrayOf("someargument", "someotherarguments", "file=somefilepath")
        val arguments = Arguments.parse(strings)
        assertEquals("somefilepath", arguments.file.path)
    }

    @Test
    fun shouldParseAndReturnMysqlTypeAsDefault() {
        val strings = arrayOf("file=somefilepath")
        val arguments = Arguments.parse(strings)
        assertEquals("somefilepath", arguments.file.path)
        assertTrue(arguments.types is MySqlTypes)
    }

    @Test
    fun shouldParseAndReturnTypeIfProvidedAsArgument() {
        val strings = arrayOf("db=oracle", "file=somefilepath")
        val arguments = Arguments.parse(strings)
        assertEquals("somefilepath", arguments.file.path)
        assertTrue(arguments.types is OracleTypes)
    }
}