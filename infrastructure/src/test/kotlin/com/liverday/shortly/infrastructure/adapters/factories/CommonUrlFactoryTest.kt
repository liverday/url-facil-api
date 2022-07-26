package com.liverday.shortly.infrastructure.adapters.factories

import com.liverday.shortly.application.ports.usecases.url.CreateUrlRequest
import com.liverday.shortlyl.domain.exceptions.DomainCreationException
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class CommonUrlFactoryTest {

    lateinit var commonUrlFactory: CommonUrlFactory

    @BeforeEach
    fun setUp() {
        commonUrlFactory = CommonUrlFactory()
    }

    @Test
    fun shouldCreateUrlWithValidPayload() {
        // given
        val request = CreateUrlRequest("https://link.com", "teste")

        // when
        val either = commonUrlFactory.create(request)

        // then
        Assertions.assertFalse(either.isLeft())

        val url = either.getRight()
        Assertions.assertEquals(url.link, request.link)
        Assertions.assertEquals(url.token, request.token)
    }

    @Test
    fun shouldCreateUrlWithoutToken() {
        // given
        val request = CreateUrlRequest("https://link.com")

        // when
        val either = commonUrlFactory.create(request)

        // then
        Assertions.assertFalse(either.isLeft())

        val url = either.getRight()
        Assertions.assertEquals(url.link, request.link)
        Assertions.assertEquals(url.token, null)
    }

    @Test
    fun shouldThrownAnExceptionIfTheRequestWasSentWithoutLink() {
        // given
        val request = CreateUrlRequest(null)

        // when
        val either = commonUrlFactory.create(request)

        // then
        Assertions.assertTrue(either.isLeft())
        Assertions.assertEquals(either.getLeft()::class.java, DomainCreationException::class.java)
    }
}