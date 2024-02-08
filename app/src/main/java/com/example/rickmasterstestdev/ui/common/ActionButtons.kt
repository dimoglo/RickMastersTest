package com.example.rickmasterstestdev.ui.common

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import com.example.rickmasterstestdev.R

@Composable
fun ActionButtons(
    onFavoriteClick: () -> Unit,
    onEditClick: (() -> Unit)? = null,
){
    Row(
        horizontalArrangement = Arrangement.End,
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .wrapContentSize()
            .padding(8.dp)
    ) {
        if (onEditClick != null) {
            IconButton(
                modifier = Modifier
                    .border(1.dp, Color.LightGray, RoundedCornerShape(30.dp)),
                onClick = onEditClick
            ) {
                Icon(
                    imageVector = Icons.Default.Edit,
                    contentDescription = "Edit",
                    modifier = Modifier.size(20.dp),
                    tint = colorResource(id = R.color.blue),
                )
            }
            Spacer(modifier = Modifier.size(8.dp))
        }
        IconButton(
            modifier = Modifier
                .border(1.dp, Color.LightGray, RoundedCornerShape(30.dp)),
            onClick = onFavoriteClick
        ) {
            Icon(
                imageVector = Icons.Default.Star,
                contentDescription = "Star",
                modifier = Modifier
                    .size(20.dp),
                tint = Color.Yellow
            )
        }
    }
}