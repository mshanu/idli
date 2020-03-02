package com.idli.extensions

import com.idli.datatypes.InferType
import com.idli.model.ColumnBuilder

fun String.parseLineToColumns(inferType: InferType): List<ColumnBuilder> {
    return this.split(",").map { ColumnBuilder(it, inferType) }
}

fun String.splitLineToValues(delimiter: String): List<String> {
    return this.split(Regex(",(?=(?:[^\\\"]*\\\"[^\\\"]*\\\")*[^\\\"]*\$)")).map { it.replace("\"", "") }

}

fun String.parseLineToColumnValues(columnBuilder: List<ColumnBuilder>): List<ColumnBuilder> {
    return columnBuilder.zip(this.splitLineToValues(","))
        .map { (columnBuild, eachValue) -> columnBuild.inferType(eachValue) }

}