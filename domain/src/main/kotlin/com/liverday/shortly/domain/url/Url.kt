package com.liverday.shortly.domain.url

import com.liverday.shortly.domain.AggregateRoot
import com.liverday.shortly.domain.exceptions.Error
import com.liverday.shortly.domain.user.UserID
import java.time.LocalDateTime

data class Url(
        val id: UrlID,
        val link: String?,
        var token: String? = null,
        val createdAt: LocalDateTime = LocalDateTime.now(),
        var updatedAt: LocalDateTime = LocalDateTime.now(),
        val userId: UserID? = null
) : com.liverday.shortly.domain.AggregateRoot<UrlID>(id) {
    companion object {
        fun newUrl(link: String?, token: String?): Url {
            return Url(id = UrlID.unique(), link, token)
        }
    }

    override fun validate(): List<Error> {
        val errors = mutableListOf<Error>()
        if (link == null)
            errors.add(Error("The link is required"))

        return errors
    }
}