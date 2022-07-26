package com.liverday.shortly.infrastructure

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.context.properties.ConfigurationPropertiesScan
import org.springframework.boot.runApplication

@SpringBootApplication
@ConfigurationPropertiesScan(value = ["com.liverday.shortly.infrastructure"])
class UrlFacilApiApplication

fun main(args: Array<String>) {
	runApplication<UrlFacilApiApplication>(*args)
}
