package com.idli.model

data class Column(
    val name: String,
    val baseType: Type,
    val sampleData: List<SampleData>
) {
    fun createStatement(): String {
        return "${name.toLowerCase().replace(" ", "_")} $baseType"
    }
}
