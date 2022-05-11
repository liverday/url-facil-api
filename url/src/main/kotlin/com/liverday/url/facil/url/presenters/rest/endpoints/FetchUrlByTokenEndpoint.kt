package com.liverday.url.facil.url.presenters.rest.endpoints

import com.liverday.url.facil.url.ports.publishers.UrlPublishMetadata
import com.liverday.url.facil.url.ports.usecases.url.FetchUrlByTokenInputBoundary
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.server.ServerWebExchange
import reactor.core.publisher.Mono
import java.net.URI

@RestController
@Suppress("unused")
class FetchUrlByTokenEndpoint(
        private val fetchUrlByTokenInputBoundary: FetchUrlByTokenInputBoundary
) {

    @GetMapping("/{token}")
    fun execute(
            @PathVariable token: String,
            exchange: ServerWebExchange,
    ): Mono<ResponseEntity<*>> {
        return Mono.defer {
            fetchUrlByTokenInputBoundary
                    .execute(token, UrlPublishMetadata.fromRequest(exchange.request))
                    .map { url ->
                        ResponseEntity
                                .status(HttpStatus.MOVED_PERMANENTLY)
                                .header("Cache-Control", "no-cache, no-store")
                                .location(URI.create(url.link))
                                .build<String>()
                    }
        }
    }
}