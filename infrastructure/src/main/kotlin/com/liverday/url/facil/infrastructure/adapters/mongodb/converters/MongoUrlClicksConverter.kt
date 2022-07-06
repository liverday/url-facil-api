package com.liverday.url.facil.infrastructure.adapters.mongodb.converters

import com.liverday.url.facil.infrastructure.adapters.mongodb.entities.MongoUrlClickData
import com.liverday.url.facil.domain.url.entities.UrlClick
import com.liverday.url.facil.application.ports.converters.EntityConverter

class MongoUrlClicksConverter : EntityConverter<UrlClick, MongoUrlClickData> {

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