package com.liverday.shortlyl.domain.url

import com.liverday.shortlyl.domain.AggregateRoot
import com.liverday.shortlyl.domain.exceptions.Error
import java.time.LocalDateTime

data class Url(
        val id: UrlID,
        val link: String?,
        var token: String? = null,
        val createdAt: LocalDateTime = LocalDateTime.now(),
        var updatedAt: LocalDateTime = LocalDateTime.now()
) : AggregateRoot<UrlID>(id) {
    override fun validate(): List<Error> {
        val errors = mutableListOf<Error>()
        if (link == null)
            errors.add(Error("The link is required"))

        return errors
    }


}