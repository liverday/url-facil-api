package com.liverday.url.facil.url.ports.usecases.url

import com.liverday.url.facil.url.domain.url.entities.Url

data class CreateUrlClickRequest(
        val url: Url,
        var platform: String,
        var device: String,
        var browser: String,
        var country: String
)