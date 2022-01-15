package com.liverday.url.facil.ports.converters.url

import com.liverday.url.facil.domain.url.entities.Url

interface UrlConverter<I> {
    fun convertToDomain(entity: I): Url
    fun convertToEntity(domain: Url): I
}