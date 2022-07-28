package com.liverday.shortly.domain.url

import com.liverday.shortly.domain.Identifier
import java.util.*

class UrlID private constructor (private val value: String) : com.liverday.shortly.domain.Identifier() {

    init {
        Objects.requireNonNull(value)
    }

    companion object {
        fun unique() = com.liverday.shortly.domain.url.UrlID.Companion.from(UUID.randomUUID())
        fun from(value: String) = com.liverday.shortly.domain.url.UrlID(value)
        private fun from(value: UUID) = com.liverday.shortly.domain.url.UrlID(value.toString().lowercase())
    }

    override fun getValue(): String {
        return value;
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is com.liverday.shortly.domain.url.UrlID) return false

        return value == other.value
    }

    override fun hashCode(): Int {
        return value.hashCode()
    }
}