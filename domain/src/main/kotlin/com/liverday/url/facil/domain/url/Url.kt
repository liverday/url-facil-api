package com.liverday.url.facil.domain.url

import com.liverday.url.facil.domain.AggregateRoot
import java.time.LocalDateTime

data class Url(
        val id: UrlID,
        val link: String,
        var token: String? = null,
        val createdAt: LocalDateTime = LocalDateTime.now(),
        var updatedAt: LocalDateTime = LocalDateTime.now()
) : AggregateRoot<UrlID>(id)