package com.liverday.shortly.application.ports.factories.url

import com.liverday.shortly.domain.url.Url
import com.liverday.shortly.application.ports.usecases.url.CreateUrlRequest
import com.liverday.shortly.domain.Either
import com.liverday.shortly.domain.exceptions.DomainCreationException

interface UrlFactory {
    fun create(request: CreateUrlRequest): com.liverday.shortly.domain.Either<com.liverday.shortly.domain.exceptions.DomainCreationException, com.liverday.shortly.domain.url.Url>
}