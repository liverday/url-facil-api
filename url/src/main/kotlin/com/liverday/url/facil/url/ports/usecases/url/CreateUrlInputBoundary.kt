package com.liverday.url.facil.url.ports.usecases.url

import com.liverday.url.facil.domain.url.entities.Url
import reactor.core.publisher.Mono

interface CreateUrlInputBoundary {
    fun execute(input: CreateUrlRequest): Mono<Url>
}