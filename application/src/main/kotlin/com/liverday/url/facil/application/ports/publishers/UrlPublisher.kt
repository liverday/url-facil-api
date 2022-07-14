package com.liverday.url.facil.application.ports.publishers

import com.liverday.url.facil.domain.url.entities.Url
import reactor.core.publisher.Mono

interface UrlPublisher {
    fun onUrlClicked(url: Url, metadata: UrlPublishMetadata): Mono<Url>
}