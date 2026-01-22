package com.example.myfulgora.ui.screens.auth

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.myfulgora.ui.components.FulgoraBackground // Certifica-te que tens este import
import com.example.myfulgora.ui.theme.GreenFresh
import com.example.myfulgora.ui.theme.GreenDeep

@Composable
fun ForgotPasswordScreen(
    onNavigateBack: () -> Unit,
    onLoginAfterReset: () -> Unit
) {
    // Controla qual o passo atual (1, 2, 3 ou 4)
    var currentStep by remember { mutableIntStateOf(1) }

    FulgoraBackground {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(24.dp)
                .statusBarsPadding(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            // Botão de Voltar (Só aparece nos passos 1, 2 e 3)
            if (currentStep < 4) {
                Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.CenterStart) {
                    IconButton(onClick = {
                        if (currentStep > 1) currentStep-- else onNavigateBack()
                    }) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back", tint = Color.White)
                    }
                }
            } else {
                Spacer(modifier = Modifier.height(48.dp)) // Espaço no ecrã de sucesso
            }

            Spacer(modifier = Modifier.height(20.dp))

            // Lógica para trocar o conteúdo do ecrã
            when (currentStep) {
                1 -> StepInputEmail(onNext = { currentStep = 2 })
                2 -> StepVerification(onNext = { currentStep = 3 })
                3 -> StepNewPassword(onNext = { currentStep = 4 })
                4 -> StepSuccess(onDone = onLoginAfterReset)
            }
        }
    }
}

// --- PASSO 1: EMAIL ---
@Composable
fun StepInputEmail(onNext: () -> Unit) {
    var email by remember { mutableStateOf("") }

    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Text("Forget your Password?", fontSize = 24.sp, fontWeight = FontWeight.Bold, color = Color.White)
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            "Enter the email address linked to your account and we'll send you a reset link.",
            color = Color.Gray, textAlign = TextAlign.Center, fontSize = 14.sp
        )
        Spacer(modifier = Modifier.height(32.dp))

        OutlinedTextField(
            value = email,
            onValueChange = { email = it },
            label = { Text("Email address") },
            modifier = Modifier.fillMaxWidth(),
            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = GreenFresh,
                unfocusedBorderColor = Color.Gray,
                focusedTextColor = Color.White,
                unfocusedTextColor = Color.White
            )
        )
        Spacer(modifier = Modifier.height(32.dp))

        Button(
            onClick = onNext,
            colors = ButtonDefaults.buttonColors(containerColor = GreenFresh),
            modifier = Modifier.fillMaxWidth().height(50.dp),
            shape = RoundedCornerShape(25.dp)
        ) {
            Text("Continue", color = Color.White, fontWeight = FontWeight.Bold, fontSize = 16.sp)
        }
    }
}

// --- PASSO 2: CÓDIGO ---
@Composable
fun StepVerification(onNext: () -> Unit) {
    var code by remember { mutableStateOf("") }

    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Text("Verification", fontSize = 24.sp, fontWeight = FontWeight.Bold, color = Color.White)
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            "Please enter the 4 digit code sent to\nab*****@gmail.com",
            color = Color.Gray, textAlign = TextAlign.Center, fontSize = 14.sp
        )
        Spacer(modifier = Modifier.height(32.dp))

        // Input Simples de 4 dígitos
        OutlinedTextField(
            value = code,
            onValueChange = { if (it.length <= 4) code = it },
            label = { Text("Enter 4-digit code") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            modifier = Modifier.fillMaxWidth(),
            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = GreenFresh, unfocusedBorderColor = Color.Gray,
                focusedTextColor = Color.White, unfocusedTextColor = Color.White
            )
        )

        Spacer(modifier = Modifier.height(32.dp))
        Button(
            onClick = onNext,
            colors = ButtonDefaults.buttonColors(containerColor = GreenFresh),
            modifier = Modifier.fillMaxWidth().height(50.dp),
            shape = RoundedCornerShape(25.dp)
        ) {
            Text("Verify", color = Color.White, fontWeight = FontWeight.Bold, fontSize = 16.sp)
        }
        Spacer(modifier = Modifier.height(16.dp))
        Text("Haven't received it? Send again", color = GreenDeep, fontSize = 12.sp, modifier = Modifier.clickable { })
    }
}

// --- PASSO 3: NOVA PASSWORD ---
@Composable
fun StepNewPassword(onNext: () -> Unit) {
    var pass1 by remember { mutableStateOf("") }
    var pass2 by remember { mutableStateOf("") }

    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Text("Create New Password", fontSize = 24.sp, fontWeight = FontWeight.Bold, color = Color.White)
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            "Your new password must be different from your previous password",
            color = Color.Gray, textAlign = TextAlign.Center, fontSize = 14.sp
        )
        Spacer(modifier = Modifier.height(32.dp))

        OutlinedTextField(
            value = pass1, onValueChange = { pass1 = it },
            label = { Text("Password") },
            modifier = Modifier.fillMaxWidth(),
            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = GreenFresh, unfocusedBorderColor = Color.Gray,
                focusedTextColor = Color.White, unfocusedTextColor = Color.White
            )
        )
        Spacer(modifier = Modifier.height(16.dp))
        OutlinedTextField(
            value = pass2, onValueChange = { pass2 = it },
            label = { Text("Confirm Password") },
            modifier = Modifier.fillMaxWidth(),
            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = GreenFresh, unfocusedBorderColor = Color.Gray,
                focusedTextColor = Color.White, unfocusedTextColor = Color.White
            )
        )

        Spacer(modifier = Modifier.height(32.dp))
        Button(
            onClick = onNext,
            colors = ButtonDefaults.buttonColors(containerColor = GreenFresh),
            modifier = Modifier.fillMaxWidth().height(50.dp),
            shape = RoundedCornerShape(25.dp)
        ) {
            Text("Reset Password", color = Color.White, fontWeight = FontWeight.Bold, fontSize = 16.sp)
        }
    }
}

// --- PASSO 4: SUCESSO ---
@Composable
fun StepSuccess(onDone: () -> Unit) {
    Column(horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.Center, modifier = Modifier.fillMaxHeight()) {
        Text("myFULGORA", fontSize = 24.sp, fontWeight = FontWeight.Bold, color = Color.White)
        Spacer(modifier = Modifier.height(40.dp))

        Icon(Icons.Default.CheckCircle, contentDescription = null, tint = GreenFresh, modifier = Modifier.size(80.dp))

        Spacer(modifier = Modifier.height(24.dp))
        Text("Password updated", fontSize = 22.sp, fontWeight = FontWeight.Bold, color = GreenFresh)
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            "Your password has been changed successfully.\nYou can now Log In with your new password.",
            color = Color.Gray, textAlign = TextAlign.Center, fontSize = 14.sp
        )

        Spacer(modifier = Modifier.height(40.dp))
        Button(
            onClick = onDone,
            colors = ButtonDefaults.buttonColors(containerColor = GreenFresh),
            modifier = Modifier.fillMaxWidth().height(50.dp),
            shape = RoundedCornerShape(25.dp)
        ) {
            Text("Log In", color = Color.White, fontWeight = FontWeight.Bold, fontSize = 16.sp)
        }
        Spacer(modifier = Modifier.height(100.dp)) // Para empurrar visualmente para cima
    }
}