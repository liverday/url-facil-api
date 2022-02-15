package com.liverday.url.facil.url.ports.usecases.url.create

import reactor.core.publisher.Mono

interface CreateUrlTokenInputBoundary {
    fun execute(): Mono<String>
}