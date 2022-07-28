package com.liverday.shortly.domain.exceptions

open class DomainException(
        message: String,
        val errors: List<com.liverday.shortly.domain.exceptions.Error>
) : com.liverday.shortly.domain.exceptions.NoStacktraceError(message) {

    companion object {
        fun with(errors: List<com.liverday.shortly.domain.exceptions.Error>): com.liverday.shortly.domain.exceptions.DomainException = com.liverday.shortly.domain.exceptions.DomainException("", errors)
        fun with(error: com.liverday.shortly.domain.exceptions.Error): com.liverday.shortly.domain.exceptions.DomainException = com.liverday.shortly.domain.exceptions.DomainException(error.message, listOf(error));
    }
}