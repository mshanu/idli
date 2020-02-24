package com.idli.datatypes.oracle

import com.idli.model.BooleanType
import com.idli.model.NullType
import com.idli.model.NumberType
import com.idli.model.StringType

class MySqlTypes : ITypes {
    override fun nullType(): NullType {
        return NullType("VARCHAR")
    }

    override fun numberType(precision: Int, scale: Int?): NumberType {
        if (scale != null && scale > 0) {
            return NumberType("DECIMAL", precision, scale)
        }
        return NumberType("INT")
    }

    override fun booleanType(): BooleanType {
        return BooleanType("BOOLEAN")
    }

    override fun stringType(size: Int): StringType {
        return StringType("VARCHAR", size)
    }
}