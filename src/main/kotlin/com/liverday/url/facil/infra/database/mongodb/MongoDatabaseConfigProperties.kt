package com.liverday.url.facil.infra.database.mongodb

import com.liverday.url.facil.domain.config.DatabaseConfigProperties
import org.springframework.boot.context.properties.ConfigurationProperties

@Suppress("unused")
@ConfigurationProperties(prefix = "mongo")
data class MongoDatabaseConfigProperties(
        private val host: String,
        private val port: String,
        private val user: String,
        private val password: String,
        private val database: String
) : DatabaseConfigProperties {
    override fun getHost(): String {
        return host
    }

    override fun getPort(): String {
        return port
    }

    override fun getUser(): String {
        return user
    }

    override fun getPassword(): String {
        return password
    }

    override fun getDatabase(): String {
        return database
    }
}