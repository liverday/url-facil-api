package com.liverday.shortly.infrastructure.rest.endpoints

import com.liverday.shortly.application.ports.publishers.UrlPublishMetadata
import com.liverday.shortly.application.ports.usecases.url.FetchUrlByTokenInputBoundary
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
            val headers = exchange.request.headers
            val userAgent = headers.getFirst("User-Agent") ?: ""
            val ip = exchange.request.remoteAddress?.address?.hostAddress ?: ""
            fetchUrlByTokenInputBoundary
                    .execute(token, UrlPublishMetadata(ip, userAgent))
                    .map { url ->
                        ResponseEntity
                                .status(HttpStatus.MOVED_PERMANENTLY)
                                .header("Cache-Control", "no-cache, no-store")
                                .location(URI.create(url.link!!))
                                .build<String>()
                    }
        }
    }
}