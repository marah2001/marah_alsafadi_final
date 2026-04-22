package com.marah.alsafadi.marah_alsafadi_final.screens.cart

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.marah.alsafadi.marah_alsafadi_final.R
import com.marah.alsafadi.marah_alsafadi_final.model.Product
import com.marah.alsafadi.marah_alsafadi_final.model.cartList
import com.marah.alsafadi.marah_alsafadi_final.model.favoriteList

@Composable
fun CartScreen(navController: NavHostController) {
    Scaffold(
        topBar = {
            Text(
                // تحديث العنوان بعدد العناصر الحقيقي
                text = if (cartList.isEmpty()) "Cart" else "Cart (${cartList.size})",
                modifier = Modifier.fillMaxWidth().padding(16.dp),
                textAlign = TextAlign.Center,
                fontWeight = FontWeight.Bold,
                fontSize = 18.sp
            )
        },
        bottomBar = {
            if (cartList.isNotEmpty()) {
                CartBottomSection()
            }
        }
    ) { paddingValues ->
        if (cartList.isEmpty()) {
            // شاشة السلة الفارغة
            Box(modifier = Modifier.fillMaxSize().padding(paddingValues), contentAlignment = Alignment.Center) {
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Icon(painterResource(R.drawable.ic_launcher_foreground), null, Modifier.size(100.dp), Color.LightGray)
                    Spacer(Modifier.height(16.dp))
                    Text("Your cart is still empty..\nGo find something you love!", color = Color.Gray, textAlign = TextAlign.Center)
                }
            }
        } else {
            LazyVerticalGrid(
                columns = GridCells.Fixed(2),
                modifier = Modifier.padding(paddingValues).padding(8.dp),
                contentPadding = PaddingValues(8.dp),
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                items(cartList) { product ->
                    CartItem(product)
                }
            }
        }
    }
}

@Composable
fun CartItem(product: Product) {
    val isFavorite = favoriteList.contains(product)

    Card(
        colors = CardDefaults.cardColors(containerColor = Color(0xFFF9F9F9)),
        shape = RoundedCornerShape(12.dp),
        modifier = Modifier.fillMaxWidth()
    ) {
        Column(modifier = Modifier.padding(8.dp)) {
            Box(modifier = Modifier.fillMaxWidth()) {
                Image(
                    painter = painterResource(id = product.image),
                    contentDescription = null,
                    modifier = Modifier.fillMaxWidth().height(100.dp).clip(RoundedCornerShape(8.dp)),
                    contentScale = ContentScale.Crop
                )
                // زر X للحذف من السلة
                IconButton(
                    onClick = { cartList.remove(product) },
                    modifier = Modifier.align(Alignment.TopEnd).size(24.dp).background(Color.White.copy(alpha = 0.8f), CircleShape)
                ) {
                    Icon(Icons.Default.Close, null, tint = Color.Red, modifier = Modifier.size(14.dp))
                }
            }

            Text(product.name, fontSize = 12.sp, maxLines = 1, fontWeight = FontWeight.SemiBold, modifier = Modifier.padding(top = 8.dp))

            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxWidth().padding(top = 4.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(product.price, color = Color.Red, fontWeight = FontWeight.Bold, fontSize = 13.sp)

                // تحكم الكمية (+ و -)
                Row(verticalAlignment = Alignment.CenterVertically) {
                    // زر الناقص
                    IconButton(
                        onClick = { if (product.quantity > 1) {
                            val index = cartList.indexOf(product)
                            cartList[index] = product.copy(quantity = product.quantity - 1)
                        }},
                        modifier = Modifier.size(20.dp).background(Color.LightGray, CircleShape)
                    ) {
                        Icon(Icons.Default.Delete, null, tint = Color.White, modifier = Modifier.size(12.dp))
                    }

                    Text(" ${String.format("%02d", product.quantity)} ", fontSize = 12.sp)

                    // زر الزائد
                    IconButton(
                        onClick = {
                            val index = cartList.indexOf(product)
                            cartList[index] = product.copy(quantity = product.quantity + 1)
                        },
                        modifier = Modifier.size(20.dp).background(Color(0xFFB12C2C), CircleShape)
                    ) {
                        Icon(Icons.Default.Add, null, tint = Color.White, modifier = Modifier.size(12.dp))
                    }
                }
            }

            // إضافة زر القلب للحذف من المفضلة إذا أردتِ
            IconButton(
                onClick = { if (isFavorite) favoriteList.remove(product) else favoriteList.add(product) },
                modifier = Modifier.align(Alignment.End).size(24.dp)
            ) {
                Icon(
                    imageVector = if (isFavorite) Icons.Default.Favorite else Icons.Default.FavoriteBorder,
                    contentDescription = null,
                    tint = if (isFavorite) Color.Red else Color.Gray,
                    modifier = Modifier.size(18.dp)
                )
            }
        }
    }
}

@Composable
fun CartBottomSection() {
    // حساب المجموع الحقيقي بناءً على السعر والكمية
    val subTotal = cartList.sumOf {
        it.price.replace("$", "").trim().toDoubleOrNull() ?: 0.0 * it.quantity
    }

    Column(modifier = Modifier.fillMaxWidth().background(Color.White).padding(16.dp)) {
        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
            Text("SubTotal", color = Color.Gray)
            Text("$${String.format("%.2f", subTotal)}", fontWeight = FontWeight.Bold)
        }
        Spacer(Modifier.height(16.dp))
        Button(
            onClick = { /* تفاصيل الدفع */ },
            modifier = Modifier.fillMaxWidth().height(50.dp),
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFB12C2C)),
            shape = RoundedCornerShape(8.dp)
        ) {
            Text("Checkout Now", color = Color.White, fontSize = 16.sp)
        }
    }
}