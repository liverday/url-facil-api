package com.liverday.shortly.application.ports.factories.url

import com.liverday.shortly.application.ports.usecases.urlClick.CreateUrlClickRequest
import com.liverday.shortly.domain.Either
import com.liverday.shortly.domain.exceptions.DomainCreationException
import com.liverday.shortly.domain.urlClick.UrlClick

interface UrlClickFactory {
    fun create(request: CreateUrlClickRequest): Either<DomainCreationException, UrlClick>
}