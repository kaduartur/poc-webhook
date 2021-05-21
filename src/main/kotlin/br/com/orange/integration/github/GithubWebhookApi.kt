package br.com.orange.integration.github

import br.com.orange.application.CreateWebhook
import br.com.orange.integration.GitWebhook
import com.fasterxml.jackson.databind.ObjectMapper
import java.net.URI
import java.net.http.HttpClient
import java.net.http.HttpRequest
import java.net.http.HttpResponse
import javax.inject.Singleton

@Singleton
class GithubWebhookApi(private val client: HttpClient) : GitWebhook {

    override fun createWebhook(createWebhook: CreateWebhook) {
        val webhookURL = buildURL(createWebhook)
        val githubWebhookRequest = GithubWebhookRequest(
            events = createWebhook.events,
            config = GithubWebhookRequest.Config(createWebhook.hookURL),
        )

        val req = HttpRequest.newBuilder()
            .header("Authorization", "token ${createWebhook.token}")
            .uri(URI(webhookURL))
            .POST(buildBody(githubWebhookRequest))
            .build()


        client.sendAsync(req, HttpResponse.BodyHandlers.ofString())
    }

    private fun buildURL(createWebhook: CreateWebhook): String {
        return "https://api.github.com/repos/${createWebhook.owner}/${createWebhook.repoName}/hooks"
    }

    private fun buildBody(request: GithubWebhookRequest): HttpRequest.BodyPublisher {
        return HttpRequest.BodyPublishers
            .ofString(ObjectMapper().writeValueAsString(request))
    }

    data class GithubWebhookRequest(
        val events: Set<String>,
        val config: Config,
    ) {
        data class Config(val url: String)
    }

}