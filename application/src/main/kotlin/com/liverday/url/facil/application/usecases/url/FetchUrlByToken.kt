package com.liverday.url.facil.application.usecases.url

import com.liverday.url.facil.domain.url.Url
import com.liverday.url.facil.application.ports.database.url.UrlDatabaseGateway
import com.liverday.url.facil.application.ports.publishers.UrlPublishMetadata
import com.liverday.url.facil.application.ports.publishers.UrlPublisher
import com.liverday.url.facil.application.ports.usecases.url.FetchUrlByTokenInputBoundary
import com.liverday.url.facil.domain.exceptions.NotFoundException
import reactor.core.publisher.Mono

class FetchUrlByToken(
        private val urlDatabaseGateway: UrlDatabaseGateway,
        private val urlPublisher: UrlPublisher
) : FetchUrlByTokenInputBoundary {
    override fun execute(token: String, metadata: UrlPublishMetadata): Mono<Url> {
        return urlDatabaseGateway
                .findUrlByToken(token)
                .flatMap {
                    urlPublisher.onUrlClicked(it, metadata)
                }
                .switchIfEmpty(Mono.error(NotFoundException.with(Url::class.java, "token", token)))
    }
}