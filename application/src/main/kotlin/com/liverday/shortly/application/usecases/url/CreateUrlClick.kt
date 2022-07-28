package com.liverday.shortly.application.usecases.url

import com.liverday.shortly.domain.urlClick.UrlClick
import com.liverday.shortly.application.ports.database.url.UrlClicksDatabaseGateway
import com.liverday.shortly.application.ports.factories.url.UrlClickFactory
import com.liverday.shortly.application.ports.usecases.url.CreateUrlClickInputBoundary
import com.liverday.shortly.application.ports.usecases.url.CreateUrlClickRequest
import com.liverday.shortly.domain.logger.Logger
import reactor.core.publisher.Mono
import reactor.core.publisher.Sinks
import reactor.core.scheduler.Schedulers

class CreateUrlClick(
        private val urlClicksDatabaseGateway: UrlClicksDatabaseGateway,
        private val urlClickFactory: UrlClickFactory,
        private val sink: Sinks.Many<CreateUrlClickRequest>,
        private val logger: com.liverday.shortly.domain.logger.Logger
) : CreateUrlClickInputBoundary {
    override fun execute(input: CreateUrlClickRequest): Mono<UrlClick> {
        return Mono.fromCallable {
            val either = urlClickFactory.create(input)

            if (either.isLeft())
                throw either.getLeft()

            either.getRight()
        }.flatMap { urlClick ->
            urlClicksDatabaseGateway.save(urlClick)
        }.subscribeOn(Schedulers.boundedElastic())
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