package com.marah.alsafadi.marah_alsafadi_final.screens.splash

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.marah.alsafadi.marah_alsafadi_final.R
import kotlinx.coroutines.delay

@Composable
fun SplashScreen(navController: NavController) {

    LaunchedEffect(key1 = true) {
        delay(2000L)
        navController.navigate("login_screen") {
            popUpTo("splash_screen") { inclusive = true }
        }
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF2EADC)),
        contentAlignment = Alignment.Center
    ) {
        Image(
            painter = painterResource(id = R.drawable.logo),
            contentDescription = "Logo",
            modifier = Modifier.size(220.dp)
        )
    }
}