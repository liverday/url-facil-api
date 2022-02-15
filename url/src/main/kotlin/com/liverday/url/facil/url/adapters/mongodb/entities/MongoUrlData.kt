package com.liverday.url.facil.url.adapters.mongodb.entities

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document
import java.time.LocalDateTime
import java.util.*

@Document("urls")
data class MongoUrlData(
    @Id
    val id: String? = null,
    val link: String,
    val token: String? = null,
    val clicks: Int,
    val createdAt: LocalDateTime,
    val updatedAt: LocalDateTime
)