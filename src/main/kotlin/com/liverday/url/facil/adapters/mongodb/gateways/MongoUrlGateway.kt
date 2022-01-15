package com.liverday.url.facil.adapters.mongodb.gateways

import com.liverday.url.facil.adapters.mongodb.entities.MongoUrlData
import com.liverday.url.facil.adapters.mongodb.repositories.MongoUrlRepository
import com.liverday.url.facil.domain.url.entities.Url
import com.liverday.url.facil.ports.converters.url.UrlConverter
import com.liverday.url.facil.ports.database.url.UrlGateway
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono
import java.util.*

class MongoUrlGateway(
        private val mongoUrlRepository: MongoUrlRepository,
        private val mongoUrlConverter: UrlConverter<MongoUrlData>
) : UrlGateway {
    override fun create(url: Url): Mono<Url> {
        return Mono.just(mongoUrlConverter.convertToEntity(url))
                .flatMap(mongoUrlRepository::save)
                .map(mongoUrlConverter::convertToDomain)
    }

    override fun findById(id: UUID): Mono<Url> {
        return mongoUrlRepository.findById(id)
                .map(mongoUrlConverter::convertToDomain)
    }

    override fun findUrlByToken(token: String): Mono<Url> {
        return mongoUrlRepository
                .findByToken(token)
                .map(mongoUrlConverter::convertToDomain)
    }

    override fun findAll(): Flux<Url> {
        return mongoUrlRepository
                .findAll()
                .map(mongoUrlConverter::convertToDomain)
    }

    override fun existsByToken(token: String?): Mono<Boolean> {
        if (token == null)
            return Mono.just(false)

        return mongoUrlRepository.existsByToken(token)
    }
}