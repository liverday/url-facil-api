package com.liverday.shortly.application.ports.usecases.url

import com.liverday.shortlyl.domain.url.Url
import com.liverday.shortly.application.ports.publishers.UrlPublishMetadata
import reactor.core.publisher.Mono

interface FetchUrlByTokenInputBoundary {
    fun execute(token: String, metadata: UrlPublishMetadata): Mono<Url>
}