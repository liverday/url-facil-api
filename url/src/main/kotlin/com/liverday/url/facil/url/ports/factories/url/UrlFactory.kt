package com.liverday.url.facil.url.ports.factories.url

import com.liverday.url.facil.domain.url.entities.Url
import com.liverday.url.facil.url.ports.usecases.url.CreateUrlRequest

interface UrlFactory {
    fun create(request: CreateUrlRequest): Url
}