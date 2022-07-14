package com.liverday.url.facil.domain.urlClick

import com.liverday.url.facil.domain.AggregateRoot
import com.liverday.url.facil.domain.url.UrlID
import java.time.LocalDateTime

data class UrlClick(
        var id: UrlClickID,
        var urlId: UrlID,
        val platform: String,
        val device: String,
        val browser: String,
        val country: String,
        val createdAt: LocalDateTime = LocalDateTime.now(),
        val updatedAt: LocalDateTime = LocalDateTime.now()
) : AggregateRoot<UrlClickID>(id)