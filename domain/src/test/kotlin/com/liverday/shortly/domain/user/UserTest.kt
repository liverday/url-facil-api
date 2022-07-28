package com.liverday.shortly.domain.user

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

class UserTest {

    @Test
    fun givenValidParams_whenCallUserConstructor_shouldInstantiateIt() {
        val expectedEmail = "fulano.beltrano@email.com"
        val expectedFirstName = "fulano"
        val expectedLastName = "beltrano"

        val user = User.newUser(expectedEmail, expectedFirstName, expectedLastName)
        val errors = user.validate()
        Assertions.assertEquals(user.email, expectedEmail)
        Assertions.assertEquals(user.firstName, expectedFirstName)
        Assertions.assertEquals(user.lastName, expectedLastName)

        Assertions.assertTrue(errors.isEmpty())
    }

    @Test
    fun givenInvalidParams_whenCallUserValidate_shouldReturnAListOfErrors() {
        val user = User.newUser("", "", "")

        val errors = user.validate()

        Assertions.assertEquals(errors.size, 3)
    }
}