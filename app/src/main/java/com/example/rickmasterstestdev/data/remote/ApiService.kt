package com.example.rickmasterstestdev.data.remote

interface ApiService {
    suspend fun getCameras(): ServerResponse<CameraListDto>
    suspend fun getDoors(): ServerResponse<List<DoorDto>>

}