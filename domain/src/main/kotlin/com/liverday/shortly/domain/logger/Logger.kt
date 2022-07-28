package com.liverday.shortly.domain.logger

interface Logger {
    fun info(message: String, vararg metadata: Any)
    fun warn(message: String, vararg metadata: Any)
    fun error(message: String, vararg metadata: Any)
    fun debug(message: String, vararg metadata: Any)
}