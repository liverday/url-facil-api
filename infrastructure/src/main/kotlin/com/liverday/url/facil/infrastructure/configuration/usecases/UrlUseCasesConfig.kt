package com.liverday.url.facil.infrastructure.configuration.usecases

import com.blueconic.browscap.UserAgentParser
import com.blueconic.browscap.UserAgentService
import com.liverday.url.facil.infrastructure.adapters.publishers.SinkUrlPublisher
import com.liverday.url.facil.application.ports.database.url.UrlClicksDatabaseGateway
import com.liverday.url.facil.application.ports.database.url.UrlDatabaseGateway
import com.liverday.url.facil.application.ports.factories.url.UrlClickFactory
import com.liverday.url.facil.application.ports.factories.url.UrlFactory
import com.liverday.url.facil.application.ports.publishers.UrlPublisher
import com.liverday.url.facil.application.ports.usecases.url.*
import com.liverday.url.facil.application.usecases.url.CreateUrl
import com.liverday.url.facil.application.usecases.url.CreateUrlClick
import com.liverday.url.facil.application.usecases.url.CreateUrlToken
import com.liverday.url.facil.application.usecases.url.FetchUrlByToken
import com.liverday.url.facil.infrastructure.adapters.logger.Slf4jLogger
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import reactor.core.publisher.Sinks

@Configuration
@Suppress("unused")
class UrlUseCasesConfig {

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
    fun fetchUrlByTokenInputBoundary(urlGateway: UrlDatabaseGateway, urlPublisher: UrlPublisher): FetchUrlByTokenInputBoundary {
        return FetchUrlByToken(urlGateway, urlPublisher)
    }

    @Bean
    fun createUrlClickInputBoundary(
            urlClicksDatabaseGateway: UrlClicksDatabaseGateway,
            urlClickFactory: UrlClickFactory,
            sink: Sinks.Many<CreateUrlClickRequest>
    ): CreateUrlClickInputBoundary {
        return CreateUrlClick(
                urlClicksDatabaseGateway,
                urlClickFactory,
                sink,
                Slf4jLogger(CreateUrlClick::class.java)
        )
    }

    @Bean
    fun userAgentParse(): UserAgentParser {
        return UserAgentService().loadParser()
    }

    @Bean
    fun urlPublisher(sink: Sinks.Many<CreateUrlClickRequest>, userAgentParser: UserAgentParser): UrlPublisher {
        return SinkUrlPublisher(sink, userAgentParser)
    }
}