package com.liverday.shortly.application.ports.factories.url

import com.liverday.shortlyl.domain.url.Url
import com.liverday.shortly.application.ports.usecases.url.CreateUrlRequest
import com.liverday.shortlyl.domain.Either
import com.liverday.shortlyl.domain.exceptions.DomainCreationException

interface UrlFactory {
    fun create(request: CreateUrlRequest): Either<DomainCreationException, Url>
}