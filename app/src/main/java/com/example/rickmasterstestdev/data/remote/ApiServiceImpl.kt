package com.example.rickmasterstestdev.data.remote

import io.ktor.client.HttpClient
import io.ktor.client.plugins.ClientRequestException
import io.ktor.client.plugins.RedirectResponseException
import io.ktor.client.plugins.ServerResponseException
import io.ktor.client.call.body
import io.ktor.client.request.get

class ApiServiceImpl(
    private val client: HttpClient
) : ApiService {

    override suspend fun getCameras(): ServerResponse<CameraListDto> {
        return try {
            client.get(HttpRoutes.CameraBaseURL).body()
        } catch (e: RedirectResponseException) {
            // 3xx - responses
            println("Error: ${e.response.status.description}")
            ServerResponse(success = false, null)
        } catch (e: ClientRequestException) {
            // 4xx - responses
            println("Error: ${e.response.status.description}")
            ServerResponse(success = false, null)
        } catch (e: ServerResponseException) {
            // 5xx - responses
            println("Error: ${e.response.status.description}")
            ServerResponse(success = false, null)
        } catch (e: Exception) {
            println("Error: ${e.message}")
            ServerResponse(success = false, null)
        }
    }

    override suspend fun getDoors(): ServerResponse<List<DoorDto>> {
        return try {
            client.get(HttpRoutes.DoorBaseURL).body()
        } catch (e: RedirectResponseException) {
            // 3xx - responses
            println("Error: ${e.response.status.description}")
            ServerResponse(success = false, null)
        } catch (e: ClientRequestException) {
            // 4xx - responses
            println("Error: ${e.response.status.description}")
            ServerResponse(success = false, null)
        } catch (e: ServerResponseException) {
            // 5xx - responses
            println("Error: ${e.response.status.description}")
            ServerResponse(success = false, null)
        } catch (e: Exception) {
            println("Error: ${e.message}")
            ServerResponse(success = false, null)
        }
    }
}
