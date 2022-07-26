package com.liverday.shortly.application.ports.usecases.url

import reactor.core.publisher.Mono

interface CreateUrlTokenInputBoundary {
    fun execute(): Mono<String>
}