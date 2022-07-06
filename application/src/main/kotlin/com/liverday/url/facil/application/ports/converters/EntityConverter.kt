package com.liverday.url.facil.application.ports.converters

interface EntityConverter<I, O> {
    fun convertToDomain(entity: O): I
    fun convertToEntity(domain: I): O
}