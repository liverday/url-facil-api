package com.liverday.shortly.infrastructure.adapters.providers.mail

import com.liverday.shortly.application.ports.providers.mail.MailProvider
import com.liverday.shortly.application.ports.providers.mail.MailTemplateProvider
import reactor.core.publisher.Mono

class SESMailProvider(private val mailTemplateProvider: MailTemplateProvider) : MailProvider {

    override fun send(templateName: String, variables: Map<String, String>): Mono<Boolean> {
        return mailTemplateProvider.findTemplateByName(templateName)
                .map { templateEither ->
                    if (templateEither.isLeft()) {
                        throw templateEither.getLeft()
                    }

                    templateEither.getRight()
                }.map { template ->
                    true
                }
    }
}