package com.liverday.url.facil.url.domain.url.entities

import java.time.LocalDateTime
import java.util.*

data class Url(
        val id: String? = null,
        val link: String,
        var token: String? = null,
        var clicks: Int = 0,
        val createdAt: LocalDateTime = LocalDateTime.now(),
        var updatedAt: LocalDateTime = LocalDateTime.now()
)