package com.liverday.shortly.application.ports.providers.mail

import reactor.core.publisher.Mono

interface MailProvider {
    fun send(templateName: String, variables: Map<String, String>): Mono<Boolean>
}