package com.idli

import com.idli.datatypes.oracle.ITypes
import com.idli.datatypes.oracle.TypeInfer
import com.idli.extensions.parseLineToColumnValues
import com.idli.extensions.parseLineToColumns
import com.idli.model.Column
import com.idli.model.ColumnBuilder
import com.idli.model.Table
import java.io.File

fun main(args: Array<String>) {
    val (file, types, delimiter) = try {
        Arguments.parse(args)
    } catch (e: Exception) {
        print(e.message)
        return
    }

    do {
        println("Please select ")
        println("1. See the columns and derived type")
        println("2. Generate create statement")
        println("3. Exit")
        val option = readLine()!!.split('.').first().toInt()
        println()
        when (option) {
            1 -> {
                val derivedColumns = deriveColumns(file, types, delimiter)
                val maxLength = derivedColumns.map { it.name.length }.max()
                derivedColumns.forEachIndexed { index, it ->
                    println(
                        java.lang.String.format(
                            "%-3d | %-${maxLength}s | %-24s | %-50s |%n",
                            index + 1,
                            it.name,
                            it.baseType,
                            it.sampleData.take(5).map { it.data }.joinToString()
                        )
                    )
                }
            }
            2 -> print(Table("sample", deriveColumns(file, types, delimiter)).createStatement())
        }
        println()
    } while (option != 3)


}


private fun deriveColumns(
    file: File,
    types: ITypes,
    delimiter: String
): List<Column> {
    val randomLines = file
        .bufferedReader().useLines {
            it.filterIndexed { index, _ -> index == 0 || index % 23 == 0 }.toList()
        }
    val columnsBuilders = randomLines.first().parseLineToColumns(TypeInfer(types), delimiter)
    return randomLines.drop(1).fold(columnsBuilders, operation = { acc, s -> s.parseLineToColumnValues(acc, delimiter) })
        .map(ColumnBuilder::build)
}