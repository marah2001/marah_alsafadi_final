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
import com.marah.alsafadi.marah_alsafadi_final.screens.Favorite.FavoriteScreen
import com.marah.alsafadi.marah_alsafadi_final.screens.account.AccountScreen
import com.marah.alsafadi.marah_alsafadi_final.screens.cart.CartScreen
import com.marah.alsafadi.marah_alsafadi_final.screens.categories.CategoriesScreen
import com.marah.alsafadi.marah_alsafadi_final.screens.home.HomeScreen

@Composable
fun MainContainer(navController: NavHostController) { // 1. لازم تستقبل الـ rootNavController
    // 2. تعريف الـ Controller الداخلي الخاص بالـ Bottom Bar فقط
    val bottomNavController = rememberNavController()

    Scaffold(
        bottomBar = {
            // نمرر الـ bottomNavController للبار السفلي عشان يشتغل التنقل
            MyBottomNavigation(navController = bottomNavController)
        }
    ) { innerPadding ->
        // 3. الـ NavHost الداخلي للتبديل بين الشاشات الخمسة
        NavHost(
            navController = bottomNavController,
            startDestination = "home",
            modifier = Modifier.padding(innerPadding)
        ) {
            // ملاحظة: الشاشات اللي فيها منتجات (Home, Favorite) بنعطيها rootNavController
            // عشان لما نضغط على المنتج تفتح صفحة التفاصيل بملء الشاشة

            composable("home") {
                HomeScreen(navController = navController)
            }

            composable("categories") {
                CategoriesScreen(navController = bottomNavController)
            }

            composable("cart") {
                CartScreen(navController = bottomNavController)
            }

            composable("favorite") {
                FavoriteScreen(navController = navController)
            }

            composable("profile") {
                AccountScreen(navController = bottomNavController)
            }
        }
    }
}