package com.liverday.shortly.infrastructure.adapters.factories

import com.liverday.shortlyl.domain.url.Url
import com.liverday.shortly.application.ports.factories.url.UrlFactory
import com.liverday.shortly.application.ports.usecases.url.CreateUrlRequest
import com.liverday.shortlyl.domain.Either
import com.liverday.shortlyl.domain.exceptions.DomainCreationException
import com.liverday.shortlyl.domain.url.UrlID

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