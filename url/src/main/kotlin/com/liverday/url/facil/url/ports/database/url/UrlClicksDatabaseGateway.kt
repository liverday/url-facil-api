package com.liverday.url.facil.url.ports.database.url

import com.liverday.url.facil.domain.url.entities.UrlClick
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

interface UrlClicksDatabaseGateway {
    fun save(urlClick: UrlClick): Mono<UrlClick>
    fun findAll(): Flux<UrlClick>
    fun findAllByUrlId(urlId: String): Flux<UrlClick>

}