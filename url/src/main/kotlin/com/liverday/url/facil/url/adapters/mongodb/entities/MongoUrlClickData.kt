package com.liverday.url.facil.url.adapters.mongodb.entities

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document
import org.springframework.data.mongodb.core.mapping.DocumentReference
import java.time.LocalDateTime

@Document("url_clicks")
data class MongoUrlClickData(
        @Id
        var id: String? = null,

        @DocumentReference(lookup = "{ 'url': ?#{#self._id} ")
        var url: MongoUrlData? = null,

        val platform: String,
        val device: String,
        val browser: String,
        val country: String,
        val createdAt: LocalDateTime = LocalDateTime.now(),
        val updatedAt: LocalDateTime = LocalDateTime.now()
)