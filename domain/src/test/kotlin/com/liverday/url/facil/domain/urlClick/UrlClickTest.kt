package com.liverday.url.facil.domain.urlClick

import com.liverday.url.facil.domain.url.UrlID
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

class UrlClickTest {
    @Test
    fun givenValidParams_whenCallUrlConstructor_shouldInstantiateIt() {
        val urlID = UrlID.unique()
        val urlClickID = UrlClickID.unique()
        val expectedBrowser = "Chrome"
        val expectedPlatform = "Linux"
        val expectedDevice = "Notebook"

        val urlClick = UrlClick(
                urlClickID,
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
        val urlID = UrlID.unique()
        val urlClickID = UrlClickID.unique()
        val urlClick = UrlClick(urlClickID, urlID)

        val errors = urlClick.validate()

        Assertions.assertEquals(errors.size, 3)
    }
}