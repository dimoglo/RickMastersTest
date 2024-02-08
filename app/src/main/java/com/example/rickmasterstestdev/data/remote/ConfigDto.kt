package com.example.rickmasterstestdev.data.remote

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
class ServerResponse<T>(
    var success: Boolean,
    var data: T?
)

@Serializable
data class CameraListDto(
    @SerialName("room")
    var rooms: List<String>,
    var cameras: List<CameraDto>
)

@Serializable
data class CameraDto (
    var id: Int = 0,
    var name: String? = null,
    var snapshot: String? = null,
    var room: String? = null,
    var favorites: Boolean = false,
    var rec: Boolean = false,
)

@Serializable
data class DoorDto(
    var id: Int = 0,
    var name: String? = null,
    var snapshot: String? = null,
    var room: String? = null,
    var favorites: Boolean = false,
)