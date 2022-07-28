package com.liverday.shortly.domain.exceptions

class EntityAlreadyExistsException(
        message: String,
        errors: List<com.liverday.shortly.domain.exceptions.Error>
) : com.liverday.shortly.domain.exceptions.DomainException(message, errors) {
    companion object {
        private const val DEFAULT_MESSAGE = "The entity already exists with the sent arguments"
        fun with(message: String = com.liverday.shortly.domain.exceptions.EntityAlreadyExistsException.Companion.DEFAULT_MESSAGE): com.liverday.shortly.domain.exceptions.EntityAlreadyExistsException {
            return com.liverday.shortly.domain.exceptions.EntityAlreadyExistsException(message, emptyList())
        }
    }
}