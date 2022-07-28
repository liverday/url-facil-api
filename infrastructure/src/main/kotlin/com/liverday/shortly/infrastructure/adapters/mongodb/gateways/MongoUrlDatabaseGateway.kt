package com.liverday.shortly.infrastructure.adapters.mongodb.gateways

import com.liverday.shortly.infrastructure.adapters.mongodb.entities.MongoUrlData
import com.liverday.shortly.infrastructure.adapters.mongodb.repositories.MongoUrlRepository
import com.liverday.shortly.domain.url.Url
import com.liverday.shortly.application.ports.converters.EntityConverter
import com.liverday.shortly.application.ports.database.url.UrlDatabaseGateway
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono
import reactor.core.scheduler.Schedulers

class MongoUrlDatabaseGateway(
        private val mongoUrlRepository: MongoUrlRepository,
        private val mongoUrlConverter: EntityConverter<com.liverday.shortly.domain.url.Url, MongoUrlData>
) : UrlDatabaseGateway {
    override fun save(url: com.liverday.shortly.domain.url.Url): Mono<com.liverday.shortly.domain.url.Url> {
        return Mono.just(mongoUrlConverter.convertToEntity(url))
                .map { mongoUrl ->
                    mongoUrl.id = null
                    mongoUrl
                }
                .subscribeOn(Schedulers.boundedElastic())
                .flatMap(mongoUrlRepository::save)
                .map(mongoUrlConverter::convertToDomain)
    }

    override fun findById(id: String): Mono<com.liverday.shortly.domain.url.Url> {
        return mongoUrlRepository.findById(id)
                .subscribeOn(Schedulers.boundedElastic())
                .map(mongoUrlConverter::convertToDomain)
    }

    override fun findUrlByToken(token: String): Mono<com.liverday.shortly.domain.url.Url> {
        return mongoUrlRepository
                .findByToken(token)
                .subscribeOn(Schedulers.boundedElastic())
                .map(mongoUrlConverter::convertToDomain)
    }

    override fun findAll(): Flux<com.liverday.shortly.domain.url.Url> {
        return mongoUrlRepository
                .findAll()
                .subscribeOn(Schedulers.boundedElastic())
                .map(mongoUrlConverter::convertToDomain)
    }

    override fun existsByToken(token: String?): Mono<Boolean> {
        if (token == null)
            return Mono.just(false)

        return mongoUrlRepository.existsByToken(token)
                .subscribeOn(Schedulers.boundedElastic())
    }
}