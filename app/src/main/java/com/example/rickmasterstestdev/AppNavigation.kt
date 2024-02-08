package com.example.rickmasterstestdev

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.rickmasterstestdev.ui.HomeScreen

@Composable
fun AppNavigation() {
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = Screens.HomeScreen
    ) {
        composable(
            Screens.HomeScreen
        ){
            HomeScreen()
        }
    }
}

object Screens {
    const val HomeScreen = "HomeScreen"
}