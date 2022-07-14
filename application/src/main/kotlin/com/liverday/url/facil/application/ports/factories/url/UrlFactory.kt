package com.liverday.url.facil.application.ports.factories.url

import com.liverday.url.facil.domain.url.Url
import com.liverday.url.facil.application.ports.usecases.url.CreateUrlRequest
import com.liverday.url.facil.domain.Either
import com.liverday.url.facil.domain.exceptions.DomainCreationException

interface UrlFactory {
    fun create(request: CreateUrlRequest): Either<DomainCreationException, Url>
}