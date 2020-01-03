package com.idli.datatypes

import com.idli.model.BaseType

interface InferType {
    fun infer(value: String): BaseType
}