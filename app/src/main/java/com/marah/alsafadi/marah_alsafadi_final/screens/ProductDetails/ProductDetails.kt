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
fun ProductDetailsScreen(navController: NavHostController) {
    Scaffold(
        bottomBar = {
            // الجزء السفلي اللي فيه السلة وزر الشراء
            BottomBuyBar()
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .verticalScroll(rememberScrollState())
        ) {
            // 1. الجزء العلوي (السهم والاسم)
            Row(
                modifier = Modifier.fillMaxWidth().padding(16.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                IconButton(onClick = { navController.popBackStack() }) {
                    Icon(Icons.Default.KeyboardArrowLeft, contentDescription = null)
                }
                Text(
                    text = "Device Laser Hair Removal",
                    modifier = Modifier.weight(1f),
                    textAlign = TextAlign.Center,
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold
                )
            }

            // 2. الصورة الكبيرة للمنتج
            Box(modifier = Modifier.fillMaxWidth().height(300.dp).background(Color(0xFFF9F9F9))) {
                Image(
                    painter = painterResource(id = R.drawable.ic_launcher_foreground), // استبدليها بصورة الجهاز
                    contentDescription = null,
                    modifier = Modifier.fillMaxSize().padding(20.dp)
                )
                // أيقونة القلب فوق الصورة
                Icon(
                    imageVector = Icons.Default.FavoriteBorder,
                    contentDescription = null,
                    modifier = Modifier.align(Alignment.TopEnd).padding(16.dp)
                )
            }

            // 3. السعر والتقييم
            Column(modifier = Modifier.padding(16.dp)) {
                Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                    Text(text = "$10.00", style = MaterialTheme.typography.headlineSmall, fontWeight = FontWeight.Bold)
                    // العداد ( + 01 - )
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        IconButton(onClick = {}) { Icon(painterResource(R.drawable.logo), null, tint = Color.Red) } // تأكدي من وجود الأيقونة
                        Text("01")
                        IconButton(onClick = {}) { Icon(Icons.Default.AddCircle, null, tint = Color.Red) }
                    }
                }

                // كرت التقييم
                Card(modifier = Modifier.fillMaxWidth().padding(vertical = 8.dp)) {
                    Row(modifier = Modifier.padding(8.dp), verticalAlignment = Alignment.CenterVertically) {
                        Icon(Icons.Default.Star, contentDescription = null, tint = Color.Yellow)
                        Text(" 4.8 | 50 Orders")
                    }
                }

                // 4. الوصف (Description)
                Text(
                    text = "1. Applicable: 100-240V working voltage...\n2. Painless: Adjustable optimal energy...\n3. Fast and big treatment area...",
                    style = MaterialTheme.typography.bodyMedium,
                    color = Color.Gray
                )
            }
        }
    }
}

@Composable
fun BottomBuyBar() {
    Surface(shadowElevation = 8.dp) {
        Row(
            modifier = Modifier.fillMaxWidth().padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                Icons.Default.ShoppingCart,
                contentDescription = null,
                tint = Color.Red,
                modifier = Modifier.size(30.dp)
            )
            Spacer(modifier = Modifier.width(16.dp))
            Button(
                onClick = { /* Buy Now */ },
                modifier = Modifier.fillMaxWidth().height(50.dp),
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFB12C2C)),
                shape = RoundedCornerShape(8.dp)
            ) {
                Text("Buy now", color = Color.White)
            }
        }
    }
}