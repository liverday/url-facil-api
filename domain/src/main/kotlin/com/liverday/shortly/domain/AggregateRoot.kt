package com.liverday.shortly.domain

abstract class AggregateRoot<ID : com.liverday.shortly.domain.Identifier>(id: ID) : com.liverday.shortly.domain.Entity<ID>(id)