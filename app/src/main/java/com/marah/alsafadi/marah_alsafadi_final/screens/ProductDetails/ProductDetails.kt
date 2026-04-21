package com.marah.alsafadi.marah_alsafadi_final.screens.ProductDetails
import android.os.Bundle
import android.view.Surface
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowLeft
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.marah.alsafadi.marah_alsafadi_final.R

@Composable
fun ProductDetailsScreen(navController: NavHostController, product: com.marah.alsafadi.marah_alsafadi_final.model.Product) {
    Scaffold(
        bottomBar = {
            // نمرر المنتج للسلة عند الضغط على Buy Now
            BottomBuyBar(product, navController)
        }
    ) { padding ->
        Column(
            modifier = Modifier.fillMaxSize().padding(padding).verticalScroll(rememberScrollState())
        ) {
            // 1. الجزء العلوي (سهم الرجوع + اسم المنتج الحقيقي)
            Row(modifier = Modifier.fillMaxWidth().padding(16.dp), verticalAlignment = Alignment.CenterVertically) {
                IconButton(onClick = { navController.popBackStack() }) {
                    Icon(Icons.AutoMirrored.Filled.KeyboardArrowLeft, contentDescription = null)
                }
                Text(
                    text = product.name, // الاسم صار يجي من المنتج المختار
                    modifier = Modifier.weight(1f),
                    textAlign = TextAlign.Center,
                    fontWeight = FontWeight.Bold
                )
            }

            // 2. الصورة الحقيقية
            Box(modifier = Modifier.fillMaxWidth().height(300.dp).background(Color(0xFFF9F9F9))) {
                Image(
                    painter = painterResource(id = product.image), // الصورة من القائمة
                    contentDescription = null,
                    modifier = Modifier.fillMaxSize().padding(20.dp)
                )
            }

            // 3. السعر الحقيقي والوصف
            Column(modifier = Modifier.padding(16.dp)) {
                Text(text = product.price, style = MaterialTheme.typography.headlineSmall, fontWeight = FontWeight.Bold, color = Color(0xFFB12C2C))

                Spacer(modifier = Modifier.height(10.dp))

                Text(
                    text = "Description for ${product.name}:\nThis is a high quality product from ${product.brand}. Features include long battery life and ergonomic design.",
                    style = MaterialTheme.typography.bodyMedium,
                    color = Color.Gray
                )
            }
        }
    }
}

@Composable
fun BottomBuyBar(product: com.marah.alsafadi.marah_alsafadi_final.model.Product, navController: NavHostController) {
    Surface(shadowElevation = 8.dp) {
        Row(modifier = Modifier.fillMaxWidth().padding(16.dp), verticalAlignment = Alignment.CenterVertically) {
            Icon(Icons.Default.ShoppingCart, null, tint = Color.Red)
            Spacer(modifier = Modifier.width(16.dp))
            Button(
                onClick = {
                    // هاد اللي طلبتيه: يضيف للسلة وينقلنا هناك
                    com.marah.alsafadi.marah_alsafadi_final.model.cartList.add(product)
                    navController.navigate("cart")
                },
                modifier = Modifier.fillMaxWidth().height(50.dp),
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFB12C2C))
            ) {
                Text("Buy now", color = Color.White)
            }
        }
    }
}