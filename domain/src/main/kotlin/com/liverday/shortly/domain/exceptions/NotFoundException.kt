package com.liverday.shortly.domain.exceptions

import com.liverday.shortly.domain.AggregateRoot

class NotFoundException(
        message: String,
        errors: List<Error>
) : com.liverday.shortly.domain.exceptions.DomainException(message, errors) {
    companion object {
        fun <T : AggregateRoot<*>> with(clazz: Class<T>, field: String, id: String): NotFoundException {
            val message = "${clazz.simpleName} with $field $id was not found"
            return NotFoundException(message, emptyList())
        }
    }
}