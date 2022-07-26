package com.liverday.shortly.infrastructure.adapters.mongodb.entities

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document
import java.time.LocalDateTime

@Document("url_clicks")
data class MongoUrlClickData(
        @Id
        var id: String? = null,
        var urlId: String? = null,
        val platform: String,
        val device: String,
        val browser: String,
        val country: String?,
        val createdAt: LocalDateTime = LocalDateTime.now(),
        val updatedAt: LocalDateTime = LocalDateTime.now()
)