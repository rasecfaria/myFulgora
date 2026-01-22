package com.example.myfulgora.ui.screens.auth

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.myfulgora.R
import com.example.myfulgora.ui.components.FulgoraBackground
import com.example.myfulgora.ui.viewmodel.LoginState
import com.example.myfulgora.ui.viewmodel.LoginViewModel
import com.example.myfulgora.ui.theme.GreenFresh
import com.example.myfulgora.ui.theme.GreenDeep

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginScreen(
    viewModel: LoginViewModel = viewModel(),
    onLoginSuccess: () -> Unit,
    onForgotPasswordClick: () -> Unit
) {
    val state by viewModel.loginState.collectAsState()
    val username by viewModel.username.collectAsState()
    val password by viewModel.password.collectAsState()

    var passwordVisible by remember { mutableStateOf(false) }

    // O Gradiente do Botão (Linear Horizontal: GreenFresh -> GreenDeep)
    val brandGradient = Brush.horizontalGradient(
        colors = listOf(GreenFresh, GreenDeep)
    )

    LaunchedEffect(state) {
        if (state is LoginState.Success) {
            onLoginSuccess()
        }
    }

    // AQUI ESTÁ A CORREÇÃO: Usamos o componente FulgoraBackground
    FulgoraBackground {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(24.dp)
                .statusBarsPadding(), // Respeita a zona da bateria/relógio
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {

            // 1. LOGÓTIPO
            Image(
                painter = painterResource(id = R.drawable.logo_app),
                contentDescription = "Logo",
                modifier = Modifier.width(120.dp),
                contentScale = ContentScale.Fit
            )

            Text(
                text = "Welcome back, Rider",
                fontSize = 20.sp,
                color = Color.Gray
            )

            Spacer(modifier = Modifier.height(48.dp))

            // 2. CAMPOS DE TEXTO
            // Username
            OutlinedTextField(
                value = username,
                onValueChange = { viewModel.username.value = it },
                label = { Text("Username") },
                placeholder = { Text("Ex: username") },
                leadingIcon = {
                    Icon(
                        Icons.Default.Person,
                        contentDescription = null,
                        tint = GreenFresh
                    )
                },
                modifier = Modifier.fillMaxWidth(),
                singleLine = true,
                colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = GreenFresh,
                    unfocusedBorderColor = Color.Gray,
                    focusedLabelColor = GreenFresh,
                    unfocusedLabelColor = Color.LightGray,
                    cursorColor = GreenFresh,
                    focusedTextColor = Color.White,
                    unfocusedTextColor = Color.White,
                    focusedContainerColor = Color.Transparent,
                    unfocusedContainerColor = Color.Transparent
                ),
                keyboardOptions = KeyboardOptions(imeAction = ImeAction.Next)
            )

            Spacer(modifier = Modifier.height(16.dp))

            // Password
            OutlinedTextField(
                value = password,
                onValueChange = { viewModel.password.value = it },
                label = { Text("Password") },
                leadingIcon = {
                    Icon(
                        Icons.Default.Lock,
                        contentDescription = null,
                        tint = GreenFresh
                    )
                },
                trailingIcon = {
                    val image =
                        if (passwordVisible) Icons.Default.Visibility else Icons.Default.VisibilityOff
                    IconButton(onClick = { passwordVisible = !passwordVisible }) {
                        Icon(imageVector = image, contentDescription = null, tint = Color.Gray)
                    }
                },
                modifier = Modifier.fillMaxWidth(),
                singleLine = true,
                visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
                colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = GreenFresh,
                    unfocusedBorderColor = Color.Gray,
                    focusedLabelColor = GreenFresh,
                    unfocusedLabelColor = Color.LightGray,
                    cursorColor = GreenFresh,
                    focusedTextColor = Color.White,
                    unfocusedTextColor = Color.White,
                    focusedContainerColor = Color.Transparent,
                    unfocusedContainerColor = Color.Transparent
                ),
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Password,
                    imeAction = ImeAction.Done
                )
            )

            // Link Esqueci a Password
            Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.CenterEnd) {
                TextButton(
                    // 2. Aqui usamos o parâmetro novo.
                    // Quando clicas, ele dispara o aviso para a MainActivity.
                    onClick = onForgotPasswordClick
                ) {
                    Text("Forgot Password?", color = GreenFresh, fontSize = 12.sp)
                }
            }

            Spacer(modifier = Modifier.height(24.dp))

            // Erro
            if (state is LoginState.Error) {
                Text(
                    text = (state as LoginState.Error).message,
                    color = Color(0xFFFF5252),
                    fontSize = 14.sp
                )
                Spacer(modifier = Modifier.height(16.dp))
            }

            // 3. BOTÃO DE LOGIN (Ajustado e com Gradiente)
            Button(
                onClick = { viewModel.fazerLogin() },
                modifier = Modifier.height(50.dp),
                shape = RoundedCornerShape(25.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color.Transparent
                ),
                contentPadding = PaddingValues(),
                enabled = state !is LoginState.Loading
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxHeight()
                        .background(brandGradient) // O Gradiente Horizontal
                        .padding(horizontal = 48.dp), // Largura visual do botão
                    contentAlignment = Alignment.Center
                ) {
                    if (state is LoginState.Loading) {
                        CircularProgressIndicator(
                            color = Color.White,
                            modifier = Modifier.size(24.dp)
                        )
                    } else {
                        Text(
                            text = "Log In",
                            fontSize = 18.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color.White
                        )
                    }
                }
            }
        }
    }
}