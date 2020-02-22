package com.idli.datatypes.oracle

import com.idli.model.BooleanType
import com.idli.model.NullType
import com.idli.model.NumberType
import com.idli.model.StringType

interface ITypes {
    fun nullType(): NullType
    fun numberType(precision: Int, scale: Int? = null): NumberType
    fun booleanType(): BooleanType
    fun stringType(size: Int): StringType
}