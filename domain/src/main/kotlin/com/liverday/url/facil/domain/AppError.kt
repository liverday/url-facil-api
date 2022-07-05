package com.liverday.url.facil.domain

data class Validation (
    val field: String,
    val message: String
)

data class AppError(
    val status: Int,
    val message: String,
    val errors: List<Validation> = listOf()
)