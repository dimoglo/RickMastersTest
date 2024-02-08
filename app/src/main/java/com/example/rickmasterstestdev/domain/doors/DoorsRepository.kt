package com.example.rickmasterstestdev.domain.doors

interface DoorsRepository {
    suspend fun getDoors(forceRefresh: Boolean): List<DoorModel>
    suspend fun renameDoor(doorId: Int, newName: String)
    suspend fun getDoor(doorId: Int):DoorModel
    suspend fun toggleFavorite(doorId: Int)
}