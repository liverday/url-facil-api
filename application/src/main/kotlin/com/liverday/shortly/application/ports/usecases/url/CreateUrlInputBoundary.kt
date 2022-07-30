package com.liverday.shortly.application.ports.usecases.url

import com.liverday.shortly.domain.url.Url
import reactor.core.publisher.Mono

interface CreateUrlInputBoundary {
    fun execute(input: CreateUrlRequest): Mono<Url>
}