package com.idli.model

import com.idli.datatypes.InferType
import com.idli.datatypes.Types

class ColumnBuilder(private val name: String, private val infer: InferType) {
    private val sampleData = ArrayList<SampleData>()
    private val inferredTypes = Types()
    fun inferType(value: String): ColumnBuilder {
        val inferredType = infer.infer(value)
        sampleData.add(SampleData(value, inferredType))
        inferredTypes.add(inferredType)
        return this
    }

    fun build(): Column {
        return Column(name, inferredTypes.infer())
    }
}