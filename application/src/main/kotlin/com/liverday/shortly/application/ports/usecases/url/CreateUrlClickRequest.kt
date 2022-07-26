package com.liverday.shortly.application.ports.usecases.url

import com.liverday.shortlyl.domain.url.Url

data class CreateUrlClickRequest(
        val url: Url,
        var platform: String,
        var device: String,
        var browser: String,
        var ip: String
)