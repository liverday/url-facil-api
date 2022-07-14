package com.liverday.url.facil.application.ports.factories.url

import com.liverday.url.facil.application.ports.usecases.url.CreateUrlClickRequest
import com.liverday.url.facil.domain.Either
import com.liverday.url.facil.domain.exceptions.DomainCreationException
import com.liverday.url.facil.domain.urlClick.UrlClick

interface UrlClickFactory {
    fun create(request: CreateUrlClickRequest): Either<DomainCreationException, UrlClick>
}