package com.example.rickmasterstestdev.data.local

import com.example.rickmasterstestdev.data.remote.DoorDto
import com.example.rickmasterstestdev.domain.doors.DoorModel
import io.realm.kotlin.types.RealmObject

class DoorEntity: RealmObject, java.io.Serializable {
    var id: Int? = 0
    var name: String? = null
    var snapshot: String? = null
    var room: String? = null
    var favorites: Boolean = false
}

// маппер здесь, потому что смена реализации только в data модуле может произойти
fun DoorEntity.fromDto(dto: DoorDto) = apply {
    id = dto.id
    name = dto.name
    snapshot = dto.snapshot
    room = dto.room
    favorites = dto.favorites
}

fun DoorEntity.toDoorsModel(): DoorModel {
    return DoorModel(
        id = this.id ?: -1,
        name = this.name,
        snapshot = this.snapshot,
        room = this.room,
        favorites = this.favorites,
    )
}