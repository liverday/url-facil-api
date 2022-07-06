package com.liverday.url.facil.application.ports.factories.url

import com.liverday.url.facil.domain.url.entities.Url
import com.liverday.url.facil.application.ports.usecases.url.CreateUrlRequest

interface UrlFactory {
    fun create(request: CreateUrlRequest): Url
}