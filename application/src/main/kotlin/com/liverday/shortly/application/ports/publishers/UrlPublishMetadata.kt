package com.liverday.shortly.application.ports.publishers

data class UrlPublishMetadata(
        var ip: String = "",
        var userAgent: String = ""
)