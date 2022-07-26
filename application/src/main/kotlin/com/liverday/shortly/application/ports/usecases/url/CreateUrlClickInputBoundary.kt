package com.liverday.shortly.application.ports.usecases.url

import com.liverday.shortlyl.domain.urlClick.UrlClick
import reactor.core.publisher.Mono

interface CreateUrlClickInputBoundary {
    fun execute(input: CreateUrlClickRequest): Mono<UrlClick>
}