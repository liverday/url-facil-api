package com.liverday.shortlyl.domain.urlClick

import com.liverday.shortlyl.domain.AggregateRoot
import com.liverday.shortlyl.domain.exceptions.Error
import com.liverday.shortlyl.domain.url.UrlID
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