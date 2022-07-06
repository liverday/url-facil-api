package com.liverday.url.facil.infrastructure.adapters.mongodb.converters

import com.liverday.url.facil.infrastructure.adapters.mongodb.entities.MongoUrlData
import com.liverday.url.facil.domain.url.entities.Url
import com.liverday.url.facil.application.ports.converters.EntityConverter

class MongoUrlConverter : EntityConverter<Url, MongoUrlData> {
    override fun convertToDomain(entity: MongoUrlData): Url {
        return Url(
                entity.id,
                entity.link,
                entity.token,
                entity.createdAt,
                entity.updatedAt
        )
    }

    override fun convertToEntity(domain: Url): MongoUrlData {
        return MongoUrlData(
                domain.id,
                domain.link,
                domain.token,
                domain.createdAt,
                domain.updatedAt
        )
    }
}