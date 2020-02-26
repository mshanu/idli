package com.idli.extensions

fun String.isNumber(): Boolean {
    return this.matches(Regex("-?[\\d,]*\\d(\\.\\d+)?"))
}

fun String.isBoolean(): Boolean {
    return this.equals("true", ignoreCase = true) || this.equals("false", ignoreCase = true)
}

fun String.isDateTime(): Boolean {
    val dateTimePatterns = listOf(
        Regex("\\d{2}[\\/,-]\\d{2}[\\/,-]\\d{4}([T\\s]\\d{2}:\\d{2}:\\d{2}.*)"),
        Regex("\\d{4}[\\/,-]\\d{2}[\\/,-]\\d{2}([T\\s]\\d{2}:\\d{2}:\\d{2}.*)")
    )
    return dateTimePatterns.any { this.matches(it) }
}

fun String.isDate(): Boolean {
    val datePatterns = listOf(
        Regex("\\d{2}[\\/,-]\\d{2}[\\/,-]\\d{4}"),
        Regex("\\d{4}[\\/,-]\\d{2}[\\/,-]\\d{2}")
    )
    return datePatterns.any { this.matches(it) }
}