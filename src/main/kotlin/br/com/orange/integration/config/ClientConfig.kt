package br.com.orange.integration.config

import io.micronaut.context.annotation.Factory
import java.net.http.HttpClient
import javax.inject.Singleton

@Factory
internal class ClientConfig {
    @Singleton
    fun client(): HttpClient {
        return HttpClient.newHttpClient()
    }
}