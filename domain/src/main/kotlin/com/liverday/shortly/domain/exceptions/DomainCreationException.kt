package com.liverday.shortly.domain.exceptions

class DomainCreationException(
        message: String,
        errors: List<com.liverday.shortly.domain.exceptions.Error>
) : com.liverday.shortly.domain.exceptions.DomainException(message, errors) {
   companion object {
       private const val DEFAULT_MESSAGE = "The domain validation failed"
       fun with(message: String = com.liverday.shortly.domain.exceptions.DomainCreationException.Companion.DEFAULT_MESSAGE, errors: List<com.liverday.shortly.domain.exceptions.Error>): com.liverday.shortly.domain.exceptions.DomainCreationException {
           return com.liverday.shortly.domain.exceptions.DomainCreationException(message, errors)
       }
   }
}