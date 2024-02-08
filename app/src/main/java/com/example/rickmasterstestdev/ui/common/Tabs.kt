package com.example.rickmasterstestdev.ui.common

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.TabRowDefaults.tabIndicatorOffset
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.rickmasterstestdev.R
import com.example.rickmasterstestdev.domain.TabModel

@Composable
fun Tabs(
    tabs: List<TabModel>,
    onTabSelected: (TabModel) -> Unit
) {

    var selectedTabIndex by remember { mutableIntStateOf(0) }

    TabRow(
        selectedTabIndex = selectedTabIndex,
        modifier = Modifier.fillMaxWidth(),
        indicator = { tabPositions ->
            Box(
                modifier = Modifier
                    .tabIndicatorOffset(tabPositions[selectedTabIndex])
                    .height(2.dp)
                    .fillMaxWidth()
                    .background(colorResource(id = R.color.blue))
            )
        }
    ) {
        tabs.forEachIndexed { index, tab ->
            Tab(
                modifier = Modifier.background(Color.White),
                selected = index == selectedTabIndex,
                onClick = {
                    selectedTabIndex = index
                    onTabSelected(tab)
                },
            ) {
                Text(
                    text = tab.type.displayName,
                    modifier = Modifier.padding(16.dp),
                    style = TextStyle(color = Color.Black, fontSize = 16.sp)
                )
            }
        }
    }
}

