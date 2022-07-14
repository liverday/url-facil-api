package com.liverday.url.facil.infrastructure.adapters.factories

import com.liverday.url.facil.domain.urlClick.UrlClick
import com.liverday.url.facil.application.ports.factories.url.UrlClickFactory
import com.liverday.url.facil.application.ports.usecases.url.CreateUrlClickRequest
import com.liverday.url.facil.domain.Either
import com.liverday.url.facil.domain.exceptions.DomainCreationException
import com.liverday.url.facil.domain.urlClick.UrlClickID

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