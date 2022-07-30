package com.liverday.shortly.application.usecases.url

import com.liverday.shortly.application.ports.database.url.UrlDatabaseGateway
import com.liverday.shortly.application.ports.factories.url.UrlFactory
import com.liverday.shortly.application.ports.usecases.url.CreateUrlRequest
import com.liverday.shortly.application.ports.usecases.url.CreateUrlTokenInputBoundary
import com.liverday.shortly.domain.Either
import com.liverday.shortly.domain.url.Url
import com.liverday.shortly.domain.url.UrlID
import io.github.glytching.junit.extension.random.RandomBeansExtension
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.junit.jupiter.api.extension.Extensions
import org.mockito.ArgumentMatchers
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.junit.jupiter.MockitoExtension
import reactor.core.publisher.Mono
import reactor.test.StepVerifier
import java.util.*

@Extensions(value = [
    ExtendWith(RandomBeansExtension::class),
    ExtendWith(MockitoExtension::class)
])
class CreateUrlTest {
    @Mock
    private lateinit var urlDatabaseGateway: UrlDatabaseGateway

    @Mock
    private lateinit var createUrlTokenInputBoundary: CreateUrlTokenInputBoundary

    @Mock
    private lateinit var urlFactory: UrlFactory

    private lateinit var createUrl: CreateUrl

    @BeforeEach
    fun setup() {
        createUrl = CreateUrl(urlDatabaseGateway, createUrlTokenInputBoundary, urlFactory)
    }

    @Test
    fun givenValidParamsWithoutToken_whenCalledExecute_shouldCreateUrlSuccessfully() {
        val request = CreateUrlRequest(
                link = "http://link.com"
        )
        val expectedUrl = Url(id = UrlID.unique(), link = "http://link.com")

        `when`(createUrlTokenInputBoundary.execute()).thenReturn(Mono.just(UUID.randomUUID().toString()))
        `when`(urlFactory.create(request)).thenReturn(Either.Right(expectedUrl))
        `when`(urlDatabaseGateway.save(expectedUrl)).thenReturn(Mono.just(expectedUrl))
        `when`(urlDatabaseGateway.existsByToken(ArgumentMatchers.anyString())).thenReturn(Mono.just(false))

        StepVerifier.create(createUrl.execute(request))
                .expectNext(expectedUrl)
                .verifyComplete()
    }
}