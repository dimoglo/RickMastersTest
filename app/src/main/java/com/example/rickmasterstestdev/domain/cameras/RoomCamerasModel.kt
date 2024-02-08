package com.example.rickmasterstestdev.domain.cameras

data class RoomCamerasModel(
    var name: String,
    var cameras: List<CameraModel>
)

data class CameraModel (
    var id: Int? = 0,
    var name: String? = null,
    var snapshot: String? = null,
    var room: String? = null,
    var favorites: Boolean = false,
    var rec: Boolean = false,
)