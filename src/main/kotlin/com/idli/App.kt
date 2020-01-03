package com.idli

import com.idli.datatypes.oracle.OracleTypes
import com.idli.extensions.parseLineToColumnValues
import com.idli.extensions.parseLineToColumns
import java.io.File

fun main() {
    val randomLines = File("<<PathToFile>>")
        .bufferedReader().useLines { lines ->
            lines.filterIndexed { index, _ -> index == 0 || index % 23 == 0 }.toList()
        }
    val columnsBuilders = randomLines.first().parseLineToColumns(OracleTypes())
    val columns =
        randomLines.drop(1).fold(columnsBuilders, operation = { acc, s -> s.parseLineToColumnValues(acc) })
            .map { it.build() }
    print(columns.map { it.createStatement() })
}