package com.marah.alsafadi.marah_alsafadi_final.screens.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.marah.alsafadi.marah_alsafadi_final.R
import com.marah.alsafadi.marah_alsafadi_final.components.ProductCard
import com.marah.alsafadi.marah_alsafadi_final.model.cartList
import com.marah.alsafadi.marah_alsafadi_final.model.productList

@Composable
fun HomeScreen(navController: NavHostController) {
    Scaffold { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.White)
                .padding(paddingValues)
        ) {
            HomeHeader()

            LazyVerticalGrid(
                columns = GridCells.Fixed(2),
                modifier = Modifier.fillMaxSize(),
                contentPadding = PaddingValues(16.dp),
                horizontalArrangement = Arrangement.spacedBy(12.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                item(span = { GridItemSpan(2) }) {
                    LipstickBanner(navController = navController)
                }

                item(span = { GridItemSpan(2) }) {
                    Text(
                        text = "Latest Products",
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.padding(top = 10.dp, bottom = 4.dp)
                    )
                }

                items(productList.take(4)) { currentProduct ->
                    ProductCard(
                        product = currentProduct,
                        navController = navController
                    )
                }
            }
        }
    }
}

@Composable
fun LipstickBanner(navController: NavHostController) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(160.dp)
            .clip(RoundedCornerShape(15.dp))
            .background(Color(0xFFE9B28F))
    ) {
        Image(
            painter = painterResource(id = R.drawable.home1),
            contentDescription = null,
            modifier = Modifier.align(Alignment.CenterEnd).fillMaxHeight(),
            contentScale = ContentScale.Fit
        )

        Column(modifier = Modifier.padding(20.dp).align(Alignment.CenterStart)) {
            Text(text = "lipsticks set", fontSize = 18.sp, fontWeight = FontWeight.SemiBold)
            Text(text = "$10", fontSize = 26.sp, fontWeight = FontWeight.Bold, color = Color(0xFFB12C2C))
            Spacer(modifier = Modifier.height(15.dp))

            Button(
                onClick = {
                    if (productList.isNotEmpty()) {
                        cartList.add(productList[0])
                        navController.navigate("cart")
                    }
                },
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFB12C2C)),
                shape = RoundedCornerShape(10.dp),
                modifier = Modifier.height(40.dp)
            ) {
                Text("Shop Now", color = Color.White, fontSize = 14.sp)
            }
        }
    }
}

@Composable
fun HomeHeader() {
    Row(
        modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp, vertical = 15.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(text = "Good morning", fontSize = 24.sp, fontWeight = FontWeight.Bold)
        Row {
            Icon(Icons.Default.Search, null, modifier = Modifier.size(26.dp).padding(end = 12.dp))
            Icon(Icons.Default.Notifications, null, modifier = Modifier.size(26.dp), tint = Color(0xFFB12C2C))
        }
    }
}