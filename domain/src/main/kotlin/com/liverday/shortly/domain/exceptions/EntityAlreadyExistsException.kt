package com.liverday.shortly.domain.exceptions

class EntityAlreadyExistsException(
        message: String,
        errors: List<com.liverday.shortly.domain.exceptions.Error>
) : com.liverday.shortly.domain.exceptions.DomainException(message, errors) {
    companion object {
        private const val DEFAULT_MESSAGE = "The entity already exists with the sent arguments"
        fun with(message: String = DEFAULT_MESSAGE): EntityAlreadyExistsException {
            return EntityAlreadyExistsException(message, emptyList())
        }
    }
}