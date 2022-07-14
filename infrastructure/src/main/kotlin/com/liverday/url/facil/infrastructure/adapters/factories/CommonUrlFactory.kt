package com.liverday.url.facil.infrastructure.adapters.factories

import com.liverday.url.facil.domain.url.entities.Url
import com.liverday.url.facil.domain.exceptions.InvalidUrlArgumentsException
import com.liverday.url.facil.application.ports.factories.url.UrlFactory
import com.liverday.url.facil.application.ports.usecases.url.CreateUrlRequest

class CommonUrlFactory : UrlFactory {
    override fun create(request: CreateUrlRequest): Url {
        if (request.link == null)
            throw InvalidUrlArgumentsException()

        return Url(
                link = request.link!!,
                token = request.token
        )
    }
}