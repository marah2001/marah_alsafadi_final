package com.marah.alsafadi.marah_alsafadi_final.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite // استيراد القلب
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.* // استيراد Badge و BadgedBox
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.navigation.NavController
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.currentBackStackEntryAsState
// استيراد القائمة عشان نحسب العدد
import com.marah.alsafadi.marah_alsafadi_final.model.cartList

data class BottomNavItem(
    val title: String,
    val route: String,
    val icon: ImageVector
)

@Composable
fun MyBottomNavigation(navController: NavController) {
    val items = listOf(
        BottomNavItem("Home", "home", Icons.Default.Home),
        BottomNavItem("Categories", "categories", Icons.Default.Menu),
        BottomNavItem("Favorite", "favorite", Icons.Default.Favorite), // إضافة المفضلة هنا
        BottomNavItem("Cart", "cart", Icons.Default.ShoppingCart),
        BottomNavItem("Profile", "profile", Icons.Default.Person)
    )

    NavigationBar(containerColor = Color.White) {
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentDestination = navBackStackEntry?.destination

        items.forEach { item ->
            NavigationBarItem(
                icon = {
                    // --- الكود الذكي هون ---
                    BadgedBox(
                        badge = {
                            // إذا كانت السلة فيها أغراض وهاد عنصر السلة، اظهر الرقم
                            if (item.route == "cart" && cartList.size > 0) {
                                Badge(
                                    containerColor = Color(0xFFB12C2C), // أحمر
                                    contentColor = Color.White
                                ) {
                                    Text(cartList.size.toString())
                                }
                            }
                        }
                    ) {
                        Icon(item.icon, contentDescription = item.title)
                    }
                },
                label = { Text(item.title) },
                selected = currentDestination?.hierarchy?.any { it.route == item.route } == true,
                onClick = {
                    navController.navigate(item.route) {
                        popUpTo(navController.graph.findStartDestination().id) {
                            saveState = true
                        }
                        launchSingleTop = true
                        restoreState = true
                    }
                },
                colors = NavigationBarItemDefaults.colors(
                    selectedIconColor = Color(0xFFB12C2C),
                    selectedTextColor = Color(0xFFB12C2C),
                    unselectedIconColor = Color.Gray,
                    indicatorColor = Color(0xFFFDECEC)
                )
            )
        }
    }
}