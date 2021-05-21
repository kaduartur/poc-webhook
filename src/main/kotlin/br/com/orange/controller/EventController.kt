package br.com.orange.controller

import io.micronaut.http.MediaType
import io.micronaut.http.annotation.Body
import io.micronaut.http.annotation.Consumes
import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Post
import io.micronaut.http.annotation.Produces

@Controller("/v1/events")
class CatalogController {

    @Post
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.TEXT_PLAIN)
    fun events(@Body event: Event): String {
        return event.zen.toUpperCase()
    }

}

data class Event(
    val zen: String
)