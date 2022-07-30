package com.liverday.shortly.infrastructure.adapters.logger

import com.liverday.shortly.domain.logger.Logger
import org.slf4j.LoggerFactory

class Slf4jLogger(clazz: Class<*>) : Logger {
    private val logger = LoggerFactory.getLogger(clazz)

    override fun info(message: String, vararg metadata: Any) {
        logger.info(message, *metadata)
    }

    override fun warn(message: String, vararg metadata: Any) {
        logger.warn(message, *metadata)
    }

    override fun error(message: String, vararg metadata: Any) {
        logger.error(message, *metadata)
    }

    override fun debug(message: String, vararg metadata: Any) {
        logger.debug(message, *metadata)
    }
}