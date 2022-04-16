package com.liverday.url.facil.url.infra

import com.liverday.url.facil.url.adapters.factories.CommonUrlClickFactory
import com.liverday.url.facil.url.adapters.factories.CommonUrlFactory
import com.liverday.url.facil.url.adapters.publishers.SinkUrlPublisher
import com.liverday.url.facil.url.ports.database.url.UrlClicksDatabaseGateway
import com.liverday.url.facil.url.ports.database.url.UrlDatabaseGateway
import com.liverday.url.facil.url.ports.factories.url.UrlClickFactory
import com.liverday.url.facil.url.ports.factories.url.UrlFactory
import com.liverday.url.facil.url.ports.publishers.UrlPublisher
import com.liverday.url.facil.url.ports.usecases.url.*
import com.liverday.url.facil.url.usecases.url.CreateUrl
import com.liverday.url.facil.url.usecases.url.CreateUrlToken
import com.liverday.url.facil.url.usecases.url.FetchUrlByToken
import com.liverday.url.facil.url.usecases.url.CreateUrlClick
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.server.reactive.ServerHttpRequest
import org.springframework.web.server.WebFilter
import reactor.core.publisher.Sinks

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
    fun urlClickFactory(): UrlClickFactory {
        return CommonUrlClickFactory()
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
    fun createUrlClickSink(): Sinks.Many<CreateUrlClickRequest> {
        return Sinks.many().multicast().onBackpressureBuffer(1000)
    }

    @Bean
    fun urlPublisher(sink: Sinks.Many<CreateUrlClickRequest>): UrlPublisher {
        return SinkUrlPublisher(sink)
    }

    @Bean
    fun fetchUrlByTokenInputBoundary(urlGateway: UrlDatabaseGateway, urlPublisher: UrlPublisher): FetchUrlByTokenInputBoundary {
        return FetchUrlByToken(urlGateway, urlPublisher)
    }

    @Bean
    fun createUrlClickInputBoundary(urlClicksDatabaseGateway: UrlClicksDatabaseGateway, urlClickFactory: UrlClickFactory): CreateUrlClickInputBoundary {
        return CreateUrlClick(urlClicksDatabaseGateway, urlClickFactory)
    }
}