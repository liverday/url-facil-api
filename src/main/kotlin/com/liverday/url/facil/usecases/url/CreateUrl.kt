package com.liverday.url.facil.usecases.url

import com.liverday.url.facil.domain.url.entities.Url
import com.liverday.url.facil.domain.url.exceptions.TokenAlreadyExistException
import com.liverday.url.facil.ports.database.url.UrlGateway
import com.liverday.url.facil.ports.factories.url.UrlFactory
import com.liverday.url.facil.ports.usecases.url.CreateUrlInputBoundary
import com.liverday.url.facil.ports.usecases.url.CreateUrlRequest
import reactor.core.publisher.Mono

class CreateUrl(
        private val urlGateway: UrlGateway,
        private val urlFactory: UrlFactory
) : CreateUrlInputBoundary {
    override fun execute(input: CreateUrlRequest): Mono<Url> {
        val url = urlFactory.create(input)

        return urlGateway.existsByToken(url.token)
                .doOnNext { exists ->
                    if (exists) {
                        throw TokenAlreadyExistException()
                    }
                }
                .flatMap {
                    urlGateway.create(url)
                }
    }
}