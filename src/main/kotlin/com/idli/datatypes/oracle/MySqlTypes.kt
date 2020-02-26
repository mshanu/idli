package com.idli.datatypes.oracle

import com.idli.model.*

class MySqlTypes : ITypes {
    override fun dateTimeType(): DateTimeType = DateTimeType("DATETIME")

    override fun dateType(): DateType = DateType("DATE")

    override fun nullType(): NullType = NullType("VARCHAR")

    override fun numberType(precision: Int, scale: Int?): NumberType {
        if (scale != null && scale > 0) {
            return NumberType("DECIMAL", precision, scale)
        }
        return NumberType("INT")
    }

    override fun booleanType(): BooleanType = BooleanType("BOOLEAN")
    override fun stringType(size: Int): StringType = StringType("VARCHAR", size)
}