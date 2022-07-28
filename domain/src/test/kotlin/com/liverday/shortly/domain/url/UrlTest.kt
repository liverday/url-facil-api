package com.liverday.shortly.domain.url

import com.liverday.shortly.domain.exceptions.Error
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

class UrlTest {

    @Test
    fun givenValidParams_whenCallUrlConstructor_shouldInstantiateIt() {
        val expectedLink = "expectedLink"
        val expectedToken = "abc"

        val url = com.liverday.shortly.domain.url.Url.newUrl(expectedLink, expectedToken)

        Assertions.assertEquals(url.link, expectedLink)
        Assertions.assertEquals(url.token, expectedToken)
    }

    @Test
    fun givenInvalidParams_whenCallUrlConstructorAndCallValidate_shouldReturnErrors() {
        val url = com.liverday.shortly.domain.url.Url.newUrl(null, null)
        val errors: List<com.liverday.shortly.domain.exceptions.Error> = url.validate()

        Assertions.assertEquals(errors.size, 1)
        Assertions.assertEquals(errors.first().message, "The link is required")
    }
}