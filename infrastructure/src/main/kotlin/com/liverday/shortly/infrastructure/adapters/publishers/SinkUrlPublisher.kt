package com.liverday.shortly.infrastructure.adapters.publishers

import com.blueconic.browscap.UserAgentParser
import com.liverday.shortly.domain.url.Url
import com.liverday.shortly.application.ports.publishers.UrlPublishMetadata
import com.liverday.shortly.application.ports.publishers.UrlPublisher
import com.liverday.shortly.application.ports.usecases.url.CreateUrlClickRequest
import org.slf4j.LoggerFactory
import reactor.core.publisher.Mono
import reactor.core.publisher.Sinks
import reactor.core.scheduler.Schedulers

class SinkUrlPublisher(
        private val sink: Sinks.Many<CreateUrlClickRequest>,
        private val userAgentParser: UserAgentParser
) : UrlPublisher {
    private val logger = LoggerFactory.getLogger(SinkUrlPublisher::class.java)
    override fun onUrlClicked(url: com.liverday.shortly.domain.url.Url, metadata: UrlPublishMetadata): Mono<com.liverday.shortly.domain.url.Url> {
        val parsedUserAgent = userAgentParser.parse(metadata.userAgent)
        return Mono.fromCallable {
            CreateUrlClickRequest(
                    url,
                    parsedUserAgent.platform,
                    parsedUserAgent.deviceType,
                    parsedUserAgent.browser,
                    metadata.ip
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