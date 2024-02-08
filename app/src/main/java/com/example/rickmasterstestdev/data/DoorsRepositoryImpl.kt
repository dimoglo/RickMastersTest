package com.example.rickmasterstestdev.data

import com.example.rickmasterstestdev.data.local.DoorEntity
import com.example.rickmasterstestdev.data.local.fromDto
import com.example.rickmasterstestdev.data.local.toDoorsModel
import com.example.rickmasterstestdev.data.remote.ApiService
import com.example.rickmasterstestdev.domain.doors.DoorModel
import com.example.rickmasterstestdev.domain.doors.DoorsRepository
import io.realm.kotlin.Realm
import io.realm.kotlin.ext.query
import timber.log.Timber
import javax.inject.Inject

class   DoorsRepositoryImpl @Inject constructor(
    private val apiService: ApiService,
    private val realm: Realm
) : DoorsRepository {

    override suspend fun getDoors(forceRefresh: Boolean): List<DoorModel> {
        if (forceRefresh || shouldGetFromNetwork())
            return getDoorsFromNetwork()
        return getDoorsFromLocal()
    }

    override suspend fun renameDoor(doorId: Int, newName: String) {
        realm.writeBlocking {
            val door = query<DoorEntity>("id == $0", doorId).find().first()
            door.name = newName
        }
    }

    override suspend fun getDoor(doorId: Int): DoorModel {
        Timber.d("test $doorId")
        return realm.query<DoorEntity>("id == $0", doorId).find().first().toDoorsModel()
    }

    override suspend fun toggleFavorite(doorId: Int) {
        realm.writeBlocking {
            val door = query<DoorEntity>("id == $0", doorId).find().first()
            door.favorites = !door.favorites
        }
    }

    private fun shouldGetFromNetwork(): Boolean {
        val doorsCount = realm.query<DoorEntity>().count().find()
        Timber.d("$doorsCount")
        return doorsCount == 0L
    }

    private suspend fun getDoorsFromNetwork(): List<DoorModel> {
        val serverResponse = apiService.getDoors()
        if (serverResponse.success) {
            val entities = realm.write {
                val result = mutableListOf<DoorEntity>()
                delete(DoorEntity::class)
                serverResponse.data?.forEach { dto ->
                    val entity = copyToRealm(DoorEntity().fromDto(dto))
                    result.add(entity)
                }
                result.map { it.toDoorsModel() }
            }
            return entities
        } else {
            return emptyList()
        }
    }

    private fun getDoorsFromLocal(): List<DoorModel> {
        return realm.query<DoorEntity>().find().map { it.toDoorsModel() }
    }
}
