package com.liverday.url.facil.domain.url.entities

import java.time.LocalDateTime

data class UrlClick(
        var id: String? = null,
        var urlId: String? = null,
        val platform: String,
        val device: String,
        val browser: String,
        val country: String,
        val createdAt: LocalDateTime = LocalDateTime.now(),
        val updatedAt: LocalDateTime = LocalDateTime.now()
)