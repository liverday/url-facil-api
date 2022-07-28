package com.liverday.shortly.domain

import com.liverday.shortly.domain.exceptions.Error
import java.util.*

abstract class Entity<ID : com.liverday.shortly.domain.Identifier>(private val id: ID) {
    init {
        Objects.requireNonNull(id, "'id' should not be null")
    }

    abstract fun validate(): List<com.liverday.shortly.domain.exceptions.Error>

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is com.liverday.shortly.domain.Entity<*>) return false

        return (id == other.id)
    }

    override fun hashCode(): Int {
        return id.hashCode()
    }
}