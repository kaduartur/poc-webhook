package br.com.orange.controller

import br.com.orange.application.CreateWebhook
import br.com.orange.application.WebhookService
import io.micronaut.http.HttpStatus
import io.micronaut.http.MediaType
import io.micronaut.http.annotation.Body
import io.micronaut.http.annotation.Consumes
import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Post
import io.micronaut.http.annotation.Status
import javax.validation.constraints.NotEmpty
import javax.validation.constraints.NotNull

@Controller("/v1/webhooks")
class WebhookController(private val webhookService: WebhookService) {


    @Post
    @Consumes(MediaType.APPLICATION_JSON)
    @Status(HttpStatus.CREATED)
    fun createWebhook(@Body createWebhookRequest: CreateWebhookRequest) {
        webhookService.create(
            CreateWebhook(
                createWebhookRequest.repoURL,
                createWebhookRequest.repoName,
                createWebhookRequest.owner,
                createWebhookRequest.hookURL,
                createWebhookRequest.events,
                createWebhookRequest.token
            )
        )
    }
}

data class CreateWebhookRequest(
    @field:NotEmpty val repoURL: String,
    @field:NotEmpty val repoName: String,
    @field:NotEmpty val owner: String,
    @field:NotEmpty val hookURL: String,
    @field:NotNull val events: Set<String>,
    @field:NotEmpty val token: String
)