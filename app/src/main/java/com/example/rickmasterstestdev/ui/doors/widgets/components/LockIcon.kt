package com.example.rickmasterstestdev.ui.doors.widgets.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.rickmasterstestdev.R

@Composable
fun LockIcon(
    isLocked: Boolean,
    modifier: Modifier
) {
    val painterResourceId = if (isLocked) R.drawable.lock else R.drawable.lock_open
    Image(
        modifier = modifier
            .padding()
            .size(24.dp),
        painter = painterResource(id = painterResourceId),
        contentDescription = "Lock/Unlock"
    )
}