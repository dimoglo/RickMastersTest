package com.example.rickmasterstestdev.data

import com.example.rickmasterstestdev.data.local.CameraEntity
import com.example.rickmasterstestdev.data.local.fromDto
import com.example.rickmasterstestdev.data.local.toCameraModel
import com.example.rickmasterstestdev.data.remote.ApiService
import com.example.rickmasterstestdev.domain.cameras.CameraModel
import com.example.rickmasterstestdev.domain.cameras.CamerasRepository
import com.example.rickmasterstestdev.domain.cameras.RoomCamerasModel
import io.realm.kotlin.Realm
import io.realm.kotlin.ext.query
import javax.inject.Inject

class CamerasRepositoryImpl @Inject constructor(
    private val apiService: ApiService,
    private val realm: Realm
) : CamerasRepository {

    override suspend fun getCameras(forceRefresh: Boolean): List<RoomCamerasModel> {
        if (forceRefresh)
            return getCamerasFromNetwork()
        if (shouldGetFromNetwork()){
            return getCamerasFromNetwork()
        }
        return getCamerasFromLocal()
    }

    override suspend fun toggleFavorite(cameraId: Int) {
        realm.writeBlocking {
            val camera = query<CameraEntity>("id == $0", cameraId).find().first()
            camera.favorites = !camera.favorites
        }
    }

    private fun shouldGetFromNetwork(): Boolean {
        val camerasCount = realm.query<CameraEntity>().count().find()
        return camerasCount == 0L
    }

    private suspend fun getCamerasFromNetwork(): List<RoomCamerasModel> {
        val serverResponse = apiService.getCameras()
        if (serverResponse.success) {
            val entities = realm.write {
                val result = mutableListOf<CameraEntity>()
                delete(CameraEntity::class)
                serverResponse.data?.cameras?.forEach { dto ->
                    val entity = copyToRealm(CameraEntity().fromDto(dto))
                    result.add(entity)
                }
                result.map { it.toCameraModel() }
            }
            return parseCameras(entities)
        } else {
            return emptyList()
        }
    }

    private fun getCamerasFromLocal(): List<RoomCamerasModel> {
        val entities = realm.query<CameraEntity>().find().map { it.toCameraModel() }
        return parseCameras(entities)
    }

    private fun parseCameras(cameraList: List<CameraModel>): List<RoomCamerasModel> {
        val result = mutableMapOf<String, MutableList<CameraModel>>()
        for (camera in cameraList){
            if(result[camera.room] != null){
                result[camera.room]?.add(camera)
            } else {
                val name = if (camera.room == null)
                    "Комната без названия"
                else
                    camera.room!!
                result[name] = mutableListOf(camera)
            }
        }
        return result.map { RoomCamerasModel(it.key, it.value) }
    }
}
