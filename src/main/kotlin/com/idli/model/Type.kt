package com.idli.model

data class Type(val name: String, val size: Int? = null, val precision: Int? = null, val isNullable: Boolean = true) {
    override fun toString(): String {
        val statementBuilder = StringBuilder()
        statementBuilder.append(name)
        size?.let {
            statementBuilder.append("($size")
            precision?.let {
                statementBuilder.append(",$precision")
            }
            statementBuilder.append(")")
        }
        if (!isNullable) {
            statementBuilder.append(" ")
            statementBuilder.append("NOT NULL")
        }
        return statementBuilder.toString()
    }
}

