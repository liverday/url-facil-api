package com.liverday.url.facil.url.usecases.url

import com.liverday.url.facil.url.domain.url.entities.UrlClick
import com.liverday.url.facil.url.ports.database.url.UrlClicksDatabaseGateway
import com.liverday.url.facil.url.ports.factories.url.UrlClickFactory
import com.liverday.url.facil.url.ports.usecases.url.CreateUrlClickInputBoundary
import com.liverday.url.facil.url.ports.usecases.url.CreateUrlClickRequest
import reactor.core.publisher.Mono
import reactor.core.scheduler.Schedulers

class CreateUrlClick(
        private val urlClicksDatabaseGateway: UrlClicksDatabaseGateway,
        private val urlClickFactory: UrlClickFactory
) : CreateUrlClickInputBoundary {
    override fun execute(input: CreateUrlClickRequest): Mono<UrlClick> {
        return Mono.defer {
            val urlClick = urlClickFactory.create(input)
            urlClicksDatabaseGateway.save(urlClick)
        }.subscribeOn(Schedulers.boundedElastic())
    }
}