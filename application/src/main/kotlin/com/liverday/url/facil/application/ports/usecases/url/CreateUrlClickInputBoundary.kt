package com.liverday.url.facil.application.ports.usecases.url

import com.liverday.url.facil.domain.urlClick.UrlClick
import reactor.core.publisher.Mono

interface CreateUrlClickInputBoundary {
    fun execute(input: CreateUrlClickRequest): Mono<UrlClick>
}