package com.liverday.url.facil.url.ports.usecases.url

import com.liverday.url.facil.url.domain.url.entities.Url
import reactor.core.publisher.Mono

interface UpdateUrlClicksInputBoundary {
    fun execute(url: Url): Mono<Url>
}