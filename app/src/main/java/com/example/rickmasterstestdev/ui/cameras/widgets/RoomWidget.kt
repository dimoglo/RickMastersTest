package com.example.rickmasterstestdev.ui.cameras.widgets

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.rickmasterstestdev.domain.cameras.RoomCamerasModel
import com.example.rickmasterstestdev.ui.common.ActionButtons
import com.example.rickmasterstestdev.ui.common.ActionCard


@Composable
fun RoomWidget(
    room: RoomCamerasModel,
    onFavoriteClick: (Int) -> Unit
){
    Column(
        modifier = Modifier
            .fillMaxSize()

    ){
        Text(
            modifier = Modifier.padding(8.dp) ,
            style = MaterialTheme.typography.bodyLarge,
            text = room.name,
            fontWeight = FontWeight.Bold
        )
        Column (
            modifier = Modifier
                .padding(8.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ){
            room.cameras.map { camera ->
                ActionCard(
                    content = {
                        CameraWidget(
                            camera = camera
                        )
                    },
                    actionsContent = {
                        ActionButtons(
                            onFavoriteClick = {
                                camera.id?.let { onFavoriteClick(it) }
                            }
                        )
                    }
                )
            }
        }

    }
}