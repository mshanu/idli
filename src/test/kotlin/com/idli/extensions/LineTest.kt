package com.idli.extensions

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class LineTest {
    @Test
    fun shouldSplitByDelimitingCharacter() {
        val splitLineToValues = "first,second".splitLineToValues(",")
        assertEquals(2, splitLineToValues.size)
        assertEquals("first", splitLineToValues.first())
        assertEquals("second", splitLineToValues.last())
    }
    @Test
    fun shouldSplitByDelimitingCharacterIgnoringDoubleQuoted() {
        val splitLineToValues = "first,\"quoted one,quoted two\",three".splitLineToValues(",")
        assertEquals(3, splitLineToValues.size)
        assertEquals("first", splitLineToValues.first())
        assertEquals("quoted one,quoted two", splitLineToValues[1])
        assertEquals("three", splitLineToValues[2])
    }
}