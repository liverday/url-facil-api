package com.liverday.url.facil.domain

abstract class Identifier : ValueObject() {
    abstract fun getValue(): String;
}