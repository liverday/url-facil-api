package com.liverday.url.facil.url.ports.converters.url

import com.liverday.url.facil.url.domain.url.entities.Url

interface UrlConverter<I> {
    fun convertToDomain(entity: I): Url
    fun convertToEntity(domain: Url): I
}