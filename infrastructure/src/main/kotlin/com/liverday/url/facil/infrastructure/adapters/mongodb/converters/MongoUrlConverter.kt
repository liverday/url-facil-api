package com.liverday.url.facil.infrastructure.adapters.mongodb.converters

import com.liverday.url.facil.infrastructure.adapters.mongodb.entities.MongoUrlData
import com.liverday.url.facil.domain.url.Url
import com.liverday.url.facil.application.ports.converters.EntityConverter
import com.liverday.url.facil.domain.url.UrlID

class MongoUrlConverter : EntityConverter<Url, MongoUrlData> {
    override fun convertToDomain(entity: MongoUrlData): Url {
        return Url(
                UrlID.from(entity.id!!),
                entity.link,
                entity.token,
                entity.createdAt,
                entity.updatedAt
        )
    }

    override fun convertToEntity(domain: Url): MongoUrlData {
        return MongoUrlData(
                domain.id.getValue(),
                domain.link,
                domain.token,
                domain.createdAt,
                domain.updatedAt
        )
    }
}