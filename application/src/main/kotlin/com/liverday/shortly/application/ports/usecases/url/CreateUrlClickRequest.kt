package com.liverday.shortly.application.ports.usecases.url

import com.liverday.shortly.domain.url.Url

data class CreateUrlClickRequest(
        val url: com.liverday.shortly.domain.url.Url,
        var platform: String,
        var device: String,
        var browser: String,
        var ip: String
)