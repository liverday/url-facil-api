package com.liverday.url.facil.url.infra.database

import com.liverday.url.facil.url.adapters.mongodb.converters.MongoUrlClicksConverter
import com.liverday.url.facil.url.adapters.mongodb.converters.MongoUrlConverter
import com.liverday.url.facil.url.adapters.mongodb.entities.MongoUrlClickData
import com.liverday.url.facil.url.adapters.mongodb.entities.MongoUrlData
import com.liverday.url.facil.url.adapters.mongodb.gateways.MongoUrlClicksDatabaseGateway
import com.liverday.url.facil.url.adapters.mongodb.gateways.MongoUrlDatabaseGateway
import com.liverday.url.facil.url.adapters.mongodb.repositories.MongoUrlClicksRepository
import com.liverday.url.facil.url.adapters.mongodb.repositories.MongoUrlRepository
import com.liverday.url.facil.domain.url.entities.Url
import com.liverday.url.facil.domain.url.entities.UrlClick
import com.liverday.url.facil.url.ports.converters.EntityConverter
import com.liverday.url.facil.url.ports.database.url.UrlClicksDatabaseGateway
import com.liverday.url.facil.url.ports.database.url.UrlDatabaseGateway
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
@Suppress("unused", "UNCHECKED_CAST")
class DatabaseConfig {

    @Bean
    fun urlConverter(): EntityConverter<Url, MongoUrlData> {
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