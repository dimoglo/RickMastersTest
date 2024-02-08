package com.example.rickmasterstestdev.domain

data class TabModel(
    val type: TabType,
    val data: String? = null
)

enum class TabType(
    val displayName: String,
) {
    CAMERAS("Камеры"),
    DOORS("Двери")
}