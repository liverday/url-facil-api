package com.liverday.url.facil.url.ports.factories.url

import com.liverday.url.facil.domain.url.entities.UrlClick
import com.liverday.url.facil.url.ports.usecases.url.CreateUrlClickRequest

interface UrlClickFactory {
    fun create(request: CreateUrlClickRequest): UrlClick
}