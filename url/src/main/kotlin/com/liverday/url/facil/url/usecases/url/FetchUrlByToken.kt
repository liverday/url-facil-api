package com.liverday.url.facil.url.usecases.url

import com.liverday.url.facil.url.domain.url.entities.Url
import com.liverday.url.facil.url.domain.url.exceptions.UrlNotFoundException
import com.liverday.url.facil.url.ports.database.url.UrlDatabaseGateway
import com.liverday.url.facil.url.ports.usecases.url.FetchUrlByTokenInputBoundary
import com.liverday.url.facil.url.ports.usecases.url.UpdateUrlClicksInputBoundary
import reactor.core.publisher.Mono

class FetchUrlByToken(
        private val urlDatabaseGateway: UrlDatabaseGateway,
        private val updateUrlClicksInputBoundary: UpdateUrlClicksInputBoundary
) : FetchUrlByTokenInputBoundary {
    override fun execute(token: String): Mono<Url> {
        return urlDatabaseGateway
                .findUrlByToken(token)
                .flatMap(updateUrlClicksInputBoundary::execute)
                .switchIfEmpty(Mono.error(UrlNotFoundException()))
    }
}