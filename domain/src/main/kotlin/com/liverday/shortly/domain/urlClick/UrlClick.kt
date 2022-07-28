package com.liverday.shortly.domain.urlClick

import com.liverday.shortly.domain.AggregateRoot
import com.liverday.shortly.domain.exceptions.Error
import com.liverday.shortly.domain.url.UrlID
import java.time.LocalDateTime

data class UrlClick(
        var id: UrlClickID,
        var urlId: com.liverday.shortly.domain.url.UrlID,
        val platform: String = "",
        val device: String = "",
        val browser: String = "",
        val country: String? = null,
        val createdAt: LocalDateTime = LocalDateTime.now(),
        val updatedAt: LocalDateTime = LocalDateTime.now()
) : com.liverday.shortly.domain.AggregateRoot<UrlClickID>(id) {

    companion object {
        fun newUrlClick(
                urlId: com.liverday.shortly.domain.url.UrlID,
                platform: String,
                device: String,
                browser: String,
                country: String? = null
        ): UrlClick {
            return UrlClick(id = UrlClickID.unique(), urlId, platform, device, browser, country)
        }
    }

    override fun validate(): List<com.liverday.shortly.domain.exceptions.Error> {
        val errors = mutableListOf<com.liverday.shortly.domain.exceptions.Error>()

        if (platform == "")
            errors.add(com.liverday.shortly.domain.exceptions.Error("'platform' is required"))
        if (device == "")
            errors.add(com.liverday.shortly.domain.exceptions.Error("'device' is required"))
        if (browser == "")
            errors.add(com.liverday.shortly.domain.exceptions.Error("'browser' is required"))

        return errors
    }
}