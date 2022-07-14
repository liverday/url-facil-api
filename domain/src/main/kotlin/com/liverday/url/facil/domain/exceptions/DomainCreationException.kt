package com.liverday.url.facil.domain.exceptions

class DomainCreationException(
        message: String,
        errors: List<Error>
) : DomainException(message, errors) {
   companion object {
       private const val DEFAULT_MESSAGE = "The domain validation failed"
       fun with(message: String = DEFAULT_MESSAGE, errors: List<Error>): DomainCreationException {
           return DomainCreationException(message, errors)
       }
   }
}