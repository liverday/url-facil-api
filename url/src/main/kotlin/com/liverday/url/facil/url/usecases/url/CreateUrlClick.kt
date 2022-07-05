package com.liverday.url.facil.url.usecases.url

import com.liverday.url.facil.domain.url.entities.UrlClick
import com.liverday.url.facil.url.ports.database.url.UrlClicksDatabaseGateway
import com.liverday.url.facil.url.ports.factories.url.UrlClickFactory
import com.liverday.url.facil.url.ports.usecases.url.CreateUrlClickInputBoundary
import com.liverday.url.facil.url.ports.usecases.url.CreateUrlClickRequest
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.InitializingBean
import reactor.core.publisher.Mono
import reactor.core.publisher.Sinks
import reactor.core.scheduler.Schedulers

class CreateUrlClick(
        private val urlClicksDatabaseGateway: UrlClicksDatabaseGateway,
        private val urlClickFactory: UrlClickFactory,
        private val sink: Sinks.Many<CreateUrlClickRequest>
) : CreateUrlClickInputBoundary, InitializingBean {
    private val logger = LoggerFactory.getLogger(CreateUrlClick::class.java)

    override fun execute(input: CreateUrlClickRequest): Mono<UrlClick> {
        return Mono.just(urlClickFactory.create(input))
                .flatMap { urlClick ->
                    urlClicksDatabaseGateway.save(urlClick)
                }
                .subscribeOn(Schedulers.boundedElastic())
    }

    override fun afterPropertiesSet() {
        this.sink.asFlux()
                .subscribe {
                    logger.info("Received a new URL Click event: {}", it)
                    this.execute(it)
                            .subscribe()
                }
    }
}