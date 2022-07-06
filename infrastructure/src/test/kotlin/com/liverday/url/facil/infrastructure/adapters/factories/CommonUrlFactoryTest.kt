package com.liverday.url.facil.infrastructure.adapters.factories

import com.liverday.url.facil.domain.url.exceptions.InvalidUrlArgumentsException
import com.liverday.url.facil.application.ports.usecases.url.CreateUrlRequest
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
        val url = commonUrlFactory.create(request)

        // then
        Assertions.assertEquals(url.link, request.link)
        Assertions.assertEquals(url.token, request.token)
    }

    @Test
    fun shouldCreateUrlWithoutToken() {
        // given
        val request = CreateUrlRequest("https://link.com")

        // when
        val url = commonUrlFactory.create(request)

        // then
        Assertions.assertEquals(url.link, request.link)
        Assertions.assertEquals(url.token, null)
    }

    @Test
    fun shouldThrowErrorWhenUrlIsWithoutLink() {
        // given
        val request = CreateUrlRequest(null)

        // when
        Assertions.assertThrows(InvalidUrlArgumentsException::class.java) {
            commonUrlFactory.create(request)
        }
    }
}