package com.idli.datatypes

import com.idli.model.*

class Types {
    private val types = ArrayList<BaseType>()
    fun add(baseType: BaseType) {
        types.add(baseType)
    }

    fun infer(): Type {
        val allNonNullTypes = types.filter { it !is NullType }
        val isNumberType = allNonNullTypes.isNotEmpty() && allNonNullTypes.all { it is NumberType }
        val isBooleanType = allNonNullTypes.isNotEmpty() && allNonNullTypes.all { it is BooleanType }
        if (isBooleanType) return Type(allNonNullTypes.first { it is BooleanType }.name)
        val maxLength = types.map {
            when (it) {
                is StringType -> it.size
                is NumberType -> it.precision
                else -> 0
            }
        }.max()

        if (isNumberType) return Type(
            types.first { it is NumberType }.name,
            maxLength,
            allNonNullTypes.map { if (it is NumberType && it.scale != null) it.scale else 0 }.max(),
            types.any { it is NullType }
        )
        return Type(types.first().name, maxLength)
    }
}