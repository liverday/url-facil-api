package com.liverday.url.facil.url.adapters.factories

import com.liverday.url.facil.url.domain.url.entities.Url
import com.liverday.url.facil.url.domain.url.exceptions.InvalidUrlArgumentsException
import com.liverday.url.facil.url.ports.factories.url.UrlFactory
import com.liverday.url.facil.url.ports.usecases.url.CreateUrlRequest

class CommonUrlFactory : UrlFactory {
    override fun create(request: CreateUrlRequest): Url {
        if (request.link == null)
            throw InvalidUrlArgumentsException()

        return Url(
                link = request.link,
                token = request.token
        )
    }
}