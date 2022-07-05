package com.liverday.url.facil.url.adapters.mongodb.gateways

import com.liverday.url.facil.url.adapters.mongodb.entities.MongoUrlData
import com.liverday.url.facil.url.adapters.mongodb.repositories.MongoUrlRepository
import com.liverday.url.facil.domain.url.entities.Url
import com.liverday.url.facil.url.ports.converters.EntityConverter
import com.liverday.url.facil.url.ports.database.url.UrlDatabaseGateway
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono
import reactor.core.scheduler.Schedulers

class MongoUrlDatabaseGateway(
        private val mongoUrlRepository: MongoUrlRepository,
        private val mongoUrlConverter: EntityConverter<Url, MongoUrlData>
) : UrlDatabaseGateway {
    override fun save(url: Url): Mono<Url> {
        return Mono.just(mongoUrlConverter.convertToEntity(url))
                .subscribeOn(Schedulers.boundedElastic())
                .flatMap(mongoUrlRepository::save)
                .map(mongoUrlConverter::convertToDomain)
    }

    override fun findById(id: String): Mono<Url> {
        return mongoUrlRepository.findById(id)
                .subscribeOn(Schedulers.boundedElastic())
                .map(mongoUrlConverter::convertToDomain)
    }

    override fun findUrlByToken(token: String): Mono<Url> {
        return mongoUrlRepository
                .findByToken(token)
                .subscribeOn(Schedulers.boundedElastic())
                .map(mongoUrlConverter::convertToDomain)
    }

    override fun findAll(): Flux<Url> {
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