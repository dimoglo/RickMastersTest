package com.example.rickmasterstestdev.ui.doors

import androidx.compose.foundation.layout.Arrangement
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
import com.example.rickmasterstestdev.ui.common.ActionButtons
import com.example.rickmasterstestdev.ui.common.ActionCard
import com.example.rickmasterstestdev.ui.doors.widgets.DoorWidget

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DoorScreen(
    doorsViewModel: DoorsViewModel = hiltViewModel(),
    onNavigateToEditScreen: (Int) -> Unit,
) {
    val state by doorsViewModel.state.collectAsState()

    LaunchedEffect(true) {
        doorsViewModel.getDoors(forceRefresh = false)
    }

    val refreshState = rememberPullToRefreshState()
    if (refreshState.isRefreshing) {
        LaunchedEffect(true) {
            doorsViewModel.getDoors(forceRefresh = true)
        }
    }

    LaunchedEffect(state.data) {
        if (state.data != null)
            refreshState.endRefresh()
    }

    Box(
        Modifier.nestedScroll(refreshState.nestedScrollConnection)
    ) {
        Column(
            modifier = Modifier
                .padding(8.dp)
                .fillMaxSize()
                .verticalScroll(state = rememberScrollState()),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            if (state.data != null) {
                state.data!!.map { door ->
                    ActionCard(
                        content = {
                            DoorWidget(
                                door = door
                            )
                        },
                        actionsContent = {
                            ActionButtons(
                                onFavoriteClick = {
                                    doorsViewModel.toggleFavorite(door.id)
                                },
                                onEditClick = {
                                    onNavigateToEditScreen(door.id)
                                }
                            )
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