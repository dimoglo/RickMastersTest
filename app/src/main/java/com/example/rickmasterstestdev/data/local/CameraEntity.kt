 package com.example.rickmasterstestdev.data.local

import com.example.rickmasterstestdev.data.remote.CameraDto
import com.example.rickmasterstestdev.domain.cameras.CameraModel
import io.realm.kotlin.types.RealmObject

class CameraEntity: RealmObject, java.io.Serializable  {
    var id: Int? = 0
    var name: String? = null
    var snapshot: String? = null
    var room: String? = null
    var favorites: Boolean = false
    var rec: Boolean = false
}

 // маппер здесь, потому что смена реализации только в data модуле может произойти
fun CameraEntity.fromDto(dto: CameraDto) = apply {
    id = dto.id
    name = dto.name
    snapshot = dto.snapshot
    room = dto.room
    favorites = dto.favorites
    rec = dto.rec
}

fun CameraEntity.toCameraModel(): CameraModel {
    return CameraModel(
         id = this.id,
         name = this.name,
         snapshot = this.snapshot,
         room = this.room,
         favorites = this.favorites,
         rec = this.rec,
    )
}