package com.liverday.url.facil.url.presenters.rest.endpoints

import com.liverday.url.facil.url.ports.usecases.url.create.CreateUrlInputBoundary
import com.liverday.url.facil.url.ports.usecases.url.create.CreateUrlRequest
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import reactor.core.publisher.Mono
import javax.validation.Valid

@RestController
@Suppress("unused")
@RequestMapping("/url")
class CreateUrlEndpoint(
        private val createUrlInputBoundary: CreateUrlInputBoundary
) {

    @PostMapping
    fun execute(
            @Valid @RequestBody body: CreateUrlRequest
    ): Mono<ResponseEntity<*>> {
        return createUrlInputBoundary.execute(body)
                .map {
                    ResponseEntity
                            .ok(it)
                }
    }
}