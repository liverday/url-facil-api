package com.liverday.url.facil.infrastructure.adapters.mongodb.repositories

import com.liverday.url.facil.infrastructure.adapters.mongodb.entities.MongoUrlClickData
import org.springframework.data.repository.reactive.ReactiveCrudRepository
import org.springframework.stereotype.Repository
import reactor.core.publisher.Flux

@Repository
interface MongoUrlClicksRepository : ReactiveCrudRepository<MongoUrlClickData, String> {
    fun findAllByUrlId(urlId: String): Flux<MongoUrlClickData>
}