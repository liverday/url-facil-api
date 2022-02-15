package com.liverday.url.facil.url.ports.usecases.url.create

import com.liverday.url.facil.url.domain.url.entities.Url
import reactor.core.publisher.Mono

interface CreateUrlInputBoundary {
    fun execute(input: CreateUrlRequest): Mono<Url>
}