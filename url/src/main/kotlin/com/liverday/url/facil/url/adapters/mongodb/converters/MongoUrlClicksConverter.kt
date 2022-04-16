package com.liverday.url.facil.url.adapters.mongodb.converters

import com.liverday.url.facil.url.adapters.mongodb.entities.MongoUrlClickData
import com.liverday.url.facil.url.adapters.mongodb.entities.MongoUrlData
import com.liverday.url.facil.url.domain.url.entities.Url
import com.liverday.url.facil.url.domain.url.entities.UrlClick
import com.liverday.url.facil.url.ports.converters.EntityConverter

class MongoUrlClicksConverter(private val urlConverter: EntityConverter<Url, MongoUrlData>) : EntityConverter<UrlClick, MongoUrlClickData> {

    override fun convertToDomain(entity: MongoUrlClickData): UrlClick {
        return UrlClick(
                entity.id,
                entity.urlId,
                entity.platform,
                entity.device,
                entity.browser,
                entity.country,
                entity.createdAt,
                entity.updatedAt
        )
    }

    override fun convertToEntity(domain: UrlClick): MongoUrlClickData {
        return MongoUrlClickData(
                domain.id,
                domain.urlId,
                domain.platform,
                domain.device,
                domain.browser,
                domain.country,
                domain.createdAt,
                domain.updatedAt
        )
    }
}