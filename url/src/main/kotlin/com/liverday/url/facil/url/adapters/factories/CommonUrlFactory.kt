package com.liverday.url.facil.url.adapters.factories

import com.liverday.url.facil.url.domain.url.entities.Url
import com.liverday.url.facil.url.ports.factories.url.UrlFactory
import com.liverday.url.facil.url.ports.usecases.url.create.CreateUrlRequest

class CommonUrlFactory : UrlFactory {
    override fun create(request: CreateUrlRequest): Url {
        return Url(
                link = request.link!!,
                token = request.token
        )
    }
}