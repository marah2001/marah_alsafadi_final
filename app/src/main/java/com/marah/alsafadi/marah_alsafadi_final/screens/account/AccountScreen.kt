package com.marah.alsafadi.marah_alsafadi_final.screens.account
import androidx.compose.foundation.background
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
@Composable
fun AccountScreen(navController: NavHostController) {
    // 1. تعريف المتغيرات اللي رح تتغير (State)
    var name by remember { mutableStateOf("Marah Fadi Al-Harthy") }
    var phone by remember { mutableStateOf("0599001122") }
    var email by remember { mutableStateOf("Marah.fadi@gmail.com") }

    // حالة بتحدد إذا إحنا حالياً بنعدل أو بس بنعرض
    var isEditing by remember { mutableStateOf(false) }

    Column(modifier = Modifier.fillMaxSize().padding(20.dp)) {
        Text(
            text = "Account",
            modifier = Modifier.fillMaxWidth(),
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.titleLarge
        )

        Spacer(modifier = Modifier.height(20.dp))

        if (!isEditing) {
            // واجهة العرض العادية (زي ما هي بالصورة)
            Row(verticalAlignment = Alignment.CenterVertically) {
                // ... كود الصورة والمعلومات ...
                Column(modifier = Modifier.weight(1f)) {
                    Text(text = name, fontWeight = FontWeight.Bold)
                    Text(text = phone, color = Color.Gray)
                    Text(text = email, color = Color.Gray)
                }
                IconButton(onClick = { isEditing = true }) { // عند الضغط على القلم
                    Icon(imageVector = Icons.Default.Edit, contentDescription = null, tint = Color.Blue)
                }
            }
        } else {
            // "الفريم" أو واجهة التعديل
            Column(modifier = Modifier.fillMaxWidth().background(Color.White).padding(16.dp)) {
                OutlinedTextField(value = name, onValueChange = { name = it }, label = { Text("Name") })
                OutlinedTextField(value = phone, onValueChange = { phone = it }, label = { Text("Phone") })
                OutlinedTextField(value = email, onValueChange = { email = it }, label = { Text("Email") })

                Button(
                    onClick = { isEditing = false }, // هون السحر: بيسكر التعديل وبيرجع يعرض البيانات الجديدة
                    modifier = Modifier.fillMaxWidth().padding(top = 10.dp)
                ) {
                    Text("Save Changes")
                }
            }
        }
    }
}