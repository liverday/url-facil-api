package com.liverday.url.facil.domain.exceptions

import com.liverday.url.facil.domain.AggregateRoot

class EntityAlreadyExistsException(
        message: String,
        errors: List<Error>
) : DomainException(message, errors) {
    companion object {
        fun <T : AggregateRoot<*>> with(clazz: Class<T>): EntityAlreadyExistsException {
            val message = "${clazz.simpleName} already exists with the following arguments"
            return EntityAlreadyExistsException(message, emptyList())
        }
    }
}