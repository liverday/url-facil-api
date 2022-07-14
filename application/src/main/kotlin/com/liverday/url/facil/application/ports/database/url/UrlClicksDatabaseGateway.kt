package com.liverday.url.facil.application.ports.database.url

import com.liverday.url.facil.domain.urlClick.UrlClick
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

interface UrlClicksDatabaseGateway {
    fun save(urlClick: UrlClick): Mono<UrlClick>
    fun findAll(): Flux<UrlClick>
    fun findAllByUrlId(urlId: String): Flux<UrlClick>

}