package com.liverday.url.facil.url.adapters.mongodb.converters

import com.liverday.url.facil.url.adapters.mongodb.entities.MongoUrlData
import com.liverday.url.facil.url.domain.url.entities.Url
import io.github.glytching.junit.extension.random.Random
import io.github.glytching.junit.extension.random.RandomBeansExtension
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.junit.jupiter.api.extension.Extensions
import org.mockito.junit.jupiter.MockitoExtension

@Extensions(value = [
    ExtendWith(RandomBeansExtension::class),
    ExtendWith(MockitoExtension::class)
])
class MongoUrlConverterTest {

    private lateinit var mongoUrlConverter: MongoUrlConverter

    @BeforeEach
    fun setUp() {
        mongoUrlConverter = MongoUrlConverter()
    }

    @Test
    fun givenAnEntity_shouldBeAbleToConvertToDomain(@Random mongoUrlData: MongoUrlData) {
        val convertedUrl = mongoUrlConverter.convertToDomain(mongoUrlData)

        Assertions.assertEquals(convertedUrl.id, mongoUrlData.id)
        Assertions.assertEquals(convertedUrl.token, mongoUrlData.token)
        Assertions.assertEquals(convertedUrl.clicks, mongoUrlData.clicks)
        Assertions.assertEquals(convertedUrl.link, mongoUrlData.link)
        Assertions.assertEquals(convertedUrl.createdAt, mongoUrlData.createdAt)
        Assertions.assertEquals(convertedUrl.updatedAt, mongoUrlData.updatedAt)
    }

    @Test
    fun givenADomain_shouldBeAbleToConvertToEntity(@Random url: Url) {
        val entityUrl = mongoUrlConverter.convertToEntity(url)

        Assertions.assertEquals(entityUrl.id, url.id)
        Assertions.assertEquals(entityUrl.token, url.token)
        Assertions.assertEquals(entityUrl.clicks, url.clicks)
        Assertions.assertEquals(entityUrl.link, url.link)
        Assertions.assertEquals(entityUrl.createdAt, url.createdAt)
        Assertions.assertEquals(entityUrl.updatedAt, url.updatedAt)
    }
}
