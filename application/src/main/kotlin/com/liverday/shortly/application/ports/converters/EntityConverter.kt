package com.liverday.shortly.application.ports.converters

interface EntityConverter<I, O> {
    fun convertToDomain(entity: O): I
    fun convertToEntity(domain: I): O
}