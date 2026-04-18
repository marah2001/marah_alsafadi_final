package com.marah.alsafadi.marah_alsafadi_final.screens.login

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.marah.alsafadi.marah_alsafadi_final.R

@Composable
fun LoginScreen(navController: NavHostController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFCDCDBE))
            .verticalScroll(rememberScrollState())
    ) {
        Image(
            painter = painterResource(id = R.drawable.logo),
            contentDescription = null,
            modifier = Modifier
                .fillMaxWidth()
                .height(320.dp),
            contentScale = ContentScale.Crop
        )

        Spacer(modifier = Modifier.height(24.dp))

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 30.dp)
        ) {
            Text(
                text = "Your Phone number",
                color = Color.Black,
                fontWeight = FontWeight.Bold,
                fontSize = 16.sp
            )

            Spacer(modifier = Modifier.height(8.dp))

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color.White, RoundedCornerShape(8.dp))
                    .padding(horizontal = 16.dp, vertical = 15.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(text = "+972", fontWeight = FontWeight.Bold, color = Color.Black)
                Spacer(modifier = Modifier.width(12.dp))
                VerticalDivider(modifier = Modifier.height(20.dp), thickness = 1.dp, color = Color.LightGray)
                Spacer(modifier = Modifier.width(12.dp))
                Text(text = "Enter Your Phone Number", color = Color.Gray, fontSize = 14.sp)
            }

            TextButton(
                onClick = { /* Navigate to Email Login */ },
                modifier = Modifier.align(Alignment.CenterHorizontally)
            ) {
                Text(
                    text = "Sign in with Email",
                    color = Color(0xFFB12C2C),
                    textDecoration = TextDecoration.Underline
                )
            }

            Spacer(modifier = Modifier.height(10.dp))

            Button(
                onClick = {
                    navController.navigate("main_container") {
                        popUpTo("login_screen") { inclusive = true }
                    }
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(55.dp),
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFB12C2C)),
                shape = RoundedCornerShape(8.dp)
            ) {
                Text(text = "login", color = Color.White, fontSize = 18.sp)
            }

            Text(
                text = buildAnnotatedString {
                    append("By clicking login you agree to our ")
                    withStyle(style = SpanStyle(color = Color(0xFF3498DB))) { append("terms & conditions ") }
                    append("and ")
                    withStyle(style = SpanStyle(color = Color(0xFF3498DB))) { append("privacy policy") }
                },
                modifier = Modifier.padding(vertical = 20.dp),
                textAlign = TextAlign.Center,
                fontSize = 12.sp,
                color = Color.DarkGray
            )

            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxWidth()
            ) {
                HorizontalDivider(modifier = Modifier.weight(1f), thickness = 1.dp, color = Color.LightGray)
                Text(text = " OR ", modifier = Modifier.padding(horizontal = 8.dp), color = Color.Gray)
                HorizontalDivider(modifier = Modifier.weight(1f), thickness = 1.dp, color = Color.LightGray)
            }

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 30.dp),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                SocialButton(iconRes = R.drawable.ic_google) { /* Google Action */ }
                Spacer(modifier = Modifier.width(20.dp))
                SocialButton(iconRes = R.drawable.ic_facebook) { /* FB Action */ }
                Spacer(modifier = Modifier.width(20.dp))
                SocialButton(iconRes = R.drawable.ic_twitter) { /* Twitter Action */ }
            }
        }
    }
}

// دالة الزر الاجتماعي عشان الكود يشتغل كامل
@Composable
fun SocialButton(iconRes: Int, onClick: () -> Unit) {
    IconButton(onClick = onClick) {
        Icon(
            painter = painterResource(id = iconRes),
            contentDescription = null,
            modifier = Modifier.size(40.dp),
            tint = Color.Unspecified
        )
    }
}