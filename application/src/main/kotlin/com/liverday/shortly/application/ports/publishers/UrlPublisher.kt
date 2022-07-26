package com.liverday.shortly.application.ports.publishers

import com.liverday.shortlyl.domain.url.Url
import reactor.core.publisher.Mono

interface UrlPublisher {
    fun onUrlClicked(url: Url, metadata: UrlPublishMetadata): Mono<Url>
}