package com.liverday.shortly.infrastructure.adapters.mongodb.converters

import com.liverday.shortly.infrastructure.adapters.mongodb.entities.MongoUrlData
import com.liverday.shortly.domain.url.Url
import com.liverday.shortly.application.ports.converters.EntityConverter
import com.liverday.shortly.domain.url.UrlID

class MongoUrlConverter : EntityConverter<com.liverday.shortly.domain.url.Url, MongoUrlData> {
    override fun convertToDomain(entity: MongoUrlData): com.liverday.shortly.domain.url.Url {
        return com.liverday.shortly.domain.url.Url(
                com.liverday.shortly.domain.url.UrlID.from(entity.id!!),
                entity.link,
                entity.token,
                entity.createdAt,
                entity.updatedAt
        )
    }

    override fun convertToEntity(domain: com.liverday.shortly.domain.url.Url): MongoUrlData {
        return MongoUrlData(
                domain.id.getValue(),
                domain.link!!,
                domain.token,
                domain.createdAt,
                domain.updatedAt
        )
    }
}