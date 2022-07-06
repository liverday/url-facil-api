package com.liverday.url.facil.application.ports.usecases.url

data class CreateUrlRequest(
        val link: String?,
        val token: String? = null
)