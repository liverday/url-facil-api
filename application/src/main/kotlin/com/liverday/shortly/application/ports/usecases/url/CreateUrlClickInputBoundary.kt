package com.liverday.shortly.application.ports.usecases.url

import com.liverday.shortly.domain.urlClick.UrlClick
import reactor.core.publisher.Mono

interface CreateUrlClickInputBoundary {
    fun execute(input: CreateUrlClickRequest): Mono<UrlClick>
}