package com.liverday.url.facil.application.ports.factories.url

import com.liverday.url.facil.domain.urlClick.UrlClick
import com.liverday.url.facil.application.ports.usecases.url.CreateUrlClickRequest

interface UrlClickFactory {
    fun create(request: CreateUrlClickRequest): UrlClick
}