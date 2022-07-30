package com.liverday.shortly.domain

abstract class Identifier : ValueObject() {
    abstract fun getValue(): String;
}