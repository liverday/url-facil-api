package com.liverday.url.facil.infrastructure.adapters.mongodb.gateways

import com.liverday.url.facil.infrastructure.adapters.mongodb.converters.MongoUrlConverter
import com.liverday.url.facil.infrastructure.adapters.mongodb.entities.MongoUrlData
import com.liverday.url.facil.infrastructure.adapters.mongodb.repositories.MongoUrlRepository
import com.liverday.url.facil.domain.url.Url
import io.github.glytching.junit.extension.random.Random
import io.github.glytching.junit.extension.random.RandomBeansExtension
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.junit.jupiter.api.extension.Extensions
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito.*
import org.mockito.junit.jupiter.MockitoExtension
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono
import reactor.test.StepVerifier
import java.time.Duration

@Extensions(value = [
    ExtendWith(MockitoExtension::class),
    ExtendWith(RandomBeansExtension::class)
])
class MongoDatabaseGatewayTest {

    @Mock
    lateinit var mongoUrlRepository: MongoUrlRepository

    @Mock
    lateinit var mongoUrlConverter: MongoUrlConverter

    @InjectMocks
    lateinit var mongoUrlDatabaseGateway: MongoUrlDatabaseGateway

    @Test
    fun givenADomain_ShouldBeAbleToSaveAndReturnAnEntity(@Random url: Url, @Random mongoUrlData: MongoUrlData) {
        `when`(mongoUrlConverter.convertToEntity(url)).thenReturn(mongoUrlData)
        `when`(mongoUrlRepository.save(mongoUrlData)).thenReturn(Mono.just(mongoUrlData))
        `when`(mongoUrlConverter.convertToDomain(mongoUrlData)).thenReturn(url)

        StepVerifier.create(mongoUrlDatabaseGateway.save(url))
                .expectNext(url)
                .verifyComplete()

        verify(mongoUrlConverter, times(1)).convertToEntity(url)
        verify(mongoUrlConverter, times(1)).convertToDomain(mongoUrlData)
        verify(mongoUrlRepository, times(1)).save(mongoUrlData)
    }

    @Test
    fun givenAnValidId_ShouldBeAbleToFindUrl(@Random id: String, @Random url: Url, @Random mongoUrlData: MongoUrlData) {
        `when`(mongoUrlConverter.convertToDomain(mongoUrlData)).thenReturn(url)
        `when`(mongoUrlRepository.findById(id)).thenReturn(Mono.just(mongoUrlData))

        StepVerifier.create(mongoUrlDatabaseGateway.findById(id))
                .expectNext(url)
                .verifyComplete()

        verify(mongoUrlConverter, times(1)).convertToDomain(mongoUrlData)
        verify(mongoUrlRepository, times(1)).findById(id)
    }

    @Test
    fun givenAInvalidId_ShouldNotBeAbleToFindUrl(@Random id: String, @Random mongoUrlData: MongoUrlData) {
        `when`(mongoUrlRepository.findById(id)).thenReturn(Mono.empty())

        StepVerifier.create(mongoUrlDatabaseGateway.findById(id))
                .expectNextCount(0)
                .verifyComplete()

        verify(mongoUrlRepository, times(1)).findById(id)
        verify(mongoUrlConverter, times(0)).convertToDomain(mongoUrlData)
    }

    @Test
    fun givenAnValidToken_ShouldBeAbleToFindUrl(@Random token: String, @Random mongoUrlData: MongoUrlData) {
        `when`(mongoUrlRepository.findByToken(token)).thenReturn(Mono.empty())

        StepVerifier.create(mongoUrlDatabaseGateway.findUrlByToken(token))
                .expectNextCount(0)
                .verifyComplete()

        verify(mongoUrlRepository, times(1)).findByToken(token)
        verify(mongoUrlConverter, times(0)).convertToDomain(mongoUrlData)
    }

    @Test
    fun givenAInvalidToken_ShouldNotBeAbleToFindUrl(@Random token: String, @Random mongoUrlData: MongoUrlData) {
        `when`(mongoUrlRepository.findByToken(token)).thenReturn(Mono.empty())

        StepVerifier.create(mongoUrlDatabaseGateway.findUrlByToken(token))
                .expectNextCount(0)
                .verifyComplete()

        verify(mongoUrlRepository, times(1)).findByToken(token)
        verify(mongoUrlConverter, times(0)).convertToDomain(mongoUrlData)
    }

    @Test
    fun givenAnValidFlux_ShouldBeAbleToListUrl(
            @Random mongoUrlDataOne: MongoUrlData,
            @Random mongoUrlDataTwo: MongoUrlData,
            @Random mongoUrlDataThree: MongoUrlData,

            @Random urlOne: Url,
            @Random urlTwo: Url,
            @Random urlThree: Url,
    ) {
        `when`(mongoUrlConverter.convertToDomain(mongoUrlDataOne)).thenReturn(urlOne)
        `when`(mongoUrlConverter.convertToDomain(mongoUrlDataTwo)).thenReturn(urlTwo)
        `when`(mongoUrlConverter.convertToDomain(mongoUrlDataThree)).thenReturn(urlThree)

        `when`(mongoUrlRepository.findAll()).thenReturn(Flux.just(mongoUrlDataOne, mongoUrlDataTwo, mongoUrlDataThree).delayElements(Duration.ofMillis(15)))

        StepVerifier.create(mongoUrlDatabaseGateway.findAll())
                .expectNext(urlOne)
                .expectNext(urlTwo)
                .expectNext(urlThree)
                .verifyComplete()
    }
}