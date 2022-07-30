package com.liverday.shortly.domain

abstract class AggregateRoot<ID : Identifier>(id: ID) : Entity<ID>(id)