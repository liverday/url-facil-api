package com.liverday.shortly.application.ports.providers.mail

import com.liverday.shortly.domain.Either
import com.liverday.shortly.domain.exceptions.NotFoundException
import reactor.core.publisher.Mono

interface MailTemplateProvider {
    fun findTemplateByName(templateName: String): Mono<Either<NotFoundException, String>>
    fun compile(content: String, variables: Map<String, String>): Mono<String>
}