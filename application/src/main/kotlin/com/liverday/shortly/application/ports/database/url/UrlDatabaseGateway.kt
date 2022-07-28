package com.liverday.shortly.application.ports.database.url

import com.liverday.shortly.domain.url.Url
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

interface UrlDatabaseGateway {
    fun save(url: com.liverday.shortly.domain.url.Url): Mono<com.liverday.shortly.domain.url.Url>
    fun findById(id: String): Mono<com.liverday.shortly.domain.url.Url>
    fun findUrlByToken(token: String): Mono<com.liverday.shortly.domain.url.Url>
    fun existsByToken(token: String?): Mono<Boolean>
    fun findAll(): Flux<com.liverday.shortly.domain.url.Url>
}