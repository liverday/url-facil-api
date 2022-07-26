package com.liverday.shortly.infrastructure.configuration.factories

import com.liverday.shortly.infrastructure.adapters.factories.CommonUrlClickFactory
import com.liverday.shortly.infrastructure.adapters.factories.CommonUrlFactory
import com.liverday.shortly.application.ports.factories.url.UrlClickFactory
import com.liverday.shortly.application.ports.factories.url.UrlFactory
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
@Suppress("unused")
class FactoryConfig {
    @Bean
    fun urlFactory(): UrlFactory {
        return CommonUrlFactory()
    }

    @Bean
    fun urlClickFactory(): UrlClickFactory {
        return CommonUrlClickFactory()
    }
}