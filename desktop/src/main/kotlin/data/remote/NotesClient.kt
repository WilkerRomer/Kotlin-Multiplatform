package data.remote

import io.ktor.client.*
import io.ktor.client.engine.okhttp.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.serialization.kotlinx.json.*

val notesClient = HttpClient(OkHttp) {
    install(ContentNegotiation){
        json()
    }
}