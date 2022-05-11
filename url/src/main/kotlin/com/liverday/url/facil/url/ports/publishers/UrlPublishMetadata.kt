package com.liverday.url.facil.url.ports.publishers

import org.springframework.http.server.reactive.ServerHttpRequest

data class UrlPublishMetadata(
        var ip: String = "",
        var userAgent: String = ""
) {
    companion object {
        fun fromRequest(request: ServerHttpRequest): UrlPublishMetadata {
            val headers = request.headers
            val userAgent = headers.getFirst("User-Agent") ?: ""
            val ip = request.remoteAddress?.address?.hostAddress ?: ""
            return UrlPublishMetadata(
                    ip,
                    userAgent
            )
        }
    }
}