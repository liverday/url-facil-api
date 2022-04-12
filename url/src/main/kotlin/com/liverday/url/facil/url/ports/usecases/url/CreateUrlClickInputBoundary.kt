package com.liverday.url.facil.url.ports.usecases.url

import com.liverday.url.facil.url.domain.url.entities.UrlClick
import reactor.core.publisher.Mono

interface CreateUrlClickInputBoundary {
    fun execute(input: CreateUrlClickRequest): Mono<UrlClick>
}