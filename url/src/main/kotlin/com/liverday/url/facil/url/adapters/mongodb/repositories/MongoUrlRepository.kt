package com.liverday.url.facil.url.adapters.mongodb.repositories

import com.liverday.url.facil.url.adapters.mongodb.entities.MongoUrlData
import org.springframework.data.repository.reactive.ReactiveCrudRepository
import org.springframework.stereotype.Repository
import reactor.core.publisher.Mono
import java.util.*

@Repository
interface MongoUrlRepository : ReactiveCrudRepository<MongoUrlData, String> {
    fun findByToken(token: String): Mono<MongoUrlData>
    fun existsByToken(token: String): Mono<Boolean>
}