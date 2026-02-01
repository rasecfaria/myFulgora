package com.example.myfulgora.ui.screens.tabs

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.myfulgora.ui.components.CustomDarkInput
import com.example.myfulgora.ui.components.CustomDarkPasswordInput
import com.example.myfulgora.ui.components.FulgoraBackground
import com.example.myfulgora.ui.components.FulgoraTopBar
import com.example.myfulgora.ui.theme.Dimens
import com.example.myfulgora.ui.theme.GreenFresh

@Composable
fun ProfileScreen(
    onMenuClick: () -> Unit = {}
) {
    // Variáveis de Estado (Simulação de dados do utilizador)
    var name by remember { mutableStateOf("Alex Rider") }
    var email by remember { mutableStateOf("alex.rider@fulgora.com") }
    var phone by remember { mutableStateOf("+1 234 567 890") }
    var password by remember { mutableStateOf("password123") }

    FulgoraBackground {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 24.dp) // Margem lateral padrão
                .verticalScroll(rememberScrollState())
        ) {
            Spacer(modifier = Modifier.height(Dimens.TopPadding))

            // 1. CABEÇALHO (Top Bar)
            FulgoraTopBar(
                title = "My Profile",
                subtitle = "Edit details",
                onMenuClick = onMenuClick
            )

            Spacer(modifier = Modifier.height(32.dp))

            // 2. AVATAR DE PERFIL (Com botão de editar)
            Box(
                modifier = Modifier
                    .size(100.dp)
                    .align(Alignment.CenterHorizontally)
            ) {
                // O Círculo da Foto
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .clip(CircleShape)
                        .background(Color.Gray),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        imageVector = Icons.Default.Person,
                        contentDescription = null,
                        tint = Color.White,
                        modifier = Modifier.size(50.dp)
                    )
                    // Futuramente: Image(...)
                }

                // O "Badge" com o ícone de Editar
                Box(
                    modifier = Modifier
                        .size(32.dp)
                        .align(Alignment.BottomEnd) // Canto inferior direito
                        .clip(CircleShape)
                        .background(GreenFresh)
                        .clickable { /* Ação de trocar foto */ },
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        imageVector = Icons.Default.Edit,
                        contentDescription = "Edit Photo",
                        tint = Color.Black,
                        modifier = Modifier.size(16.dp)
                    )
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Nome e Email (Texto estático por baixo da foto)
            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(text = name, color = Color.White, fontSize = 20.sp, fontWeight = FontWeight.Bold)
                Text(text = email, color = Color.Gray, fontSize = 14.sp)
            }

            Spacer(modifier = Modifier.height(32.dp))

            // 3. FORMULÁRIO DE EDIÇÃO

            // Campo Nome
            Text("Full Name", color = Color.Gray, fontSize = 14.sp, modifier = Modifier.padding(bottom = 8.dp))
            CustomDarkInput(text = name, onTextChange = { name = it }, placeholder = "Your Name")

            Spacer(modifier = Modifier.height(16.dp))

            // Campo Email
            Text("Email Address", color = Color.Gray, fontSize = 14.sp, modifier = Modifier.padding(bottom = 8.dp))
            CustomDarkInput(text = email, onTextChange = { email = it }, placeholder = "Your Email")

            Spacer(modifier = Modifier.height(16.dp))

            // Campo Telemóvel
            Text("Phone Number", color = Color.Gray, fontSize = 14.sp, modifier = Modifier.padding(bottom = 8.dp))
            CustomDarkInput(text = phone, onTextChange = { phone = it }, placeholder = "Your Phone")

            Spacer(modifier = Modifier.height(16.dp))

            // Campo Password (Mascarado)
            Text("Password", color = Color.Gray, fontSize = 14.sp, modifier = Modifier.padding(bottom = 8.dp))
            CustomDarkPasswordInput(text = password, onTextChange = { password = it }, placeholder = "Password")

            Spacer(modifier = Modifier.height(32.dp))

            // 4. BOTÃO SAVE CHANGES
            Button(
                onClick = { /* Lógica de Guardar */ },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp),
                colors = ButtonDefaults.buttonColors(containerColor = GreenFresh),
                shape = RoundedCornerShape(12.dp)
            ) {
                Text("Save Changes", color = Color.Black, fontWeight = FontWeight.Bold, fontSize = 16.sp)
            }

            // Espaço final para não bater no fundo/navbar
            Spacer(modifier = Modifier.height(Dimens.ScrollBottomPadding))
        }
    }
}