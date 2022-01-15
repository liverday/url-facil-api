package com.liverday.url.facil.adapters.mongodb.converters

import com.liverday.url.facil.adapters.mongodb.entities.MongoUrlData
import com.liverday.url.facil.domain.url.entities.Url
import com.liverday.url.facil.ports.converters.url.UrlConverter
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
        val id = UUID.randomUUID()

        return MongoUrlData(
                id,
                domain.link,
                domain.token,
                domain.createdAt,
                domain.updatedAt
        )
    }
}