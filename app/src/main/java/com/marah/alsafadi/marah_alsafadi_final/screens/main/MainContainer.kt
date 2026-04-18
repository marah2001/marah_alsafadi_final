package com.marah.alsafadi.marah_alsafadi_final.screens.main

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.marah.alsafadi.marah_alsafadi_final.components.MyBottomNavigation
import com.marah.alsafadi.marah_alsafadi_final.screens.home.HomeScreen
import com.marah.alsafadi.marah_alsafadi_final.screens.categories.CategoriesScreen
import com.marah.alsafadi.marah_alsafadi_final.screens.cart.CartScreen

@Composable
fun MainContainer(navController: NavHostController) {
    val bottomNavController = rememberNavController()

    Scaffold(
        bottomBar = {
            MyBottomNavigation(navController = bottomNavController)
        }
    ) { innerPadding ->
        NavHost(
            navController = bottomNavController,
            startDestination = "home",
            modifier = Modifier.padding(innerPadding)
        )
        {
            composable("home") {
                HomeScreen(navController = bottomNavController)
            }
            composable("categories") {
                CategoriesScreen(navController = bottomNavController)
            }
            composable("cart") {
                CartScreen(navController = bottomNavController)
            }
        }
    }
}