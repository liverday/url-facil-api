package com.liverday.url.facil.application.ports.usecases.url

import com.liverday.url.facil.domain.url.entities.Url

data class CreateUrlClickRequest(
        val url: Url,
        var platform: String,
        var device: String,
        var browser: String,
        var ip: String
)