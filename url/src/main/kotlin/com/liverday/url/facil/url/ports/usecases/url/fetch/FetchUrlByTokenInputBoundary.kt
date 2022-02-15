package com.liverday.url.facil.url.ports.usecases.url.fetch

import com.liverday.url.facil.url.domain.url.entities.Url
import reactor.core.publisher.Mono

interface FetchUrlByTokenInputBoundary {
    fun execute(token: String): Mono<Url>
}