package com.liverday.shortly.domain.urlClick

import com.liverday.shortly.domain.url.UrlID
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

class UrlClickTest {
    @Test
    fun givenValidParams_whenCallUrlConstructor_shouldInstantiateIt() {
        val urlID = com.liverday.shortly.domain.url.UrlID.unique()
        val expectedBrowser = "Chrome"
        val expectedPlatform = "Linux"
        val expectedDevice = "Notebook"

        val urlClick = UrlClick.newUrlClick(
                urlID,
                device = expectedDevice,
                browser = expectedBrowser,
                platform = expectedPlatform
        )

        val errors = urlClick.validate()

        Assertions.assertEquals(urlClick.device, expectedDevice)
        Assertions.assertEquals(urlClick.browser, expectedBrowser)
        Assertions.assertEquals(urlClick.platform, expectedPlatform)

        Assertions.assertEquals(errors.size, 0)
    }

    @Test
    fun givenInvalidParams_whenCallUrlConstructorAndCallValidate_shouldReturnErrors() {
        val urlID = com.liverday.shortly.domain.url.UrlID.unique()
        val urlClick = UrlClick.newUrlClick(urlID, "", "", "")

        val errors = urlClick.validate()

        Assertions.assertEquals(errors.size, 3)
    }
}