package com.liverday.shortly.infrastructure.rest.endpoints

import com.liverday.shortlyl.domain.url.Url
import com.liverday.shortly.application.ports.usecases.url.CreateUrlInputBoundary
import com.liverday.shortly.application.ports.usecases.url.CreateUrlRequest
import org.springframework.hateoas.EntityModel
import org.springframework.hateoas.server.reactive.WebFluxLinkBuilder.*
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.server.ServerWebExchange
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
            @Valid @RequestBody body: CreateUrlRequest,
            exchange: ServerWebExchange
    ): Mono<EntityModel<Url>> {
        val fetchUrlByTokenEndpoint = methodOn(FetchUrlByTokenEndpoint::class.java)
        return createUrlInputBoundary.execute(body)
                .flatMap {
                    val link = linkTo(fetchUrlByTokenEndpoint.execute(it.token!!, exchange)).withSelfRel()
                    Mono.zip(Mono.just(it), link.toMono())
                }.map {
                    val url = it.t1
                    val link = it.t2
                    EntityModel.of(url, link)
                }
    }
}