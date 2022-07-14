package com.liverday.url.facil.infrastructure.adapters.mongodb.converters

import com.liverday.url.facil.infrastructure.adapters.mongodb.entities.MongoUrlClickData
import com.liverday.url.facil.domain.urlClick.UrlClick
import com.liverday.url.facil.application.ports.converters.EntityConverter
import com.liverday.url.facil.domain.url.UrlID
import com.liverday.url.facil.domain.urlClick.UrlClickID

class MongoUrlClicksConverter : EntityConverter<UrlClick, MongoUrlClickData> {

    override fun convertToDomain(entity: MongoUrlClickData): UrlClick {
        return UrlClick(
                UrlClickID.from(entity.id!!),
                UrlID.from(entity.urlId!!),
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
                null,
                domain.urlId.getValue(),
                domain.platform,
                domain.device,
                domain.browser,
                domain.country,
                domain.createdAt,
                domain.updatedAt
        )
    }
}