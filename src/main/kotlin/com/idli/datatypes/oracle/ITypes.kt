package com.idli.datatypes.oracle

import com.idli.model.*

interface ITypes {
    fun nullType(): NullType
    fun numberType(precision: Int, scale: Int? = null): NumberType
    fun booleanType(): BooleanType
    fun stringType(size: Int): StringType
    fun dateTimeType(): DateTimeType
    fun dateType(): DateType
}