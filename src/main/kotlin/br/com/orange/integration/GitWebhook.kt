package br.com.orange.integration

import br.com.orange.application.CreateWebhook

interface GitWebhook {
    fun createWebhook(createWebhook: CreateWebhook)
}