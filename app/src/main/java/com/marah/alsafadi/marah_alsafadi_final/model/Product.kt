package com.marah.alsafadi.marah_alsafadi_final.model

import androidx.compose.runtime.mutableStateListOf
import com.marah.alsafadi.marah_alsafadi_final.R

// هاد هو تعريف الـ cartList (السلة)
// لازم يكون "برا" الكلاس عشان كل الشاشات تشوفه
val cartList = mutableStateListOf<Product>()
// قائمة المفضلة العالمية
val favoriteList = mutableStateListOf<Product>()

// هاد تعريف شكل المنتج
data class Product(
    val name: String,
    val brand: String,
    val price: String,
    val oldPrice: String,
    val discount: String,
    val image: Int
)

// هادي قائمة المنتجات اللي بتظهر في الهوم (الرفوف)
val productList = listOf(
    Product("Device Laser Hair", "Qmele", "$18", "$20", "10%", R.drawable.p4),
    Product("Beauty Blender Set", "Cherry", "$10", "$15", "33%", R.drawable.p3),
    Product("Makeup Brushes", "Luxury", "$25", "$50", "50%", R.drawable.p3),
    Product("Skincare Kit", "Glow", "$40", "$45", "11%", R.drawable.p1)
)
