package com.liverday.shortly.infrastructure.adapters.mongodb.converters

import com.liverday.shortly.infrastructure.adapters.mongodb.entities.MongoUrlClickData
import com.liverday.shortlyl.domain.urlClick.UrlClick
import com.liverday.shortly.application.ports.converters.EntityConverter
import com.liverday.shortlyl.domain.url.UrlID
import com.liverday.shortlyl.domain.urlClick.UrlClickID

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