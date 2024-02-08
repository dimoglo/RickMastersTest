package com.example.rickmasterstestdev.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.rickmasterstestdev.domain.TabModel
import com.example.rickmasterstestdev.domain.TabType
import com.example.rickmasterstestdev.ui.cameras.CameraScreen
import com.example.rickmasterstestdev.ui.common.Tabs
import com.example.rickmasterstestdev.ui.doors.DoorScreen
import com.example.rickmasterstestdev.ui.doors.widgets.components.EditScreenWidget

@Composable
fun HomeScreen() {
    var doorId by remember { mutableStateOf(-1) }

    var selectedTab by remember { mutableStateOf(TabType.CAMERAS) }
    val onTabSelected: (TabModel) -> Unit = { tabModel ->
        selectedTab = tabModel.type
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        Text(
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .padding(8.dp),
            text = "Мой дом",
            style = MaterialTheme.typography.headlineSmall
        )
        Row(
            modifier = Modifier
                .fillMaxWidth()
        ){
            Tabs(
                tabs = listOf(
                    TabModel(TabType.CAMERAS),
                    TabModel(TabType.DOORS)
                ),
                onTabSelected = onTabSelected
            )
        }

        when (selectedTab) {
            TabType.CAMERAS -> {
                CameraScreen()
            }
            TabType.DOORS -> {
                DoorScreen(
                    onNavigateToEditScreen = {
                        doorId = it
                    }
                )
            }
        }

        if(doorId != -1){
            EditScreenWidget(
                doorId = doorId,
                navigateBack = {
                    doorId = -1
                }
            )
        }
    }
}