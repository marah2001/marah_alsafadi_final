package com.marah.alsafadi.marah_alsafadi_final.screens.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.marah.alsafadi.marah_alsafadi_final.R
import com.marah.alsafadi.marah_alsafadi_final.model.Product

// 1. تعريف القائمة خارج الـ Composable (أفضل للأداء)
val productList = listOf(
    Product("Device Laser Hair", "Qmele", "$18", "$20", "10%", R.drawable.logo),
    Product("Beauty Blender Set", "Cherry", "$10", "$15", "33%", R.drawable.logo)
)

@Composable
fun HomeScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .verticalScroll(rememberScrollState())
    ) {
        // نداء الهيدر
        HomeHeader()

        // البانر البرتقالي (Lipsticks set)
        Box(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth()
                .height(160.dp)
                .clip(RoundedCornerShape(12.dp))
                .background(Color(0xFFF5C5A8))
        ) {
            Image(
                painter = painterResource(id = R.drawable.logo),
                contentDescription = null,
                modifier = Modifier.align(Alignment.CenterEnd).fillMaxHeight(),
                contentScale = ContentScale.Fit
            )
            Column(modifier = Modifier.padding(20.dp).align(Alignment.CenterStart)) {
                Text("lipsticks set", fontWeight = FontWeight.Bold, fontSize = 18.sp)
                Text("$10", color = Color.Red, fontWeight = FontWeight.ExtraBold, fontSize = 20.sp)
                Spacer(modifier = Modifier.height(10.dp))
                Button(
                    onClick = {},
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFA52A2A)),
                    shape = RoundedCornerShape(8.dp),
                    modifier = Modifier.height(35.dp),
                    contentPadding = PaddingValues(horizontal = 16.dp, vertical = 0.dp)
                ) {
                    Text("Shop Now", fontSize = 12.sp, color = Color.White)
                }
            }
        }

        Text(
            text = "Latest Products",
            modifier = Modifier.padding(start = 16.dp, top = 8.dp, bottom = 8.dp),
            fontWeight = FontWeight.Bold,
            fontSize = 18.sp
        )

        // نداء شبكة المنتجات
        ProductGrid()

        // مسافة صغيرة في الآخر عشان ما يختفي الكود وراء الـ Bottom Navigation
        Spacer(modifier = Modifier.height(80.dp))
    }
}

@Composable
fun HomeHeader() {
    Row(
        modifier = Modifier.fillMaxWidth().padding(16.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column {
            Text("Good morning", fontSize = 14.sp, color = Color.Gray)
            Text("Marah Alsafadi", fontSize = 18.sp, fontWeight = FontWeight.Bold)
        }
        Row {
            IconButton(onClick = { }) {
                Icon(Icons.Default.Search, contentDescription = null, tint = Color.Black)
            }
            IconButton(onClick = { }) {
                Icon(Icons.Default.Notifications, contentDescription = null, tint = Color.Black)
            }
        }
    }
}

@Composable
fun ProductGrid() {
    // بما إننا جوا Column معمول له Scroll، بنستخدم Column عادي مع Rows لعرض المنتجات
    Column(modifier = Modifier.padding(horizontal = 16.dp)) {
        productList.chunked(2).forEach { rowItems ->
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                rowItems.forEach { product ->
                    Box(modifier = Modifier.weight(1f)) {
                        ProductItem(product)
                    }
                }
                if (rowItems.size == 1) Spacer(modifier = Modifier.weight(1f))
            }
            Spacer(modifier = Modifier.height(16.dp))
        }
    }
}

@Composable
fun ProductItem(product: Product) {
    Card(
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(containerColor = Color(0xFFF8F8F8)),
        modifier = Modifier.fillMaxWidth()
    ) {
        Column(modifier = Modifier.padding(8.dp)) {
            Box {
                Image(
                    painter = painterResource(id = product.image),
                    contentDescription = null,
                    modifier = Modifier.fillMaxWidth().height(120.dp).clip(RoundedCornerShape(8.dp)),
                    contentScale = ContentScale.Crop
                )
                Icon(
                    Icons.Default.FavoriteBorder,
                    contentDescription = null,
                    modifier = Modifier.align(Alignment.TopEnd).padding(4.dp).size(20.dp),
                    tint = Color.Gray
                )
            }
            Spacer(modifier = Modifier.height(8.dp))
            Text(product.name, fontWeight = FontWeight.Bold, fontSize = 14.sp, maxLines = 1)
            Text(product.brand, fontSize = 12.sp, color = Color.Gray)
            Row(verticalAlignment = Alignment.CenterVertically) {
                Text(product.price, color = Color.Red, fontWeight = FontWeight.Bold, fontSize = 14.sp)
                Spacer(modifier = Modifier.width(6.dp))
                Text(
                    product.oldPrice,
                    fontSize = 11.sp,
                    color = Color.Gray,
                    style = androidx.compose.ui.text.TextStyle(textDecoration = TextDecoration.LineThrough)
                )
            }
        }
    }
}