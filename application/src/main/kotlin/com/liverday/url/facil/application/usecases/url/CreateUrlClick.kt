package com.liverday.url.facil.application.usecases.url

import com.liverday.url.facil.domain.urlClick.UrlClick
import com.liverday.url.facil.application.ports.database.url.UrlClicksDatabaseGateway
import com.liverday.url.facil.application.ports.factories.url.UrlClickFactory
import com.liverday.url.facil.application.ports.usecases.url.CreateUrlClickInputBoundary
import com.liverday.url.facil.application.ports.usecases.url.CreateUrlClickRequest
import com.liverday.url.facil.domain.logger.Logger
import reactor.core.publisher.Mono
import reactor.core.publisher.Sinks
import reactor.core.scheduler.Schedulers

class CreateUrlClick(
        private val urlClicksDatabaseGateway: UrlClicksDatabaseGateway,
        private val urlClickFactory: UrlClickFactory,
        private val sink: Sinks.Many<CreateUrlClickRequest>,
        private val logger: Logger
) : CreateUrlClickInputBoundary {
    override fun execute(input: CreateUrlClickRequest): Mono<UrlClick> {
        return Mono.just(urlClickFactory.create(input))
                .flatMap { urlClick ->
                    urlClicksDatabaseGateway.save(urlClick)
                }
                .subscribeOn(Schedulers.boundedElastic())
    }

    init {
        this.sink.asFlux()
                .subscribe {
                    logger.info("Received a new URL Click event: {}", it)
                    this.execute(it)
                            .subscribe()
                }
    }
}