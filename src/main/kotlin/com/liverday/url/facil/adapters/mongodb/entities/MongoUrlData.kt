package com.liverday.url.facil.adapters.mongodb.entities

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document
import java.time.LocalDate
import java.time.LocalDateTime
import java.util.*

@Document
data class MongoUrlData(
    @Id
    val id: UUID,
    val link: String,
    val token: String? = null,
    val createdAt: LocalDateTime,
    val updatedAt: LocalDateTime
)