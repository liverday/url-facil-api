package com.liverday.shortly.application.usecases.url

import com.liverday.shortly.application.ports.database.url.UrlDatabaseGateway
import com.liverday.shortly.application.ports.usecases.url.CreateUrlTokenInputBoundary
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono
import kotlin.random.Random

class CreateUrlToken(
        private val urlGateway: UrlDatabaseGateway
) : CreateUrlTokenInputBoundary {
    private val tokenLength = 6
    private val upperCaseSymbols = (65..90) //ASCII Range
            .map { it.toChar() }
            .toCharArray()

    private val lowerCaseSymbols = (97..122)
            .map { it.toChar() }
            .toCharArray()

    private fun generateToken(length: Int = tokenLength): Mono<String> {
        val symbols = upperCaseSymbols + lowerCaseSymbols
        return Flux
                .just(*(0..length).toList().toTypedArray())
                .map {
                    val nextIndex = Random.nextInt(0, symbols.size)
                    symbols[nextIndex]
                }
                .collectList()
                .map {
                    it.joinToString("")
                }
    }

    override fun execute(): Mono<String> {
        return generateToken()
                .flatMap { token ->
                    urlGateway.existsByToken(token)
                        .flatMap { exists ->
                            if (exists) {
                                execute()
                            } else {
                                Mono.just(token)
                            }
                        }
                }
    }
}