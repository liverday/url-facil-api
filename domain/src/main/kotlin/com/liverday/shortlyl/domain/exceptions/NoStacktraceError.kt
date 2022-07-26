package com.liverday.shortlyl.domain.exceptions

open class NoStacktraceError : RuntimeException {
    constructor(message: String): super(message, null)
    constructor(message: String, cause: Throwable): super(message, cause)
}