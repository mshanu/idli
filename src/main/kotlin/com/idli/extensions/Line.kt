package com.idli.extensions

import com.idli.datatypes.InferType
import com.idli.model.Column
import com.idli.model.ColumnBuilder

fun String.parseLineToColumns(inferType: InferType): List<ColumnBuilder> {
    return this.split(",").map { ColumnBuilder(it, inferType) }
}

fun String.parseLineToColumnValues(columnBuilder: List<ColumnBuilder>): List<ColumnBuilder> {
    return columnBuilder.zip(this.split(","))
        .map { (columnBuild, eachValue) -> columnBuild.inferType(eachValue) }

}