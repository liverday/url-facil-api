package com.liverday.url.facil.infrastructure.adapters.mongodb.gateways

import com.liverday.url.facil.infrastructure.adapters.mongodb.entities.MongoUrlClickData
import com.liverday.url.facil.infrastructure.adapters.mongodb.repositories.MongoUrlClicksRepository
import com.liverday.url.facil.domain.url.entities.UrlClick
import com.liverday.url.facil.application.ports.converters.EntityConverter
import com.liverday.url.facil.application.ports.database.url.UrlClicksDatabaseGateway
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

class MongoUrlClicksDatabaseGateway(
        private val mongoUrlClicksRepository: MongoUrlClicksRepository,
        private val mongoUrlClicksConverter: EntityConverter<UrlClick, MongoUrlClickData>,
) : UrlClicksDatabaseGateway {
    override fun save(urlClick: UrlClick): Mono<UrlClick> {
        return Mono.just(mongoUrlClicksConverter.convertToEntity(urlClick))
                .flatMap(mongoUrlClicksRepository::save)
                .map(mongoUrlClicksConverter::convertToDomain)

    }

    override fun findAll(): Flux<UrlClick> {
        return mongoUrlClicksRepository.findAll()
                .map(mongoUrlClicksConverter::convertToDomain)
    }

    override fun findAllByUrlId(urlId: String): Flux<UrlClick> {
        return mongoUrlClicksRepository.findAllByUrlId(urlId)
                .map(mongoUrlClicksConverter::convertToDomain)
    }
}