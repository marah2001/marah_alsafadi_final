package com.marah.alsafadi.marah_alsafadi_final.screens.account

import android.content.Intent
import android.net.Uri
import android.widget.Toast
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.marah.alsafadi.marah_alsafadi_final.R
import com.marah.alsafadi.marah_alsafadi_final.model.cartList

@Composable
fun AccountScreen(navController: NavHostController) {
    val context = LocalContext.current
    var showPaymentDialog by remember { mutableStateOf(false) }
    var showSettingsDialog by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .verticalScroll(rememberScrollState())
    ) {
        Text(
            text = "Account",
            modifier = Modifier.fillMaxWidth().padding(top = 40.dp, bottom = 20.dp),
            textAlign = androidx.compose.ui.text.style.TextAlign.Center,
            fontSize = 22.sp,
            fontWeight = FontWeight.Bold
        )

        ProfileHeader()

        Spacer(modifier = Modifier.height(20.dp))

        Column(modifier = Modifier.padding(horizontal = 20.dp)) {

            AccountItem("My order", Icons.Default.DateRange, badgeCount = cartList.size) {
                navController.navigate("cart")
            }

            AccountItem("payment method", Icons.Default.Refresh) {
                showPaymentDialog = true
            }

            AccountItem("shipping address", Icons.Default.LocationOn) {
                val gmmIntentUri = Uri.parse("geo:31.5,34.4667?q=Gaza")
                val mapIntent = Intent(Intent.ACTION_VIEW, gmmIntentUri)
                context.startActivity(mapIntent)
            }

            Divider(modifier = Modifier.padding(vertical = 15.dp), color = Color(0xFFF1F1F1))

            AccountItem("FQA", Icons.Default.Info) {
                val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse("https://google.com"))
                context.startActivity(browserIntent)
            }

            AccountItem("invite friends", Icons.Default.Share) {
                Toast.makeText(context, "سيتم اضافة هذه الميزة قريبا", Toast.LENGTH_SHORT).show()
            }

            AccountItem("settings", Icons.Default.Settings) {
                showSettingsDialog = true
            }

            Divider(modifier = Modifier.padding(vertical = 15.dp), color = Color(0xFFF1F1F1))

            AccountItem("Logout", Icons.Default.ExitToApp, isLogout = true) {
                navController.navigate("login") {
                    popUpTo(0) // مسح الذاكرة عشان ما يرجع للخلف
                }
            }
        }
    }

    if (showPaymentDialog) {
        PaymentMethodDialog { showPaymentDialog = false }
    }
    if (showSettingsDialog) {
        SettingsDialog { showSettingsDialog = false }
    }
}

@Composable
fun ProfileHeader() {
    Row(
        modifier = Modifier.fillMaxWidth().padding(horizontal = 25.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box {
            Image(
                painter = painterResource(id = R.drawable.home1),
                contentDescription = null,
                modifier = Modifier.size(80.dp).clip(CircleShape).border(1.dp, Color.Gray, CircleShape)
            )
            Icon(
                Icons.Default.Edit, "Edit",
                modifier = Modifier.align(Alignment.BottomEnd).size(24.dp).background(Color.Red, CircleShape).padding(4.dp),
                tint = Color.White
            )
        }
        Spacer(modifier = Modifier.width(15.dp))
        Column {
            Text("Mona Fadl Al-Harthy", fontWeight = FontWeight.Bold, fontSize = 16.sp)
            Text("009665211043", color = Color.Gray, fontSize = 14.sp)
            Text("Mona Fadl@gmail.com", color = Color.Gray, fontSize = 14.sp)
        }
    }
}

@Composable
fun AccountItem(title: String, icon: ImageVector, badgeCount: Int = 0, isLogout: Boolean = false, onClick: () -> Unit) {
    Row(
        modifier = Modifier.fillMaxWidth().clickable { onClick() }.padding(vertical = 12.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(icon, null, modifier = Modifier.size(24.dp), tint = if (isLogout) Color.Gray else Color.Black)
        Spacer(modifier = Modifier.width(15.dp))
        Text(text = title, modifier = Modifier.weight(1f), fontSize = 15.sp)
        if (badgeCount > 0) {
            Text(text = badgeCount.toString(), color = Color.Red, fontWeight = FontWeight.Bold)
        }
    }
}

@Composable
fun PaymentMethodDialog(onDismiss: () -> Unit) {
    AlertDialog(
        onDismissRequest = onDismiss,
        title = { Text("Payment Methods") },
        text = {
            Column {
                Text("• Google Pay", modifier = Modifier.padding(8.dp))
                Text("• Apple Pay", modifier = Modifier.padding(8.dp))
                Text("• Credit Card", modifier = Modifier.padding(8.dp))
            }
        },
        confirmButton = { TextButton(onClick = onDismiss) { Text("Close") } }
    )
}

@Composable
fun SettingsDialog(onDismiss: () -> Unit) {
    AlertDialog(
        onDismissRequest = onDismiss,
        title = { Text("Settings") },
        text = {
            Column {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text("Change Theme")
                    Spacer(modifier = Modifier.weight(1f))
                    Switch(checked = false, onCheckedChange = {})
                }
                Text("Change Language", modifier = Modifier.padding(vertical = 10.dp))
            }
        },
        confirmButton = { TextButton(onClick = onDismiss) { Text("Save") } }
    )
}