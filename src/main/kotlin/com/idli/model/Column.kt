package com.idli.model

data class Column(val name: String, val baseType: Type) {
    fun createStatement(): String {
        return "${name.toLowerCase().replace(" ", "_")} $baseType"
    }
}
