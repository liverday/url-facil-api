package com.liverday.shortly.application.ports.factories.url

import com.liverday.shortly.application.ports.usecases.url.CreateUrlClickRequest
import com.liverday.shortlyl.domain.Either
import com.liverday.shortlyl.domain.exceptions.DomainCreationException
import com.liverday.shortlyl.domain.urlClick.UrlClick

interface UrlClickFactory {
    fun create(request: CreateUrlClickRequest): Either<DomainCreationException, UrlClick>
}