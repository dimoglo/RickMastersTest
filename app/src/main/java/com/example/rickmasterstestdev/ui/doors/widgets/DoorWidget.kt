package com.example.rickmasterstestdev.ui.doors.widgets

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.example.rickmasterstestdev.domain.doors.DoorModel
import com.example.rickmasterstestdev.ui.doors.widgets.components.LockIcon

@Composable
fun DoorWidget(
    door: DoorModel
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .shadow(2.dp, RoundedCornerShape(12.dp), true),
        colors = CardDefaults.cardColors(containerColor = Color.White)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
        ) {
            Box {
                if (door.snapshot != null) {
                    Image(
                        painter = rememberAsyncImagePainter(door.snapshot),
                        contentDescription = "Snapshot",
                        modifier = Modifier
                            .height(200.dp)
                            .fillMaxWidth()
                            .background(Color.Gray)
                    )
                    Icon(
                        imageVector = Icons.Default.PlayArrow,
                        contentDescription = "Play",
                        modifier = Modifier
                            .align(Alignment.Center)
                            .size(60.dp)
                            .border(
                                2.dp,
                                color = Color.White,
                                shape = RoundedCornerShape(50.dp)
                            ),
                        tint = Color.White
                    )
                }
            }
            Surface(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 24.dp)

            ) {
                Row(
                    modifier = Modifier
                        .background(Color.White),
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {

                    if (door.favorites)
                        Icon(
                            imageVector = Icons.Default.Star,
                            contentDescription = "Favorite",
                            tint = Color.Yellow,
                        )

                    Text(
                        modifier = Modifier.weight(1f),
                        text = door.name ?: "Unknown Camera",
                        style = MaterialTheme.typography.bodyLarge
                            .copy(color = Color.Black)
                    )

                    LockIcon(
                        isLocked = true,
                        modifier = Modifier
                    )
                }
            }
        }
    }
}


