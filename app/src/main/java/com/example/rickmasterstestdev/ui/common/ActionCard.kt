package com.example.rickmasterstestdev.ui.common

import androidx.compose.animation.core.tween
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.gestures.AnchoredDraggableState
import androidx.compose.foundation.gestures.DraggableAnchors
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.anchoredDraggable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import kotlin.math.roundToInt

enum class DragValue { Visible, Invisible }

@OptIn(ExperimentalFoundationApi::class)
fun getAnchors(density: Density, maxOffset: Float) = with(density) {
    DraggableAnchors {
        DragValue.Invisible at 0.dp.toPx()
        DragValue.Visible at -maxOffset
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun ActionCard(
    content: @Composable () -> Unit,
    actionsContent: @Composable () -> Unit,
) {
    val density = LocalDensity.current
    var maxOffset = remember { 0f }
    val threshold = with(LocalDensity.current) {
        5.dp.toPx()
    }

    val state = remember { AnchoredDraggableState(
        initialValue = DragValue.Invisible,
        anchors = getAnchors(density, maxOffset),
        positionalThreshold = { distance -> distance * 0.1f },
        velocityThreshold = { threshold },
        animationSpec = tween(),
        confirmValueChange = {
            true
        },
    )
    }

    LaunchedEffect(key1 = maxOffset) {
        state.updateAnchors(getAnchors(density, maxOffset))
    }

    Box {
        Box(
            modifier = Modifier.matchParentSize(),
            contentAlignment = Alignment.CenterEnd
        ){
            Box(
                modifier = Modifier
                    .wrapContentSize()
                    .onGloballyPositioned {
                        maxOffset = it.size.width.toFloat()
                    }

            ) {
                actionsContent()
            }
        }
        Box(
            modifier = Modifier
                .offset {
                    IntOffset(
                        x = state
                            .requireOffset()
                            .roundToInt(),
                        y = 0
                    )
                }
                .anchoredDraggable(
                    orientation = Orientation.Horizontal,
                    state = state
                )
        ){
            content()
        }
    }
}