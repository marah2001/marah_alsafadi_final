package com.marah.alsafadi.marah_alsafadi_final.navigation


import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.marah.alsafadi.marah_alsafadi_final.model.productList
import com.marah.alsafadi.marah_alsafadi_final.screens.ProductDetails.ProductDetailsScreen
import com.marah.alsafadi.marah_alsafadi_final.screens.cart.CartScreen
import com.marah.alsafadi.marah_alsafadi_final.screens.login.LoginScreen
import com.marah.alsafadi.marah_alsafadi_final.screens.main.MainContainer
import com.marah.alsafadi.marah_alsafadi_final.screens.splash.SplashScreen

@Composable
fun RootNavGraph(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = "splash",
        route = "root_graph"
    ) {
        composable(route = "splash") {
            SplashScreen(navController = navController)
        }

        composable(route = "login") {
            LoginScreen(navController = navController)
        }

        composable(route = "main_container") {
            MainContainer(navController = navController)
        }
        composable("product_details/{productId}") { backStackEntry ->
            val productId = backStackEntry.arguments?.getString("productId")?.toInt() ?: 0
            ProductDetailsScreen(navController, productList[productId])
        }
        composable(route = "cart") {
            CartScreen(navController)
        }
    }
}