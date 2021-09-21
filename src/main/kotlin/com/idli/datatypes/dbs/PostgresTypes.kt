package com.idli.datatypes.dbs

import com.idli.model.*

class PostgresTypes : ITypes {
    override fun dateTimeType() = DateTimeType("TIMESTAMP")
    override fun dateType() = DateType("DATE")
    override fun nullType(): NullType = NullType("VARCHAR")
    override fun numberType(precision: Int, scale: Int?) = NumberType("NUMERIC", precision, scale)
    override fun booleanType() = BooleanType("BOOLEAN")
    override fun stringType(size: Int) = StringType("VARCHAR", size)
}