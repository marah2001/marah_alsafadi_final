package com.marah.alsafadi.marah_alsafadi_final.model

import androidx.annotation.DrawableRes

data class Product(
    val name: String,
    val brand: String,
    val price: String,
    val oldPrice: String,
    val discount: String,
    @DrawableRes val image: Int
)