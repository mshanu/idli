package com.idli

import com.idli.datatypes.oracle.ITypes
import com.idli.datatypes.oracle.MySqlTypes
import com.idli.datatypes.oracle.OracleTypes
import java.io.File

data class Arguments(val file: File, val types: ITypes) {
    companion object {
        val types = mapOf<String, ITypes>("mysql" to MySqlTypes(), "oracle" to OracleTypes())
        fun parse(args: Array<String>): Arguments {
            val filePath = args.find { it.contains(Regex("^file=.*")) }?.split("=")?.get(1)
                ?: throw Exception("file argument not specified.")
            val db = args.find { it.contains(Regex("^db=.*")) }?.split("=")?.get(1)?.toLowerCase()
                ?: "mysql"
            if (!types.containsKey(db)) throw Exception("Specified db does not exists.")
            return Arguments(File(filePath), types.getValue(db))
        }
    }
}