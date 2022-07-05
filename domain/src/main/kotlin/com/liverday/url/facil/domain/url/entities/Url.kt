package com.liverday.url.facil.domain.url.entities

import java.time.LocalDateTime

data class Url(
        val id: String? = null,
        val link: String,
        var token: String? = null,
        val createdAt: LocalDateTime = LocalDateTime.now(),
        var updatedAt: LocalDateTime = LocalDateTime.now()
)