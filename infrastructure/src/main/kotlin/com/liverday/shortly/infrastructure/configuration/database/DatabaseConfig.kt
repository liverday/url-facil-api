package com.liverday.shortly.infrastructure.configuration.database

import com.liverday.shortly.infrastructure.adapters.mongodb.converters.MongoUrlClicksConverter
import com.liverday.shortly.infrastructure.adapters.mongodb.converters.MongoUrlConverter
import com.liverday.shortly.infrastructure.adapters.mongodb.entities.MongoUrlClickData
import com.liverday.shortly.infrastructure.adapters.mongodb.entities.MongoUrlData
import com.liverday.shortly.infrastructure.adapters.mongodb.gateways.MongoUrlClicksDatabaseGateway
import com.liverday.shortly.infrastructure.adapters.mongodb.gateways.MongoUrlDatabaseGateway
import com.liverday.shortly.infrastructure.adapters.mongodb.repositories.MongoUrlClicksRepository
import com.liverday.shortly.infrastructure.adapters.mongodb.repositories.MongoUrlRepository
import com.liverday.shortly.domain.url.Url
import com.liverday.shortly.domain.urlClick.UrlClick
import com.liverday.shortly.application.ports.converters.EntityConverter
import com.liverday.shortly.application.ports.database.url.UrlClicksDatabaseGateway
import com.liverday.shortly.application.ports.database.url.UrlDatabaseGateway
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
@Suppress("unused", "UNCHECKED_CAST")
class DatabaseConfig {

    @Bean
    fun urlConverter(): EntityConverter<com.liverday.shortly.domain.url.Url, MongoUrlData> {
        return MongoUrlConverter()
    }
    @Bean
    fun urlDatabaseGateway(mongoUrlRepository: MongoUrlRepository): UrlDatabaseGateway {
        return MongoUrlDatabaseGateway(mongoUrlRepository, urlConverter())
    }

    @Bean
    fun urlClicksConverter(): EntityConverter<UrlClick, MongoUrlClickData> {
        return MongoUrlClicksConverter()
    }

    @Bean
    fun urlClicksDatabaseGateway(mongoUrlClicksRepository: MongoUrlClicksRepository): UrlClicksDatabaseGateway {
        return MongoUrlClicksDatabaseGateway(mongoUrlClicksRepository, urlClicksConverter())
    }
}