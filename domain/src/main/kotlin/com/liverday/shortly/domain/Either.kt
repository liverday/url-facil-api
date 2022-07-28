package com.liverday.shortly.domain

abstract class Either<L, R> private constructor() {

    abstract fun isLeft(): Boolean
    abstract fun isRight(): Boolean

    abstract fun getLeft(): L
    abstract fun getRight(): R

    class Left<L, R>(private val value: L) : com.liverday.shortly.domain.Either<L, R>() {
        override fun isLeft(): Boolean {
            return true
        }

        override fun isRight(): Boolean {
            return false
        }

        override fun getLeft(): L {
            return value;
        }

        override fun getRight(): R {
            throw NoSuchElementException("There is no right in Left");
        }
    }

    class Right<L, R>(private val value: R) : com.liverday.shortly.domain.Either<L, R>() {
        override fun isLeft(): Boolean {
            return false
        }

        override fun isRight(): Boolean {
            return true
        }

        override fun getLeft(): L {
            throw NoSuchElementException("There is no left in Right")
        }

        override fun getRight(): R {
            return value
        }
    }

    companion object {
        fun <L, R> right(value: R): com.liverday.shortly.domain.Either<L, R> = com.liverday.shortly.domain.Either.Right(value)
        fun <L, R> left(value: L): com.liverday.shortly.domain.Either<L, R> = com.liverday.shortly.domain.Either.Left(value)
    }
}