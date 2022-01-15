package com.liverday.url.facil.domain.url.entities

import java.time.LocalDateTime
import java.util.*

data class Url(
        val id: UUID = UUID.randomUUID(),
        val shorten_link: String,
        val link: String,
        val token: String? = null,
        val createdAt: LocalDateTime = LocalDateTime.now(),
        val updatedAt: LocalDateTime = LocalDateTime.now()
)