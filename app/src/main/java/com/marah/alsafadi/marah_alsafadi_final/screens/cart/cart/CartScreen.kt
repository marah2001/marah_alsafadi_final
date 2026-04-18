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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.marah.alsafadi.marah_alsafadi_final.R
import com.marah.alsafadi.marah_alsafadi_final.model.Product

// 1. القائمة التجريبية للمنتجات في السلة
val cartProducts = listOf(
    Product("Device Laser Hair Rem...", "", "$10.00", "", "", R.drawable.logo),
    Product("Device Laser Hair Rem...", "", "$10.00", "", "", R.drawable.logo),
    Product("Device Laser Hair Rem...", "", "$10.00", "", "", R.drawable.logo),
    Product("Device Laser Hair Rem...", "", "$10.00", "", "", R.drawable.logo)
)

@Composable
fun CartScreen() {
    Scaffold(
        topBar = {
            Text(
                text = "Cart (4)",
                modifier = Modifier.fillMaxWidth().padding(16.dp),
                textAlign = androidx.compose.ui.text.style.TextAlign.Center,
                fontWeight = FontWeight.Bold,
                fontSize = 18.sp
            )
        },
        bottomBar = {
            CartBottomSection()
        }
    ) { paddingValues ->
        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            modifier = Modifier.padding(paddingValues).padding(8.dp),
            contentPadding = PaddingValues(8.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(cartProducts) { product ->
                CartItem(product)
            }
        }
    }
}

// 2. تصميم كرت المنتج في السلة (مع الزيادة والنقصان)
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
                // زر الحذف (X)
                IconButton(
                    onClick = { },
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

                // التحكم بالكمية
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(Icons.Default.Delete, contentDescription = null, tint = Color.White, modifier = Modifier.size(18.dp).background(Color.LightGray, CircleShape))
                    Text(" 01 ", fontSize = 12.sp)
                    Icon(Icons.Default.Add, contentDescription = null, tint = Color.White, modifier = Modifier.size(18.dp).background(Color(0xFFA52A2A), CircleShape))
                }
            }

            Row(
                modifier = Modifier.fillMaxWidth().padding(top = 8.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text("Buy now", color = Color(0xFFA52A2A), fontSize = 12.sp, fontWeight = FontWeight.Bold)
                Icon(Icons.Default.Info, contentDescription = null, tint = Color.Gray, modifier = Modifier.size(16.dp))
            }
        }
    }
}

// 3. الجزء السفلي (SubTotal وزر Buy Now)
@Composable
fun CartBottomSection() {
    Column(
        modifier = Modifier.fillMaxWidth().background(Color.White).padding(16.dp)
    ) {
        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
            Text("SubTotal", color = Color.Gray)
            Text("$45.00", fontWeight = FontWeight.Bold)
        }
        Spacer(modifier = Modifier.height(16.dp))
        Button(
            onClick = { },
            modifier = Modifier.fillMaxWidth().height(50.dp),
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFA52A2A)),
            shape = RoundedCornerShape(8.dp)
        ) {
            Text("Buy now", color = Color.White, fontSize = 16.sp)
        }
    }
}