package com.liverday.shortly.infrastructure.adapters.factories

import com.liverday.shortly.domain.urlClick.UrlClick
import com.liverday.shortly.application.ports.factories.url.UrlClickFactory
import com.liverday.shortly.application.ports.usecases.url.CreateUrlClickRequest
import com.liverday.shortly.domain.Either
import com.liverday.shortly.domain.exceptions.DomainCreationException
import com.liverday.shortly.domain.urlClick.UrlClickID

class CommonUrlClickFactory : UrlClickFactory {
    override fun create(request: CreateUrlClickRequest): com.liverday.shortly.domain.Either<com.liverday.shortly.domain.exceptions.DomainCreationException, UrlClick> {
        val urlClick = UrlClick.newUrlClick(
                request.url.id,
                request.platform,
                request.device,
                request.browser,
                ""
        )

        val errors = urlClick.validate()

        if (errors.isNotEmpty()) {
            return com.liverday.shortly.domain.Either.Left(com.liverday.shortly.domain.exceptions.DomainCreationException.with(errors = errors))
        }

        return com.liverday.shortly.domain.Either.Right(urlClick)
    }
}