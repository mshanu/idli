package com.idli.datatypes.oracle

import com.idli.model.*

class OracleTypes : ITypes {
    override fun dateTimeType() = DateTimeType("DATETIME")
    override fun dateType() = DateType("DATE")
    override fun nullType(): NullType = NullType("VARCHAR2")
    override fun numberType(precision: Int, scale: Int?) = NumberType("NUMBER", precision, scale)
    override fun booleanType() = BooleanType("BOOLEAN")
    override fun stringType(size: Int) = StringType("VARCHAR2", size)
}