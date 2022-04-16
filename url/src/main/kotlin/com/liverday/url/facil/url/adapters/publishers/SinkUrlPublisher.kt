package com.liverday.url.facil.url.adapters.publishers

import com.liverday.url.facil.url.domain.url.entities.Url
import com.liverday.url.facil.url.ports.publishers.UrlPublishMetadata
import com.liverday.url.facil.url.ports.publishers.UrlPublisher
import com.liverday.url.facil.url.ports.usecases.url.CreateUrlClickRequest
import reactor.core.publisher.Mono
import reactor.core.publisher.Sinks
import reactor.core.scheduler.Schedulers

class SinkUrlPublisher(private val sink: Sinks.Many<CreateUrlClickRequest>) : UrlPublisher {
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
                .doOnNext(sink::tryEmitNext)
                .thenReturn(url)
    }
}