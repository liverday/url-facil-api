package com.liverday.url.facil.url.adapters.factories

import com.liverday.url.facil.url.domain.url.entities.Url
import com.liverday.url.facil.url.ports.usecases.url.CreateUrlClickRequest
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class CommonUrlClickFactoryTest {
    lateinit var commonUrlClickFactory: CommonUrlClickFactory

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
                country = "Brazil"
        )

        val urlClick = commonUrlClickFactory.create(request)

        Assertions.assertEquals(urlClick.url, url)
        Assertions.assertEquals(urlClick.platform, "Platform")
        Assertions.assertEquals(urlClick.device, "Desktop")
        Assertions.assertEquals(urlClick.browser, "Chrome")
        Assertions.assertEquals(urlClick.country, "Brazil")
    }

    private fun sampleUrl(): Url {
        return Url(
                "id",
                "link.com",
                "abcde"
        )
    }
}