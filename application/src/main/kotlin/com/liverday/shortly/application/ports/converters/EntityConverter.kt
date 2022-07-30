package com.liverday.shortly.application.ports.converters

import com.liverday.shortly.domain.AggregateRoot

interface EntityConverter<I : AggregateRoot<*>, O> {
    fun convertToDomain(entity: O): I
    fun convertToEntity(domain: I): O
}