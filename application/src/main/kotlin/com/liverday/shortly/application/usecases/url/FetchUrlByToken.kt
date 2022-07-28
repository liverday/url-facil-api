package com.liverday.shortly.application.usecases.url

import com.liverday.shortly.domain.url.Url
import com.liverday.shortly.application.ports.database.url.UrlDatabaseGateway
import com.liverday.shortly.application.ports.publishers.UrlPublishMetadata
import com.liverday.shortly.application.ports.publishers.UrlPublisher
import com.liverday.shortly.application.ports.usecases.url.FetchUrlByTokenInputBoundary
import com.liverday.shortly.domain.exceptions.NotFoundException
import reactor.core.publisher.Mono

class FetchUrlByToken(
        private val urlDatabaseGateway: UrlDatabaseGateway,
        private val urlPublisher: UrlPublisher
) : FetchUrlByTokenInputBoundary {
    override fun execute(token: String, metadata: UrlPublishMetadata): Mono<com.liverday.shortly.domain.url.Url> {
        return urlDatabaseGateway
                .findUrlByToken(token)
                .flatMap {
                    urlPublisher.onUrlClicked(it, metadata)
                }
                .switchIfEmpty(Mono.error(com.liverday.shortly.domain.exceptions.NotFoundException.with(com.liverday.shortly.domain.url.Url::class.java, "token", token)))
    }
}