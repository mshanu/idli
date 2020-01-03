package com.idli.datatypes

import com.idli.model.BooleanType
import com.idli.model.NullType
import com.idli.model.NumberType
import com.idli.model.StringType
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test

class TypesTest {
    @Nested
    class BooleanTypeTest {
        private val types = Types()
        @Test
        fun shouldReturnBooleanTypeWhenAllSampleTypesAreBoolean() {
            types.add(BooleanType("BOOLEAN"))
            types.add(BooleanType("BOOLEAN"))
            types.add(BooleanType("BOOLEAN"))
            assertEquals("BOOLEAN", types.infer().name)
        }

        @Test
        fun shouldReturnBooleanTypeWhenItsNullTypesAndAtLeastOneBooleanType() {
            types.add(NullType("VARCHAR2"))
            types.add(NullType("VARCHAR2"))
            types.add(BooleanType("BOOLEAN"))
            assertEquals("BOOLEAN", types.infer().name)
        }

        @Test
        fun shouldNotReturnBooleanTypeWhenThereIsANonBooleanNullTypeThatExists() {
            types.add(NullType("VARCHAR2"))
            types.add(NullType("VARCHAR2"))
            types.add(BooleanType("BOOLEAN"))
            types.add(StringType("VARCHAR2", 12))
            assertNotEquals("BOOLEAN", types.infer().name)
        }
    }


    @Nested
    class StringTypeTest {
        private val types = Types()
        @Test
        fun shouldInferToTypeOfVarcharForAllStringTypeWithLongestSize() {
            types.add(StringType("varchar", 6))
            types.add(StringType("varchar", 4))
            types.add(StringType("varchar", 3))
            val type = types.infer()
            assertEquals("varchar", type.name)
            assertEquals(6, type.size)
        }

        @Test
        fun shouldInferToTypeOfVarcharForAllNotNullAndVarcharType() {
            types.add(StringType("varchar", 6))
            types.add(StringType("varchar", 3))
            types.add(NullType("varchar"))
            val type = types.infer()
            assertEquals("varchar", type.name)
            assertEquals(6, type.size)
        }

        @Test
        fun shouldInferToVarcharTypeIfOneOfTheTypeIsVarcharType() {
            types.add(StringType("varchar", 6))
            types.add(StringType("varchar", 3))
            types.add(NumberType("NUMBER", 3))
            types.add(NumberType("BOOLEAN", 3))
            types.add(NullType("varchar"))
            val type = types.infer()
            assertEquals("varchar", type.name)
            assertEquals(6, type.size)
        }
    }

    @Nested
    class NumberTypeTest {
        private val types = Types()
        @Test
        fun shouldInferToNumberTypeIfAllTypeIsNumber() {
            types.add(NumberType("NUMBER", 3, 2))
            types.add(NumberType("NUMBER", 3, 5))
            types.add(NumberType("NUMBER", 3))
            val inferredType = types.infer()
            assertEquals("NUMBER", inferredType.name)
            assertEquals(3, inferredType.size)
            assertEquals(5, inferredType.precision)
            assertFalse(inferredType.isNullable)
        }

        @Test
        fun shouldInferToNumberTypeNullableForMixOfNumberTypeAndNullType() {
            types.add(NumberType("NUMBER", 3, 2))
            types.add(NumberType("NUMBER", 3, 5))
            types.add(NullType("string"))
            val inferredType = types.infer()
            assertEquals("NUMBER", inferredType.name)
            assertEquals(3, inferredType.size)
            assertEquals(5, inferredType.precision)
            assertTrue(inferredType.isNullable)
        }
    }
}