package com.liverday.url.facil.url.ports.publishers

data class UrlPublishMetadata(
        var platform: String = "",
        var device: String = "",
        var browser: String = "",
        var country: String = ""
)