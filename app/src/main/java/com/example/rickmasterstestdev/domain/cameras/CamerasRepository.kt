package com.example.rickmasterstestdev.domain.cameras

interface CamerasRepository {
    suspend fun getCameras(forceRefresh: Boolean): List<RoomCamerasModel>
    suspend fun toggleFavorite(cameraId: Int)
}