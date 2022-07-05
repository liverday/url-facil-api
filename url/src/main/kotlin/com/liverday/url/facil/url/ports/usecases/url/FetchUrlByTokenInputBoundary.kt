package com.liverday.url.facil.url.ports.usecases.url

import com.liverday.url.facil.domain.url.entities.Url
import com.liverday.url.facil.url.ports.publishers.UrlPublishMetadata
import reactor.core.publisher.Mono

interface FetchUrlByTokenInputBoundary {
    fun execute(token: String, metadata: UrlPublishMetadata): Mono<Url>
}