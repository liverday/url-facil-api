package com.liverday.url.facil.domain.exceptions

import com.liverday.url.facil.domain.AggregateRoot
import com.liverday.url.facil.domain.Identifier

class NotFoundException(
        message: String,
        errors: List<Error>
) : DomainException(message, errors) {

    companion object {
        fun <T : AggregateRoot<*>> with(clazz: Class<T>, id: Identifier): NotFoundException {
            val message = "${clazz.simpleName} with ID ${id.getValue()} was not found"
            return NotFoundException(message, emptyList())
        }
    }
}