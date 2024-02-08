package com.example.rickmasterstestdev.ui.doors

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.rickmasterstestdev.domain.doors.DoorModel
import com.example.rickmasterstestdev.domain.doors.DoorsRepository
import com.example.rickmasterstestdev.ui.ScreenState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class DoorsViewModel @Inject constructor(
    private val repository: DoorsRepository
): ViewModel(){
    val state = MutableStateFlow<ScreenState<List<DoorModel>>>(ScreenState.Idle)
    val doorName = MutableStateFlow<String>("")

    fun getDoors(forceRefresh: Boolean) {
        viewModelScope.launch(Dispatchers.IO) {
            state.emit(ScreenState.Loading)
            try {
                val doors = repository.getDoors(forceRefresh)
                Timber.d("$doors")
                state.emit(ScreenState.Success(doors))
            } catch (e: Exception) {
                state.emit(ScreenState.Error(e.message ?: "Unknown Error"))            }
        }
    }
    fun renameDoor(doorId: Int, newName: String) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                repository.renameDoor(doorId, newName)
                if(state.value.data != null){
                    val copy = state.value.data?.map {
                        if(it.id == doorId) {
                            it.name = newName
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

    fun toggleFavorite(doorId: Int) {
        viewModelScope.launch {
            try {
                repository.toggleFavorite(doorId)
                if(state.value.data != null){
                    val copy = state.value.data?.map {
                        if(it.id == doorId) {
                            it.favorites = !it.favorites
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

    fun getDoor(doorId: Int){
        viewModelScope.launch(Dispatchers.IO) {
            val door = repository.getDoor(doorId)
            doorName.emit(door.name ?: "")
        }
    }
}