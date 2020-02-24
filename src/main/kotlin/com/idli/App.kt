package com.idli

import com.idli.datatypes.oracle.MySqlTypes
import com.idli.datatypes.oracle.TypeInfer
import com.idli.extensions.isNumber
import com.idli.extensions.parseLineToColumnValues
import com.idli.extensions.parseLineToColumns
import com.idli.model.ColumnBuilder
import com.idli.model.Table
import kotliquery.queryOf
import kotliquery.sessionOf
import java.io.File

fun main() {
    val file = File("<<PathToFile>>")
    val randomLines = file
        .bufferedReader().useLines {
            it.filterIndexed { index, _ -> index == 0 || index % 23 == 0 }.toList()
        }
    val columnsBuilders = randomLines.first().parseLineToColumns(TypeInfer(MySqlTypes()))
    val columns =
        randomLines.drop(1).fold(columnsBuilders, operation = { acc, s -> s.parseLineToColumnValues(acc) })
            .map(ColumnBuilder::build)

    print(Table("sample", columns).createStatement())
    val session = sessionOf("jdbc:mysql://localhost:3306/sample", "sample_user", "samplepassword")
    session.execute(queryOf("drop table samples"))
    session.execute(queryOf(Table("samples", columns).createStatement()))
    val remaining = file.bufferedReader().useLines {
        it.toList()
    }.drop(1)
    remaining.forEach { s ->

        val query =
            "insert into samples(${columns.joinToString(separator = ",") { it.name }}) values (${columns.joinToString(
                separator = ","
            ) { ":${it.name}" }})"
        val hashMap = columns.map { it.name }.zip(s.split(",").map { if (it.isEmpty()) null else if(it.isNumber()) it.toDouble() else it }).toMap()
        println(query)
        println(hashMap)

        session.execute(queryOf(query, hashMap))
    }
}