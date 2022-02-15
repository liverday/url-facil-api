package com.liverday.url.facil.url.usecases.url

import com.liverday.url.facil.url.domain.url.entities.Url
import com.liverday.url.facil.url.domain.url.exceptions.TokenAlreadyExistException
import com.liverday.url.facil.url.ports.database.url.UrlDatabaseGateway
import com.liverday.url.facil.url.ports.factories.url.UrlFactory
import com.liverday.url.facil.url.ports.usecases.url.CreateUrlInputBoundary
import com.liverday.url.facil.url.ports.usecases.url.CreateUrlRequest
import com.liverday.url.facil.url.ports.usecases.url.CreateUrlTokenInputBoundary
import reactor.core.publisher.Mono

class CreateUrl(
        private val urlGateway: UrlDatabaseGateway,
        private val createUrlTokenInputBoundary: CreateUrlTokenInputBoundary,
        private val urlFactory: UrlFactory
) : CreateUrlInputBoundary {
    override fun execute(input: CreateUrlRequest): Mono<Url> {
        val url = urlFactory.create(input)

        val tokenMono = if (url.token == null) {
            createUrlTokenInputBoundary.execute()
        } else {
            Mono.just(url.token!!)
        }

        return tokenMono
                .flatMap { token ->
                    url.token = token
                    urlGateway.existsByToken(token)
                }
                .doOnNext { exists ->
                    if (exists) {
                        throw TokenAlreadyExistException()
                    }
                }
                .flatMap {
                    urlGateway.save(url)
                }
    }
}