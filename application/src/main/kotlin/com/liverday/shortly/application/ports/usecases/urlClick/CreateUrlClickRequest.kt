package com.liverday.shortly.application.ports.usecases.urlClick

import com.liverday.shortly.domain.url.Url

data class CreateUrlClickRequest(
        val url: Url,
        var platform: String,
        var device: String,
        var browser: String,
        var ip: String
)