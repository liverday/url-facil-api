package com.liverday.url.facil.url.infra

import com.liverday.url.facil.url.adapters.factories.CommonUrlFactory
import com.liverday.url.facil.url.ports.database.url.UrlDatabaseGateway
import com.liverday.url.facil.url.ports.factories.url.UrlFactory
import com.liverday.url.facil.url.ports.usecases.url.create.CreateUrlInputBoundary
import com.liverday.url.facil.url.ports.usecases.url.create.CreateUrlTokenInputBoundary
import com.liverday.url.facil.url.ports.usecases.url.fetch.FetchUrlByTokenInputBoundary
import com.liverday.url.facil.url.usecases.url.CreateUrl
import com.liverday.url.facil.url.usecases.url.CreateUrlToken
import com.liverday.url.facil.url.usecases.url.FetchUrlByToken
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.server.reactive.ServerHttpRequest
import org.springframework.web.server.WebFilter

@Configuration
@Suppress("unused")
class AppConfig {

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

    @Bean
    fun urlFactory(): UrlFactory {
        return CommonUrlFactory()
    }

    @Bean
    fun createUrlTokenInputBoundary(urlGateway: UrlDatabaseGateway): CreateUrlTokenInputBoundary {
        return CreateUrlToken(urlGateway)
    }

    @Bean
    fun createUrlInputBoundary(urlGateway: UrlDatabaseGateway, createUrlTokenInputBoundary: CreateUrlTokenInputBoundary, urlFactory: UrlFactory): CreateUrlInputBoundary {
        return CreateUrl(urlGateway, createUrlTokenInputBoundary, urlFactory)
    }

    @Bean
    fun fetchUrlByTokenInputBoundary(urlGateway: UrlDatabaseGateway): FetchUrlByTokenInputBoundary {
        return FetchUrlByToken(urlGateway)
    }
}