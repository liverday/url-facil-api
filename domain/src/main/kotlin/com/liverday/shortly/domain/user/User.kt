package com.liverday.shortly.domain.user

import com.liverday.shortly.domain.AggregateRoot
import com.liverday.shortly.domain.exceptions.Error
import java.time.LocalDateTime

data class User(
        val id: UserID,
        val email: String = "",
        val firstName: String = "",
        val lastName: String = "",
        val createdAt: LocalDateTime = LocalDateTime.now(),
        var updatedAt: LocalDateTime = LocalDateTime.now()
) : com.liverday.shortly.domain.AggregateRoot<UserID>(id) {
    companion object {
        fun newUser(email: String, firstName: String, lastName: String): User {
            return User(UserID.unique(), email, firstName, lastName)
        }
    }

    override fun validate(): List<Error> {
        val errors = mutableListOf<Error>()

        if (email === "") {
            errors.add(Error("The email is required"))
        }

        if (firstName === "") {
            errors.add(Error("The first name is required"))
        }

        if (lastName === "") {
            errors.add(Error("The last name is required"))
        }

        return errors;
    }
}