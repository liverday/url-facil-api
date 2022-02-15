package com.liverday.url.facil.url.adapters.mongodb.converters

import com.liverday.url.facil.url.adapters.mongodb.entities.MongoUrlData
import com.liverday.url.facil.url.domain.url.entities.Url
import com.liverday.url.facil.url.ports.converters.url.UrlConverter
import java.util.*

class MongoUrlConverter : UrlConverter<MongoUrlData> {
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
                null,
                domain.link,
                domain.token,
                domain.createdAt,
                domain.updatedAt
        )
    }
}