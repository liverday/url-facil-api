package com.liverday.url.facil.infrastructure.configuration.factories

import com.liverday.url.facil.infrastructure.adapters.factories.CommonUrlClickFactory
import com.liverday.url.facil.infrastructure.adapters.factories.CommonUrlFactory
import com.liverday.url.facil.application.ports.factories.url.UrlClickFactory
import com.liverday.url.facil.application.ports.factories.url.UrlFactory
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