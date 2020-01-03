package com.idli.model

sealed class BaseType(open val name: String)
data class NullType(val fallBackType: String) : BaseType(fallBackType)
data class StringType(override val name: String, val size: Int) : BaseType(name)
data class NumberType(
    override val name: String,
    val precision: Int,
    val scale: Int? = null
) : BaseType(name)

data class BooleanType(override val name: String) : BaseType(name)

