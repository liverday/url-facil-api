package com.liverday.shortly.domain.urlClick

import com.liverday.shortly.domain.Identifier
import java.util.*

class UrlClickID private constructor(private val value: String) : Identifier() {
    init {
        Objects.requireNonNull(value)
    }

    companion object {
        fun unique() = from(UUID.randomUUID())
        fun from(value: String) = UrlClickID(value)
        private fun from(value: UUID) = UrlClickID(value.toString().lowercase())
    }

    override fun getValue(): String {
        return value;
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is UrlClickID) return false

        return value === other.value
    }

    override fun hashCode(): Int {
        return value.hashCode()
    }
}