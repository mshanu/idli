package com.idli.datatypes.oracle

import com.idli.datatypes.InferType
import com.idli.extensions.isBoolean
import com.idli.extensions.isNumber
import com.idli.model.*

class OracleTypes : InferType {
    override fun infer(value: String): BaseType {
        if (value.isEmpty()) {
            return NullType("VARCHAR2")
        }
        if (value.isNumber()) {
            val split = value.replace(",", "").split(".")
            return if (split.count() > 1) {
                NumberType("NUMBER", split.first().length, split.drop(0).first().length)
            } else {
                NumberType("NUMBER", split.first().length)
            }

        }
        if (value.isBoolean()) {
            return BooleanType("BOOLEAN")
        }
        return StringType("varchar2", value.length)
    }
}