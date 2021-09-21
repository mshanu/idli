package com.idli.datatypes.dbs

import com.idli.datatypes.InferType
import com.idli.extensions.isBoolean
import com.idli.extensions.isDate
import com.idli.extensions.isDateTime
import com.idli.extensions.isNumber
import com.idli.model.BaseType

class TypeInfer(private val type: ITypes) : InferType {
    override fun infer(value: String): BaseType {
        if (value.isEmpty()) {
            return type.nullType()
        }
        if (value.isNumber()) {
            val split = value.replace(",", "").split(".")
            return if (split.count() > 1) {
                type.numberType(split.first().length + split.last().length, split.drop(0).first().length)
            } else {
                type.numberType(split.first().length)
            }

        }
        if (value.isDateTime()) {
            return type.dateTimeType();
        }
        if (value.isDate()) {
            return type.dateType();
        }
        if (value.isBoolean()) {
            return type.booleanType()
        }
        return type.stringType(value.length)
    }
}