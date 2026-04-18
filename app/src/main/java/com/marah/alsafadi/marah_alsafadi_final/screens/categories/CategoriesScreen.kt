package com.marah.alsafadi.marah_alsafadi_final.screens.categories

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
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
import com.marah.alsafadi.marah_alsafadi_final.R
import com.marah.alsafadi.marah_alsafadi_final.model.Category

// القائمة لازم تكون بره الـ @Composable أو جواها بطريقة صح، هيك صح:
val categoryList = listOf(
    Category("Skin creams", R.drawable.logo),
    Category("Nail products", R.drawable.logo),
    Category("perfume", R.drawable.logo),
    Category("Skin care Tools", R.drawable.logo),
    Category("Makeup", R.drawable.logo),
    Category("Hair care tools", R.drawable.logo)
)

@Composable
fun CategoriesScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        Text(
            text = "Categories",
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 24.dp, bottom = 16.dp),
            textAlign = TextAlign.Center,
            fontWeight = FontWeight.Bold,
            fontSize = 22.sp,
            color = Color.Black
        )

        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            contentPadding = PaddingValues(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp),
            horizontalArrangement = Arrangement.spacedBy(16.dp),
            modifier = Modifier.fillMaxSize()
        ) {
            // التصحيح هنا: استخدام دالة items الصحيحة
            items(categoryList) { category ->
                CategoryItem(category)
            }
        }
    }
}

@Composable
fun CategoryItem(category: Category) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(160.dp)
            .clip(RoundedCornerShape(12.dp))
            .background(Color.LightGray)
    ) {
        Image(
            painter = painterResource(id = category.image),
            contentDescription = category.name,
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxSize()
        )

        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.Black.copy(alpha = 0.2f))
        )

        Text(
            text = category.name,
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(bottom = 12.dp),
            color = Color.White,
            fontWeight = FontWeight.Bold,
            fontSize = 14.sp,
            textAlign = TextAlign.Center
        )
    }
}