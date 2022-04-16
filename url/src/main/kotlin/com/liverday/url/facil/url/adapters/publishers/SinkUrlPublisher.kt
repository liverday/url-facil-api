package com.liverday.url.facil.url.adapters.publishers

import com.liverday.url.facil.url.domain.url.entities.Url
import com.liverday.url.facil.url.ports.publishers.UrlPublishMetadata
import com.liverday.url.facil.url.ports.publishers.UrlPublisher
import com.liverday.url.facil.url.ports.usecases.url.CreateUrlClickRequest
import org.slf4j.LoggerFactory
import reactor.core.publisher.Mono
import reactor.core.publisher.Sinks
import reactor.core.scheduler.Schedulers

class SinkUrlPublisher(private val sink: Sinks.Many<CreateUrlClickRequest>) : UrlPublisher {
    private val logger = LoggerFactory.getLogger(SinkUrlPublisher::class.java)
    override fun onUrlClicked(url: Url, metadata: UrlPublishMetadata): Mono<Url> {
        return Mono.fromCallable {
            CreateUrlClickRequest(
                    url,
                    metadata.platform,
                    metadata.device,
                    metadata.browser,
                    metadata.country
            )
        }
                .subscribeOn(Schedulers.parallel())
                .doOnNext { next ->
                    logger.info("Emitting a new URL Clicked event through reactor sink: {}", next)
                    this.sink.tryEmitNext(next)
                }
                .thenReturn(url)
    }
}