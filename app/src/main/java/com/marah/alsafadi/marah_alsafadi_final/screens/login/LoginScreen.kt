package com.marah.alsafadi.marah_alsafadi_final.screens.login

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.marah.alsafadi.marah_alsafadi_final.R

@Composable
fun LoginScreen(navController: NavHostController) {
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    val context = LocalContext.current
    val allowedEmails = listOf("marah@gmail.com", "admin@marah.com")

    Box(modifier = Modifier.fillMaxSize()) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    Brush.verticalGradient(
                        colors = listOf(Color(0xFFFDFCFB), Color(0xFFE2D1C3))
                    )
                )
        )

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 30.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Surface(
                modifier = Modifier.size(100.dp),
                shape = CircleShape,
                color = Color.White,
                shadowElevation = 8.dp
            ) {
                Image(
                    painter = painterResource(id = R.drawable.logo),
                    contentDescription = "Logo",
                    modifier = Modifier.padding(15.dp)
                )
            }

            Spacer(modifier = Modifier.height(30.dp))

            Text(
                text = "Welcome Back",
                fontSize = 28.sp,
                fontWeight = FontWeight.ExtraBold,
                color = Color(0xFF4E342E)
            )

            Text(
                text = "Login to your account to continue",
                fontSize = 14.sp,
                color = Color.Gray
            )

            Spacer(modifier = Modifier.height(40.dp))

            OutlinedTextField(
                value = email,
                onValueChange = { email = it },
                label = { Text("Email Address") },
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(15.dp),
                colors = TextFieldDefaults.colors(
                    focusedContainerColor = Color.White,
                    unfocusedContainerColor = Color.White,
                    focusedIndicatorColor = Color(0xFFB12C2C)
                )
            )

            Spacer(modifier = Modifier.height(15.dp))

            OutlinedTextField(
                value = password,
                onValueChange = { password = it },
                label = { Text("Password") },
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(15.dp),
                visualTransformation = PasswordVisualTransformation(),
                colors = TextFieldDefaults.colors(
                    focusedContainerColor = Color.White,
                    unfocusedContainerColor = Color.White,
                    focusedIndicatorColor = Color(0xFFB12C2C)
                )
            )

            Spacer(modifier = Modifier.height(30.dp))

            Button(
                onClick = {
                    if (email in allowedEmails) {
                        navController.navigate("main_container") {
                            popUpTo("login") { inclusive = true }
                        }
                    } else {
                        Toast.makeText(context, "البريد غير مدرج", Toast.LENGTH_SHORT).show()
                    }
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(55.dp),
                shape = RoundedCornerShape(15.dp),
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFB12C2C))
            ) {
                Text("Login", fontSize = 18.sp, fontWeight = FontWeight.Bold, color = Color.White)
            }

            Spacer(modifier = Modifier.height(40.dp))

            Text(text = "━━  Or connect with  ━━", color = Color.Gray, fontSize = 12.sp)

            Spacer(modifier = Modifier.height(20.dp))

            Row(
                horizontalArrangement = Arrangement.spacedBy(20.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                SocialCircleButton(R.drawable.ic_google) {}
                SocialCircleButton(R.drawable.ic_facebook) { }
            }
        }
    }
}

@Composable
fun SocialCircleButton(iconRes: Int, onClick: () -> Unit) {
    IconButton(
        onClick = onClick,
        modifier = Modifier
            .size(55.dp)
            .clip(CircleShape)
            .background(Color.White)
            .padding(12.dp)
    ) {
        Image(painter = painterResource(id = iconRes), contentDescription = null)
    }
}