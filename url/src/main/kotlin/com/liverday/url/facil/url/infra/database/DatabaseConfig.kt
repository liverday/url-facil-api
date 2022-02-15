package com.liverday.url.facil.url.infra.database

import com.liverday.url.facil.url.adapters.mongodb.converters.MongoUrlConverter
import com.liverday.url.facil.url.adapters.mongodb.entities.MongoUrlData
import com.liverday.url.facil.url.adapters.mongodb.gateways.MongoUrlDatabaseGateway
import com.liverday.url.facil.url.adapters.mongodb.repositories.MongoUrlRepository
import com.liverday.url.facil.url.ports.converters.url.UrlConverter
import com.liverday.url.facil.url.ports.database.url.UrlDatabaseGateway
import com.mongodb.reactivestreams.client.MongoClient
import com.mongodb.reactivestreams.client.MongoClients
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
@Suppress("unused", "UNCHECKED_CAST")
class DatabaseConfig {

    @Bean
    fun urlConverter(): UrlConverter<*> {
        return MongoUrlConverter()
    }
    @Bean
    fun urlGateway(mongoUrlRepository: MongoUrlRepository): UrlDatabaseGateway {
        return MongoUrlDatabaseGateway(mongoUrlRepository, urlConverter() as UrlConverter<MongoUrlData>)
    }
}