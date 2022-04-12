package com.liverday.url.facil.url.ports.converters

interface EntityConverter<I, O> {
    fun convertToDomain(entity: O): I
    fun convertToEntity(domain: I): O
}