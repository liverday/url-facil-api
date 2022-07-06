package com.liverday.url.facil.infrastructure.adapters.factories

import com.liverday.url.facil.domain.url.entities.UrlClick
import com.liverday.url.facil.application.ports.factories.url.UrlClickFactory
import com.liverday.url.facil.application.ports.usecases.url.CreateUrlClickRequest

class CommonUrlClickFactory : UrlClickFactory {
    override fun create(request: CreateUrlClickRequest): UrlClick {
        return UrlClick(
                urlId = request.url.id,
                platform = request.platform,
                device = request.device,
                browser = request.browser,
                country = ""
        )
    }
}