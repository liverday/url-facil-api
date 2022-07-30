package com.liverday.shortly.infrastructure.adapters.providers.mail

import com.liverday.shortly.application.ports.providers.mail.MailProvider
import com.liverday.shortly.application.ports.providers.mail.MailTemplateProvider
import reactor.core.publisher.Mono

class S3MailProvider(private val mailTemplateProvider: MailTemplateProvider) : MailProvider {

    override fun send(templateName: String, variables: Map<String, String>): Mono<Boolean> {
        return mailTemplateProvider.findTemplateByName(templateName)
                .map { template ->

                }
                .switchIfEmpty()
    }
}