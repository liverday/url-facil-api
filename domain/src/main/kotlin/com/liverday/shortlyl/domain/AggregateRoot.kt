package com.liverday.shortlyl.domain

abstract class AggregateRoot<ID : Identifier>(id: ID) : Entity<ID>(id)