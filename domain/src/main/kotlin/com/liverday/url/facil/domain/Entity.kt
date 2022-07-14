package com.liverday.url.facil.domain

import java.util.*

abstract class Entity<ID : Identifier>(private val id: ID) {
    init {
        Objects.requireNonNull(id, "'id' should not be null")
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is Entity<*>) return false

        return (id == other.id)
    }

    override fun hashCode(): Int {
        return id.hashCode()
    }
}