package com.liverday.url.facil.url.adapters.mongodb.repositories

import com.liverday.url.facil.url.adapters.mongodb.entities.MongoUrlClickData
import org.springframework.data.mongodb.repository.Query
import org.springframework.data.repository.query.Param
import org.springframework.data.repository.reactive.ReactiveCrudRepository
import org.springframework.stereotype.Repository
import reactor.core.publisher.Flux
import java.util.*

@Repository
interface MongoUrlClicksRepository : ReactiveCrudRepository<MongoUrlClickData, String> {
    fun findAllByUrlId(urlId: String): Flux<MongoUrlClickData>
}