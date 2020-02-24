package com.idli.extensions

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource

class StringTest {
    @ParameterizedTest
    @CsvSource(value = ["123,123123123 | true","0.0 | true", "13123.3 | true", "a13123 | false" , "0-20 | false"], delimiter = '|')
    fun `should check if its a number`(text: String, assertion: Boolean) {
        assertEquals(text.isNumber(), assertion)
    }

    @ParameterizedTest
    @CsvSource(value = ["True | true", "true | true", "False | true", "false | true"], delimiter = '|')
    fun `should check if its a boolean`(text: String, assertion: Boolean) {
        assertEquals(text.isBoolean(), assertion)
    }
}