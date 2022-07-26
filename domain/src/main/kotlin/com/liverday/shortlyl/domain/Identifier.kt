package com.liverday.shortlyl.domain

abstract class Identifier : ValueObject() {
    abstract fun getValue(): String;
}