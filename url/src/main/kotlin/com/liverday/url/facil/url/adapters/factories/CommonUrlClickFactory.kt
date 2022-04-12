package com.liverday.url.facil.url.adapters.factories

import com.liverday.url.facil.url.domain.url.entities.UrlClick
import com.liverday.url.facil.url.ports.factories.url.UrlClickFactory
import com.liverday.url.facil.url.ports.usecases.url.CreateUrlClickRequest

class CommonUrlClickFactory : UrlClickFactory {
    override fun create(request: CreateUrlClickRequest): UrlClick {
        return UrlClick(
                url = request.url,
                platform = request.platform,
                device = request.device,
                browser = request.browser,
                country = request.country,
        )
    }
}