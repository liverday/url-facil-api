package com.liverday.url.facil.infrastructure

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.context.properties.ConfigurationPropertiesScan
import org.springframework.boot.runApplication

@SpringBootApplication
@ConfigurationPropertiesScan(value = ["com.liverday.url.facil.infrastructure"])
class UrlFacilApiApplication

fun main(args: Array<String>) {
	runApplication<UrlFacilApiApplication>(*args)
}
