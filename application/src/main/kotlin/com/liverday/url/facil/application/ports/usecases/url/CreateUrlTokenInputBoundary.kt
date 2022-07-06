package com.liverday.url.facil.application.ports.usecases.url

import reactor.core.publisher.Mono

interface CreateUrlTokenInputBoundary {
    fun execute(): Mono<String>
}