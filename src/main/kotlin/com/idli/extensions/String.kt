package com.idli.extensions

fun String.isNumber(): Boolean {
    return this.matches(Regex("-?[\\d,]+\\d(\\.\\d+)?"))
}

fun String.isBoolean(): Boolean {
    return this.equals("true", ignoreCase = true) || this.equals("false", ignoreCase = true)
}