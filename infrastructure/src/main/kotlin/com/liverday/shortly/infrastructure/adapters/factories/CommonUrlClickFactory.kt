package com.liverday.shortly.infrastructure.adapters.factories

import com.liverday.shortlyl.domain.urlClick.UrlClick
import com.liverday.shortly.application.ports.factories.url.UrlClickFactory
import com.liverday.shortly.application.ports.usecases.url.CreateUrlClickRequest
import com.liverday.shortlyl.domain.Either
import com.liverday.shortlyl.domain.exceptions.DomainCreationException
import com.liverday.shortlyl.domain.urlClick.UrlClickID

class CommonUrlClickFactory : UrlClickFactory {
    override fun create(request: CreateUrlClickRequest): Either<DomainCreationException, UrlClick> {
        val urlClick = UrlClick(
                id = UrlClickID.unique(),
                urlId = request.url.id,
                platform = request.platform,
                device = request.device,
                browser = request.browser,
                country = ""
        )

        val errors = urlClick.validate()

        if (errors.isNotEmpty()) {
            return Either.Left(DomainCreationException.with(errors = errors))
        }

        return Either.Right(urlClick)
    }
}