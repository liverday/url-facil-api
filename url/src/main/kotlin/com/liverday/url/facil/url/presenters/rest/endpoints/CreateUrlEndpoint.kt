package com.liverday.url.facil.url.presenters.rest.endpoints

import com.liverday.url.facil.url.domain.url.entities.Url
import com.liverday.url.facil.url.ports.usecases.url.CreateUrlInputBoundary
import com.liverday.url.facil.url.ports.usecases.url.CreateUrlRequest
import org.springframework.hateoas.EntityModel
import org.springframework.hateoas.server.reactive.WebFluxLinkBuilder.*
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
    ): Mono<EntityModel<Url>> {
        val fetchUrlByTokenEndpoint = methodOn(FetchUrlByTokenEndpoint::class.java)
        return createUrlInputBoundary.execute(body)
                .flatMap {
                    val link = linkTo(fetchUrlByTokenEndpoint.execute(it.token!!)).withSelfRel()
                    Mono.zip(Mono.just(it), link.toMono())
                }.map {
                    val url = it.t1
                    val link = it.t2
                    EntityModel.of(url, link)
                }
    }
}