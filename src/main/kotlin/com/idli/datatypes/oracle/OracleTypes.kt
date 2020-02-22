package com.idli.datatypes.oracle

import com.idli.model.BooleanType
import com.idli.model.NullType
import com.idli.model.NumberType
import com.idli.model.StringType

class OracleTypes :  ITypes {
    override fun nullType(): NullType = NullType("VARCHAR2")
    override fun numberType(precision: Int, scale: Int?) = NumberType("NUMBER", precision, scale)
    override fun booleanType() = BooleanType("BOOLEAN")
    override fun stringType(size: Int) = StringType("VARCHAR2", size)
}