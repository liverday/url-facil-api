package com.liverday.shortly.infrastructure.configuration.usecases

import com.blueconic.browscap.UserAgentParser
import com.blueconic.browscap.UserAgentService
import com.liverday.shortly.infrastructure.adapters.publishers.SinkUrlPublisher
import com.liverday.shortly.application.ports.database.url.UrlClicksDatabaseGateway
import com.liverday.shortly.application.ports.database.url.UrlDatabaseGateway
import com.liverday.shortly.application.ports.factories.url.UrlClickFactory
import com.liverday.shortly.application.ports.factories.url.UrlFactory
import com.liverday.shortly.application.ports.publishers.UrlPublisher
import com.liverday.shortly.application.ports.usecases.url.*
import com.liverday.shortly.application.ports.usecases.urlClick.CreateUrlClickInputBoundary
import com.liverday.shortly.application.ports.usecases.urlClick.CreateUrlClickRequest
import com.liverday.shortly.application.usecases.url.CreateUrl
import com.liverday.shortly.application.usecases.urlClick.CreateUrlClick
import com.liverday.shortly.application.usecases.url.CreateUrlToken
import com.liverday.shortly.application.usecases.url.FetchUrlByToken
import com.liverday.shortly.infrastructure.adapters.logger.Slf4jLogger
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