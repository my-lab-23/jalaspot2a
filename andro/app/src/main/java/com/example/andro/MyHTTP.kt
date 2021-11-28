package com.example.my_http

import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.features.*
import io.ktor.client.request.*
import io.ktor.client.statement.*
import java.net.ConnectException

object MyHTTP {

    val client = HttpClient()

    suspend fun get(url: String): String {
        try {
            val response: HttpResponse = client.get(url)
            val stringBody: String = response.receive()
            return stringBody
        } catch (e: ClientRequestException) {
            return "ClientRequestException"
        } catch (e: ServerResponseException) {
            return "ServerResponseException"
        } catch (e: ConnectException) {
            return "ConnectException"
        }
    }
}
