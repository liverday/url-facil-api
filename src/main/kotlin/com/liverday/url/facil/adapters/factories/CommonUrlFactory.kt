package com.liverday.url.facil.adapters.factories

import com.liverday.url.facil.domain.url.entities.Url
import com.liverday.url.facil.ports.factories.url.UrlFactory
import com.liverday.url.facil.ports.usecases.url.CreateUrlRequest

class CommonUrlFactory : UrlFactory {
    override fun create(request: CreateUrlRequest): Url {
        return Url(
                link = request.link,
                token = request.token
        )
    }
}