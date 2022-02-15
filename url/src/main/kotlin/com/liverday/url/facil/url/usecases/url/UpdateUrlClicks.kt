package com.liverday.url.facil.url.usecases.url

import com.liverday.url.facil.url.domain.url.entities.Url
import com.liverday.url.facil.url.ports.database.url.UrlDatabaseGateway
import com.liverday.url.facil.url.ports.usecases.url.UpdateUrlClicksInputBoundary
import reactor.core.publisher.Mono

class UpdateUrlClicks(
        private val urlDatabaseGateway: UrlDatabaseGateway
) : UpdateUrlClicksInputBoundary {
    override fun execute(url: Url): Mono<Url> {
        url.clicks += 1
        return urlDatabaseGateway.save(url)
    }
}