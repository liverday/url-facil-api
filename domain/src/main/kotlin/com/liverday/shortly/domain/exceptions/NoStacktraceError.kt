package com.liverday.shortly.domain.exceptions

open class NoStacktraceError : RuntimeException {
    constructor(message: String): super(message, null)
    constructor(message: String, cause: Throwable): super(message, cause)
}