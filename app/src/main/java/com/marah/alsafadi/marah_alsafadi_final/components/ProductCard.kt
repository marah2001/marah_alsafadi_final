package com.marah.alsafadi.marah_alsafadi_final.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.marah.alsafadi.marah_alsafadi_final.model.Product
import com.marah.alsafadi.marah_alsafadi_final.model.favoriteList
import com.marah.alsafadi.marah_alsafadi_final.model.productList

@Composable
fun ProductCard(product: Product, navController: NavHostController) {
    // فحص إذا كان المنتج مضاف للمفضلة حالياً
    val isFavorite = favoriteList.contains(product)

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(4.dp)
            .clickable {
                // الانتقال لصفحة التفاصيل بناءً على ترتيب المنتج في القائمة
                val index = productList.indexOf(product)
                if (index != -1) {
                    navController.navigate("product_details/$index")
                }
            },
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Column(modifier = Modifier.padding(8.dp)) {
            // 1. حاوية الصورة والقلب
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(130.dp)
                    .background(Color(0xFFF9F9F9), RoundedCornerShape(8.dp))
            ) {
                // زر القلب (المفضلة)
                IconButton(
                    onClick = {
                        if (isFavorite) {
                            favoriteList.remove(product)
                        } else {
                            favoriteList.add(product)
                        }
                    },
                    modifier = Modifier
                        .align(Alignment.TopEnd)
                        .padding(4.dp)
                        .size(30.dp)
                ) {
                    Icon(
                        imageVector = if (isFavorite) Icons.Default.Favorite else Icons.Default.FavoriteBorder,
                        contentDescription = "Toggle Favorite",
                        tint = if (isFavorite) Color.Red else Color.Gray,
                        modifier = Modifier.size(20.dp)
                    )
                }

                // صورة المنتج
                Image(
                    painter = painterResource(id = product.image),
                    contentDescription = null,
                    modifier = Modifier
                        .align(Alignment.Center)
                        .size(90.dp)
                )

                // ملصق الخصم (اختياري - لو حبيتي تظهريه)
                if (product.discount.isNotEmpty()) {
                    Box(
                        modifier = Modifier
                            .align(Alignment.TopStart)
                            .background(Color(0xFFB12C2C), RoundedCornerShape(topStart = 8.dp, bottomEnd = 8.dp))
                            .padding(horizontal = 6.dp, vertical = 2.dp)
                    ) {
                        Text(product.discount, color = Color.White, fontSize = 10.sp)
                    }
                }
            }

            Spacer(modifier = Modifier.height(8.dp))

            // 2. تفاصيل النصوص
            Text(
                text = product.name,
                fontSize = 14.sp,
                fontWeight = FontWeight.Medium,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )

            Text(
                text = product.brand,
                fontSize = 11.sp,
                color = Color.Gray
            )

            Spacer(modifier = Modifier.height(4.dp))

            // 3. السعر والمبيعات
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Row {
                    Text(
                        text = product.price,
                        color = Color(0xFFB12C2C),
                        fontWeight = FontWeight.Bold,
                        fontSize = 15.sp
                    )
                    if (product.oldPrice.isNotEmpty()) {
                        Spacer(modifier = Modifier.width(4.dp))
                        Text(
                            text = product.oldPrice,
                            color = Color.LightGray,
                            fontSize = 12.sp,
                            style = LocalTextStyle.current.copy(
                                textDecoration = androidx.compose.ui.text.style.TextDecoration.LineThrough
                            )
                        )
                    }
                }
                Text(
                    text = "50 sold",
                    fontSize = 10.sp,
                    color = Color.LightGray
                )
            }
        }
    }
}