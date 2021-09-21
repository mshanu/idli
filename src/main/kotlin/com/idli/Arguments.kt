package com.idli

import com.idli.datatypes.dbs.ITypes
import com.idli.datatypes.dbs.MySqlTypes
import com.idli.datatypes.dbs.OracleTypes
import com.idli.datatypes.dbs.PostgresTypes
import java.io.File

data class Arguments(val file: File, val types: ITypes, val delimiter: String) {
    companion object {
        private val types = mapOf(
            "mysql" to MySqlTypes(),
            "oracle" to OracleTypes(),
            "pg" to PostgresTypes()
        )

        fun parse(args: Array<String>): Arguments {
            val argsMap = args.map { it.split("=") }.map { it.first() to it.last() }.toMap()
            val filePath = argsMap["file"]
                ?: throw Exception("file argument not specified.")
            val db = argsMap["db"]
                ?: "mysql"
            if (!types.containsKey(db)) throw Exception("Specified db does not exists.")
            val delimiter = argsMap.getOrDefault("delimiter", ",")
            return Arguments(File(filePath), types.getValue(db), delimiter)
        }
    }
}