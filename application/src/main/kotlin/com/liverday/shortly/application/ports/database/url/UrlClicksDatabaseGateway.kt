package com.liverday.shortly.application.ports.database.url

import com.liverday.shortlyl.domain.urlClick.UrlClick
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

interface UrlClicksDatabaseGateway {
    fun save(urlClick: UrlClick): Mono<UrlClick>
    fun findAll(): Flux<UrlClick>
    fun findAllByUrlId(urlId: String): Flux<UrlClick>

}