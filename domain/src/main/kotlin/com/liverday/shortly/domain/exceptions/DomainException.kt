package com.liverday.shortly.domain.exceptions

open class DomainException(
        message: String,
        val errors: List<Error>
) : com.liverday.shortly.domain.exceptions.NoStacktraceError(message) {

    companion object {
        fun with(errors: List<Error>): DomainException = DomainException("", errors)
        fun with(error: Error): DomainException = DomainException(error.message, listOf(error));
    }
}