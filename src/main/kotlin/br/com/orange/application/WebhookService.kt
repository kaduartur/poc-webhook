package br.com.orange.application

interface WebhookService {
    fun create(createWebhook: CreateWebhook)
}

data class CreateWebhook(
    val repoURL: String,
    val repoName: String,
    val owner: String,
    val hookURL: String,
    val events: Set<String>,
    val token: String
)