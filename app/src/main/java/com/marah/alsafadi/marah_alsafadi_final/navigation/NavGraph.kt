package com.marah.alsafadi.marah_alsafadi_final.navigation


import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.marah.alsafadi.marah_alsafadi_final.screens.ProductDetails.ProductDetailsScreen
import com.marah.alsafadi.marah_alsafadi_final.screens.cart.CartScreen
import com.marah.alsafadi.marah_alsafadi_final.screens.login.LoginScreen
import com.marah.alsafadi.marah_alsafadi_final.screens.main.MainContainer
import com.marah.alsafadi.marah_alsafadi_final.screens.splash.SplashScreen

@Composable
fun RootNavGraph(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = "splash", // يبدأ من السكرين تاعت السبلش
        route = "root_graph"
    ) {
        // 1. شاشة السبلش
        composable(route = "splash") {
            SplashScreen(navController = navController)
        }

        // 2. شاشة اللوجن
        composable(route = "login") {
            LoginScreen(navController = navController)
        }

        // 3. الحاوية الرئيسية (اللي فيها الـ Bottom Bar)
        composable(route = "main_container") {
            MainContainer(navController = navController)
        }
        // خلي صفحة التفاصيل هون عشان تفتح بملء الشاشة
        composable("product_details") {
            ProductDetailsScreen(navController = navController)
        }
        composable(route = "cart") { // تأكدي إن الكلمة هون cart بالظبط
            CartScreen(navController)
        }
    }
}