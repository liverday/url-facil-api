package com.liverday.url.facil.domain.url

import com.liverday.url.facil.domain.Identifier
import java.util.*

class UrlID private constructor (private val value: String) : Identifier() {

    init {
        Objects.requireNonNull(value)
    }

    companion object {
        fun unique() = from(UUID.randomUUID())
        fun from(value: String) = UrlID(value)
        fun from(value: UUID) = UrlID(value.toString().lowercase())
    }

    override fun getValue(): String {
        return value;
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is UrlID) return false

        return value == other.value
    }

    override fun hashCode(): Int {
        return value.hashCode()
    }
}