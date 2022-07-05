package com.liverday.url.facil.url.ports.database.url

import com.liverday.url.facil.domain.url.entities.Url
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

interface UrlDatabaseGateway {
    fun save(url: Url): Mono<Url>
    fun findById(id: String): Mono<Url>
    fun findUrlByToken(token: String): Mono<Url>
    fun existsByToken(token: String?): Mono<Boolean>
    fun findAll(): Flux<Url>
}