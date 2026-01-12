package com.example.moviediscoveryapp.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.moviediscoveryapp.ui.details.DetailsScreen
import com.example.moviediscoveryapp.ui.home.HomeScreen

@Composable
fun AppNavGraph(apiKey: String) {

    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = "home"
    ) {

        composable("home") {
            HomeScreen(
                apiKey = apiKey,
                navController = navController
            )
        }

        composable(
            route = "details/{id}",
            arguments = listOf(
                navArgument("id") { type = NavType.IntType }
            )
        ) { backStackEntry ->
            val id = backStackEntry.arguments?.getInt("id") ?: 0

            DetailsScreen(
                movieId = id,
                apiKey = apiKey
            )
        }
    }
}
