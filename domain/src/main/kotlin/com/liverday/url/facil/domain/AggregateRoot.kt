package com.liverday.url.facil.domain

abstract class AggregateRoot<ID : Identifier>(id: ID) : Entity<ID>(id)