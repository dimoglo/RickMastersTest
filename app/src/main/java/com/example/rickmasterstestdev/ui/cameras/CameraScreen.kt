package com.example.rickmasterstestdev.ui.cameras

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.pulltorefresh.PullToRefreshContainer
import androidx.compose.material3.pulltorefresh.rememberPullToRefreshState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.rickmasterstestdev.ui.cameras.widgets.RoomWidget


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CameraScreen(
    cameraViewModel: CamerasViewModel = hiltViewModel()
){
    val state by cameraViewModel.state.collectAsState()

    val refreshState = rememberPullToRefreshState()
    if (refreshState.isRefreshing) {
        LaunchedEffect(true) {
            cameraViewModel.getCameras(forceRefresh = true)
        }
    }

    LaunchedEffect(key1 = true) {
        cameraViewModel.getCameras(forceRefresh = false)
    }
    LaunchedEffect(state.data) {
        if (state.data != null)
            refreshState.endRefresh()
    }

    Box(
        modifier = Modifier.nestedScroll(refreshState.nestedScrollConnection)
    ){
        Column(
            modifier = Modifier
                .padding(vertical = 4.dp)
                .fillMaxSize()
                .verticalScroll(state = rememberScrollState())
        ) {
            if (state.data != null) {
                state.data!!.map { room ->
                    RoomWidget(
                        room = room,
                        onFavoriteClick = {
                            cameraViewModel.toggleFavorite(it)
                        }
                    )
                }
            } else if (!refreshState.isRefreshing) {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                ) {
                    CircularProgressIndicator(
                        progress = { 1f },
                        modifier = Modifier
                            .align(Alignment.Center),
                    )
                }
            }
        }
        if (refreshState.isRefreshing) {
            PullToRefreshContainer(
                modifier = Modifier.align(Alignment.TopCenter),
                state = refreshState,
            )
        }
    }
}