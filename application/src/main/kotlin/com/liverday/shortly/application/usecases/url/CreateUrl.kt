package com.liverday.shortly.application.usecases.url

import com.liverday.shortly.domain.url.Url
import com.liverday.shortly.application.ports.database.url.UrlDatabaseGateway
import com.liverday.shortly.application.ports.factories.url.UrlFactory
import com.liverday.shortly.application.ports.usecases.url.CreateUrlInputBoundary
import com.liverday.shortly.application.ports.usecases.url.CreateUrlRequest
import com.liverday.shortly.application.ports.usecases.url.CreateUrlTokenInputBoundary
import com.liverday.shortly.domain.exceptions.EntityAlreadyExistsException
import reactor.core.publisher.Mono

class CreateUrl(
        private val urlGateway: UrlDatabaseGateway,
        private val createUrlTokenInputBoundary: CreateUrlTokenInputBoundary,
        private val urlFactory: UrlFactory
) : CreateUrlInputBoundary {
    override fun execute(input: CreateUrlRequest): Mono<com.liverday.shortly.domain.url.Url> {
        return Mono.fromCallable {
            val either = urlFactory.create(input)

            if (either.isLeft()) {
                throw either.getLeft()
            }

            either.getRight()
        }.flatMap { url ->
            val tokenMono = if (url.token == null) {
                createUrlTokenInputBoundary.execute()
            } else {
                Mono.just(url.token!!)
            }

            tokenMono.map { token ->
                url.token = token
                url
            }
        }.flatMap { url ->
            urlGateway.existsByToken(url.token)
                    .map { exists ->
                        if (exists) {
                            throw com.liverday.shortly.domain.exceptions.EntityAlreadyExistsException.with("The URL token sent already exists")
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