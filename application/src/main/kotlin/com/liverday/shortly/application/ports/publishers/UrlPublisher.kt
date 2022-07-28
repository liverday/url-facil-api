package com.liverday.shortly.application.ports.publishers

import com.liverday.shortly.domain.url.Url
import reactor.core.publisher.Mono

interface UrlPublisher {
    fun onUrlClicked(url: com.liverday.shortly.domain.url.Url, metadata: UrlPublishMetadata): Mono<com.liverday.shortly.domain.url.Url>
}