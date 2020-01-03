package com.idli.model

data class Table(val name: String, val columns: List<Column>) {
    fun createStatement(): String {
        return "create table $name ( ${columns.map { it.createStatement() }.joinToString()} )"
    }
}

