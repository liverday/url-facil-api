package com.liverday.shortly.domain.urlClick

import com.liverday.shortly.domain.AggregateRoot
import com.liverday.shortly.domain.exceptions.Error
import com.liverday.shortly.domain.url.UrlID
import java.time.LocalDateTime

data class UrlClick(
        var id: UrlClickID,
        var urlId: UrlID,
        val platform: String = "",
        val device: String = "",
        val browser: String = "",
        val country: String? = null,
        val createdAt: LocalDateTime = LocalDateTime.now(),
        val updatedAt: LocalDateTime = LocalDateTime.now()
) : AggregateRoot<UrlClickID>(id) {

    companion object {
        fun newUrlClick(
                urlId: UrlID,
                platform: String,
                device: String,
                browser: String,
                country: String? = null
        ): UrlClick {
            return UrlClick(id = UrlClickID.unique(), urlId, platform, device, browser, country)
        }
    }

    override fun validate(): List<Error> {
        val errors = mutableListOf<Error>()

        if (platform == "")
            errors.add(Error("'platform' is required"))
        if (device == "")
            errors.add(Error("'device' is required"))
        if (browser == "")
            errors.add(Error("'browser' is required"))

        return errors
    }
}