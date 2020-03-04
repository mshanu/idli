package com.idli.extensions

import com.idli.datatypes.InferType
import com.idli.model.ColumnBuilder

fun String.parseLineToColumns(inferType: InferType, delimiter: String): List<ColumnBuilder> {
    return this.split(delimiter).map { ColumnBuilder(it, inferType) }
}

fun String.splitLineToValues(delimiter: String): List<String> {
    return this.split(Regex("\\$delimiter(?=(?:[^\\\"]*\\\"[^\\\"]*\\\")*[^\\\"]*\$)")).map { it.replace("\"", "") }
}

fun String.parseLineToColumnValues(
    columnBuilder: List<ColumnBuilder>,
    delimiter: String
): List<ColumnBuilder> {
    return columnBuilder.zip(this.splitLineToValues(delimiter))
        .map { (columnBuild, eachValue) -> columnBuild.inferType(eachValue) }

}