package com.liverday.shortly.infrastructure.adapters.mongodb.entities

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document
import java.time.LocalDateTime

@Document("urls")
data class MongoUrlData(
    @Id
    var id: String? = null,
    val link: String,
    val token: String? = null,
    val createdAt: LocalDateTime,
    val updatedAt: LocalDateTime
)