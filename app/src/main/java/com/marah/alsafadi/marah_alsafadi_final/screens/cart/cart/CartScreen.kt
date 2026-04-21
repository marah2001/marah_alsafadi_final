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
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Info
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
import com.marah.alsafadi.marah_alsafadi_final.model.cartList // استيراد القائمة المشتركة

@Composable
fun CartScreen(navController: NavHostController) {
    Scaffold(
        topBar = {
            Text(
                text = if (cartList.isEmpty()) "Cart" else "Cart (${cartList.size})",
                modifier = Modifier.fillMaxWidth().padding(16.dp),
                textAlign = TextAlign.Center,
                fontWeight = FontWeight.Bold,
                fontSize = 18.sp
            )
        },
        bottomBar = {
            // لا نعرض الجزء السفلي إلا إذا كان هناك منتجات
            if (cartList.isNotEmpty()) {
                CartBottomSection()
            }
        }
    ) { paddingValues ->

        // فحص حالة السلة
        if (cartList.isEmpty()) {
            // الحالة: السلة فاضية (نص أنيق في المنتصف)
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues),
                contentAlignment = Alignment.Center
            ) {
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_launcher_foreground), // تقدري تحطي أيقونة سلة هون
                        contentDescription = null,
                        modifier = Modifier.size(100.dp),
                        tint = Color.LightGray
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                    Text(
                        text = "Your cart is still empty..\nGo find something you love!",
                        fontSize = 18.sp,
                        color = Color.Gray,
                        textAlign = TextAlign.Center,
                        lineHeight = 24.sp,
                        fontWeight = FontWeight.Medium
                    )
                }
            }
        } else {
            // الحالة: السلة فيها منتجات
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
                IconButton(
                    onClick = { cartList.remove(product) }, // ميزة الحذف من السلة
                    modifier = Modifier.align(Alignment.TopEnd).size(24.dp).background(Color.White, CircleShape)
                ) {
                    Icon(Icons.Default.Close, contentDescription = null, tint = Color.Red, modifier = Modifier.size(16.dp))
                }
            }

            Text(product.name, fontSize = 12.sp, maxLines = 1, fontWeight = FontWeight.SemiBold, modifier = Modifier.padding(top = 8.dp))

            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxWidth().padding(top = 4.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(product.price, color = Color.Red, fontWeight = FontWeight.Bold, fontSize = 13.sp)

                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(Icons.Default.Delete, contentDescription = null, tint = Color.White, modifier = Modifier.size(18.dp).background(Color.LightGray, CircleShape))
                    Text(" 01 ", fontSize = 12.sp)
                    Icon(Icons.Default.Add, contentDescription = null, tint = Color.White, modifier = Modifier.size(18.dp).background(Color(0xFFA52A2A), CircleShape))
                }
            }
        }
    }
}

@Composable
fun CartBottomSection() {
    // حساب المجموع (افتراضي)
    Column(
        modifier = Modifier.fillMaxWidth().background(Color.White).padding(16.dp)
    ) {
        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
            Text("SubTotal", color = Color.Gray)
            Text("$${cartList.size * 10}.00", fontWeight = FontWeight.Bold) // مثال بسيط للحساب
        }
        Spacer(modifier = Modifier.height(16.dp))
        Button(
            onClick = { },
            modifier = Modifier.fillMaxWidth().height(50.dp),
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFA52A2A)),
            shape = RoundedCornerShape(8.dp)
        ) {
            Text("Checkout Now", color = Color.White, fontSize = 16.sp)
        }
    }
}