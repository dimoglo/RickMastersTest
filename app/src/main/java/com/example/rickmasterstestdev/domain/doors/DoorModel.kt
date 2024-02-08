package com.example.rickmasterstestdev.domain.doors

data class DoorModel (
    var id: Int = 0,
    var snapshot: String? = null,
    var name: String? = null,
    var room: String? = null,
    var favorites: Boolean = false,
)   