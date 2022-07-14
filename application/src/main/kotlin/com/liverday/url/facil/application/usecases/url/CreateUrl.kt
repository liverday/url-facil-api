package com.liverday.url.facil.application.usecases.url

import com.liverday.url.facil.domain.url.Url
import com.liverday.url.facil.application.ports.database.url.UrlDatabaseGateway
import com.liverday.url.facil.application.ports.factories.url.UrlFactory
import com.liverday.url.facil.application.ports.usecases.url.CreateUrlInputBoundary
import com.liverday.url.facil.application.ports.usecases.url.CreateUrlRequest
import com.liverday.url.facil.application.ports.usecases.url.CreateUrlTokenInputBoundary
import com.liverday.url.facil.domain.exceptions.EntityAlreadyExistsException
import reactor.core.publisher.Mono

class CreateUrl(
        private val urlGateway: UrlDatabaseGateway,
        private val createUrlTokenInputBoundary: CreateUrlTokenInputBoundary,
        private val urlFactory: UrlFactory
) : CreateUrlInputBoundary {
    override fun execute(input: CreateUrlRequest): Mono<Url> {
        return Mono.fromCallable {
            val either = urlFactory.create(input)

            if (either.isLeft()) {
                throw either.getLeft()
            }

            either.getRight()
        }.flatMap { url ->
            Mono.fromCallable {
                if (url.token == null) {
                    createUrlTokenInputBoundary.execute()
                } else {
                    Mono.just(url.token!!)
                }
            }.flatMap { token -> token }.map { token ->
                url.token = token
                url
            }
        }.flatMap { url ->
            urlGateway.existsByToken(url.token)
                    .map { exists ->
                        if (exists) {
                            throw EntityAlreadyExistsException.with("The URL token sent already exists")
                        }
                    }
                    .map {
                        url
                    }
        }.flatMap { url ->
            urlGateway.save(url)
        }
    }
}