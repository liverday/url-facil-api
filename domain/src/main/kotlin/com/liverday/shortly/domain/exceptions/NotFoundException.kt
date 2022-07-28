package com.liverday.shortly.domain.exceptions

import com.liverday.shortly.domain.AggregateRoot

class NotFoundException(
        message: String,
        errors: List<com.liverday.shortly.domain.exceptions.Error>
) : com.liverday.shortly.domain.exceptions.DomainException(message, errors) {
    companion object {
        fun <T : com.liverday.shortly.domain.AggregateRoot<*>> with(clazz: Class<T>, field: String, id: String): com.liverday.shortly.domain.exceptions.NotFoundException {
            val message = "${clazz.simpleName} with $field $id was not found"
            return com.liverday.shortly.domain.exceptions.NotFoundException(message, emptyList())
        }
    }
}