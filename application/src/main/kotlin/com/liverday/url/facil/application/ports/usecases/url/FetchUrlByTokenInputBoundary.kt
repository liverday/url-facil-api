package com.liverday.url.facil.application.ports.usecases.url

import com.liverday.url.facil.domain.url.Url
import com.liverday.url.facil.application.ports.publishers.UrlPublishMetadata
import reactor.core.publisher.Mono

interface FetchUrlByTokenInputBoundary {
    fun execute(token: String, metadata: UrlPublishMetadata): Mono<Url>
}