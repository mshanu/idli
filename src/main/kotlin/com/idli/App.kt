package com.idli

import com.idli.datatypes.oracle.OracleTypes
import com.idli.extensions.parseLineToColumnValues
import com.idli.extensions.parseLineToColumns
import com.idli.model.ColumnBuilder
import com.idli.model.Table
import java.io.File

fun main() {
    val randomLines = File("<<PathToFile>>")
        .bufferedReader().useLines {
            it.filterIndexed { index, _ -> index == 0 || index % 23 == 0 }.toList()
        }
    val columnsBuilders = randomLines.first().parseLineToColumns(OracleTypes())
    val columns =
        randomLines.drop(1).fold(columnsBuilders, operation = { acc, s -> s.parseLineToColumnValues(acc) })
            .map(ColumnBuilder::build)
    print(Table("sample", columns).createStatement())
}