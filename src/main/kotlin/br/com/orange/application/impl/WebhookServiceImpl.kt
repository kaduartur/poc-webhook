package br.com.orange.application.impl

import br.com.orange.application.CreateWebhook
import br.com.orange.application.WebhookService
import br.com.orange.integration.GitWebhook
import javax.inject.Singleton

@Singleton
class WebhookServiceImpl(private val git: GitWebhook) : WebhookService {

    override fun create(createWebhook: CreateWebhook) {
        git.createWebhook(createWebhook)
    }

}