package com.example.rickmasterstestdev.ui.cameras

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.rickmasterstestdev.domain.cameras.CamerasRepository
import com.example.rickmasterstestdev.domain.cameras.RoomCamerasModel
import com.example.rickmasterstestdev.ui.ScreenState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CamerasViewModel @Inject constructor(
    private val repository: CamerasRepository
): ViewModel() {
    val state = MutableStateFlow<ScreenState<List<RoomCamerasModel>>>(ScreenState.Idle)

    fun getCameras(forceRefresh: Boolean) {
        viewModelScope.launch(Dispatchers.IO)  {
            state.emit(ScreenState.Loading)
            try {
                val cameras = repository.getCameras(forceRefresh)
                state.emit(ScreenState.Success(cameras))
            } catch (e: Exception) {
                state.emit(ScreenState.Error(e.message ?: "Unknown Error"))
            }
        }
    }
    fun toggleFavorite(cameraId: Int) {
        viewModelScope.launch {
            try {
                repository.toggleFavorite(cameraId)
                if(state.value.data != null){
                    val copy = state.value.data?.map {
                        it.cameras = it.cameras.map { camera ->
                            if (camera.id == cameraId){
                                camera.favorites = !camera.favorites
                            }
                            camera
                        }
                        it
                    }
                    state.value = ScreenState.Success(copy!!)
                }
            } catch (e: Exception) {
                state.emit(ScreenState.Toast(e.message.toString()))
            }
        }
    }
}