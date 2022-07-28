package com.liverday.shortly.domain

abstract class Identifier : com.liverday.shortly.domain.ValueObject() {
    abstract fun getValue(): String;
}