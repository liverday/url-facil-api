package com.liverday.shortly.infrastructure.adapters.factories

import com.liverday.shortly.domain.urlClick.UrlClick
import com.liverday.shortly.application.ports.factories.url.UrlClickFactory
import com.liverday.shortly.application.ports.usecases.urlClick.CreateUrlClickRequest
import com.liverday.shortly.domain.Either
import com.liverday.shortly.domain.exceptions.DomainCreationException

class CommonUrlClickFactory : UrlClickFactory {
    override fun create(request: CreateUrlClickRequest): Either<DomainCreationException, UrlClick> {
        val urlClick = UrlClick.newUrlClick(
                request.url.id,
                request.platform,
                request.device,
                request.browser,
                ""
        )

        val errors = urlClick.validate()

        if (errors.isNotEmpty()) {
            return Either.Left(DomainCreationException.with(errors = errors))
        }

        return Either.Right(urlClick)
    }
}