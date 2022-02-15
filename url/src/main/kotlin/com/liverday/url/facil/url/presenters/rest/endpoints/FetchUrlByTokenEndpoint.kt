package com.liverday.url.facil.url.presenters.rest.endpoints

import com.liverday.url.facil.url.ports.usecases.url.fetch.FetchUrlByTokenInputBoundary
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
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
    ): Mono<ResponseEntity<*>> {
        return fetchUrlByTokenInputBoundary
                .execute(token)
                .map { url ->
                    ResponseEntity
                            .status(HttpStatus.PERMANENT_REDIRECT)
                            .location(URI.create(url.link))
                            .build<String>()
                }
    }
}