package com.liverday.url.facil.infrastructure.configuration.web

import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.server.reactive.ServerHttpRequest
import org.springframework.web.server.WebFilter

@Configuration
@Suppress("unused")
class WebConfig {

    @Bean
    fun contextPathWebFilter(@Value("\${server.context-path}") contextPath: String): WebFilter? {
        return WebFilter { exchange, chain ->
            val request: ServerHttpRequest = exchange.request
            if (request.uri.path.startsWith(contextPath)) {
                chain.filter(
                        exchange.mutate()
                                .request(request.mutate().contextPath(contextPath).build())
                                .build())
            } else {
                chain.filter(exchange)
            }
        }
    }
}