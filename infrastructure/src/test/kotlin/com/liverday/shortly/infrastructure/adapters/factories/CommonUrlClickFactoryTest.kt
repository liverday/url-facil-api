package com.liverday.shortly.infrastructure.adapters.factories

import com.liverday.shortlyl.domain.url.Url
import com.liverday.shortly.application.ports.usecases.url.CreateUrlClickRequest
import com.liverday.shortlyl.domain.exceptions.DomainCreationException
import com.liverday.shortlyl.domain.url.UrlID
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class CommonUrlClickFactoryTest {
    private lateinit var commonUrlClickFactory: CommonUrlClickFactory

    @BeforeEach
    fun setUp() {
        commonUrlClickFactory = CommonUrlClickFactory()
    }

    @Test
    fun shouldCreateAValidUrlClick() {
        val url = sampleUrl()
        val request = CreateUrlClickRequest(
                url,
                platform = "Platform",
                device = "Desktop",
                browser = "Chrome",
                ip = ""
        )

        val either = commonUrlClickFactory.create(request)
        Assertions.assertFalse(either.isLeft())

        val urlClick = either.getRight()
        Assertions.assertEquals(urlClick.urlId, url.id)

        Assertions.assertEquals(urlClick.platform, "Platform")
        Assertions.assertEquals(urlClick.device, "Desktop")
        Assertions.assertEquals(urlClick.browser, "Chrome")
        Assertions.assertEquals(urlClick.country, "")
    }

    @Test
    fun shouldThrowAnExceptionIfValidationFails() {
        val url = sampleUrl()
        val request = CreateUrlClickRequest(
                url,
                platform = "Platform",
                device = "Desktop",
                browser = "",
                ip = ""
        )

        val either = commonUrlClickFactory.create(request)
        Assertions.assertTrue(either.isLeft())

        val exception = either.getLeft()
        Assertions.assertEquals(exception.errors.size, 1)
        Assertions.assertEquals(exception::class.java, DomainCreationException::class.java)
    }

    private fun sampleUrl(): Url {
        return Url(
                UrlID.from("id"),
                "link.com",
                "abcde"
        )
    }
}