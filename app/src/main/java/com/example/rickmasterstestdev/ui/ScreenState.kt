package com.example.rickmasterstestdev.ui

sealed class ScreenState<out T>(
    val isLoading: Boolean = false,
    val data: T? = null,
    val errorMessage: String? = null
) {
    data object Idle: ScreenState<Nothing>()
    data object Loading: ScreenState<Nothing>(true)
    class Success<T>(data: T): ScreenState<T>(false, data)
    class Exit<T>(data: T? = null): ScreenState<T>(false, data)
    class Error<T>(errorMessage: String): ScreenState<T>(false, null, errorMessage)
    class Toast<T>(errorMessage: String): ScreenState<T>(false, null, errorMessage)
}