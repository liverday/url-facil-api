package com.liverday.url.facil.domain.url

import com.liverday.url.facil.domain.exceptions.Error
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

class UrlTest {

    @Test
    fun givenValidParams_whenCallUrlConstructor_shouldInstantiateIt() {
        val expectedLink = "expectedLink"
        val expectedToken = "abc"

        val url = Url(id = UrlID.unique(), link = expectedLink, token = expectedToken)

        Assertions.assertEquals(url.link, expectedLink)
        Assertions.assertEquals(url.token, expectedToken)
    }

    @Test
    fun givenInvalidParams_whenCallUrlConstructorAndCallValidate_shouldReturnErrors() {
        val url = Url(id = UrlID.unique(), link = null, token = null)
        val errors: List<Error> = url.validate()

        Assertions.assertEquals(errors.size, 1)
        Assertions.assertEquals(errors.first().message, "The link is required")
    }
}