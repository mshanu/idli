package com.idli

import com.idli.datatypes.oracle.TypeInfer
import com.idli.extensions.parseLineToColumnValues
import com.idli.extensions.parseLineToColumns
import com.idli.model.ColumnBuilder
import com.idli.model.Table

fun main(args: Array<String>) {
    val (file, types) = try {
        Arguments.parse(args)
    } catch (e: Exception) {
        print(e.message)
        return
    }
    val randomLines = file
        .bufferedReader().useLines {
            it.filterIndexed { index, _ -> index == 0 || index % 23 == 0 }.toList()
        }
    val columnsBuilders = randomLines.first().parseLineToColumns(TypeInfer(types))
    val columns =
        randomLines.drop(1).fold(columnsBuilders, operation = { acc, s -> s.parseLineToColumnValues(acc) })
            .map(ColumnBuilder::build)

    print(Table("sample", columns).createStatement())
}