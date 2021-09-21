package com.idli.datatypes.dbs

import com.idli.model.StringType
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource

class TypeInferTest {
    private val oracleTypes = TypeInfer(OracleTypes())
    @Test
    fun `should return varchar type for string`() {
        val type = oracleTypes.infer("value")
        assertEquals("VARCHAR2", type.name)
        assertTrue((type is StringType))
        assertEquals(5, (type as StringType).size)
    }

    @Test
    fun `should return varchar type for string with numbers`() {
        val type = oracleTypes.infer("val21e")
        assertEquals("VARCHAR2", type.name)
        assertEquals(6, (type as StringType).size)
    }

    @Test
    fun `should return date time type for string with date`() {
        val type = oracleTypes.infer("2020-01-01 10:10:10+05:30")
        assertEquals("DATETIME", type.name)
    }

    @Test
    fun `should return date type for string with only date`() {
        val type = oracleTypes.infer("2020-01-01")
        assertEquals("DATE", type.name)
    }

    @ParameterizedTest
    @CsvSource(
        value = ["123123 | NUMBER", "12321,12321 | NUMBER",
            "asdf1asdf | VARCHAR2", "-123123 | NUMBER"], delimiter = '|'
    )
    fun `should return number data type`(value: String, type: String) {
        assertEquals(type, oracleTypes.infer(value).name, value)
    }

}