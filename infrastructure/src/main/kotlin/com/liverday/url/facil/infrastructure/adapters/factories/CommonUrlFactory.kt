package com.liverday.url.facil.infrastructure.adapters.factories

import com.liverday.url.facil.domain.url.Url
import com.liverday.url.facil.application.ports.factories.url.UrlFactory
import com.liverday.url.facil.application.ports.usecases.url.CreateUrlRequest
import com.liverday.url.facil.domain.Either
import com.liverday.url.facil.domain.exceptions.DomainCreationException
import com.liverday.url.facil.domain.url.UrlID

class CommonUrlFactory : UrlFactory {
    override fun create(request: CreateUrlRequest): Either<DomainCreationException, Url> {
        val url = Url(
                id = UrlID.unique(),
                link = request.link,
                token = request.token
        )

        val errors = url.validate()

        if (errors.isNotEmpty()) {
            return Either.Left(DomainCreationException.with(errors = errors))
        }

        return Either.Right(url)
    }
}