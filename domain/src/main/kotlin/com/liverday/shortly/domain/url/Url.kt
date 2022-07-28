package com.liverday.shortly.domain.url

import com.liverday.shortly.domain.AggregateRoot
import com.liverday.shortly.domain.exceptions.Error
import com.liverday.shortly.domain.user.UserID
import java.time.LocalDateTime

data class Url(
        val id: com.liverday.shortly.domain.url.UrlID,
        val link: String?,
        var token: String? = null,
        val createdAt: LocalDateTime = LocalDateTime.now(),
        var updatedAt: LocalDateTime = LocalDateTime.now(),
        val userId: UserID? = null
) : com.liverday.shortly.domain.AggregateRoot<com.liverday.shortly.domain.url.UrlID>(id) {
    companion object {
        fun newUrl(link: String?, token: String?): com.liverday.shortly.domain.url.Url {
            return com.liverday.shortly.domain.url.Url(id = com.liverday.shortly.domain.url.UrlID.Companion.unique(), link, token)
        }
    }

    override fun validate(): List<com.liverday.shortly.domain.exceptions.Error> {
        val errors = mutableListOf<com.liverday.shortly.domain.exceptions.Error>()
        if (link == null)
            errors.add(com.liverday.shortly.domain.exceptions.Error("The link is required"))

        return errors
    }
}