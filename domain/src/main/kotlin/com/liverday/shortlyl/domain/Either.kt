package com.liverday.shortlyl.domain

abstract class Either<L, R> private constructor() {

    abstract fun isLeft(): Boolean
    abstract fun isRight(): Boolean

    abstract fun getLeft(): L
    abstract fun getRight(): R

    class Left<L, R>(private val value: L) : Either<L, R>() {
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

    class Right<L, R>(private val value: R) : Either<L, R>() {
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
        fun <L, R> right(value: R): Either<L, R> = Right(value)
        fun <L, R> left(value: L): Either<L, R> = Left(value)
    }
}