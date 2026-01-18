package com.example.myfulgora.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.myfulgora.R
import kotlinx.coroutines.delay

@Composable
fun SplashScreen(onSplashFinished: () -> Unit) {

    // O mesmo fundo degradê para manter a consistência
    val darkGradient = Brush.verticalGradient(
        colors = listOf(
            Color(0xFF0F172A),
            Color(0xFF052e16)
        )
    )

    // O Temporizador de 2 segundos
    LaunchedEffect(Unit) {
        delay(2000) // Espera 2000 milissegundos (2 segundos)
        onSplashFinished() // Avisa que acabou
    }

    // O Design
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(darkGradient),
        contentAlignment = Alignment.Center
    ) {
        // O Logótipo no centro
        Image(
            painter = painterResource(id = R.drawable.logo_app),
            contentDescription = "Logo",
            modifier = Modifier.size(150.dp) // Podes ajustar o tamanho aqui
        )
    }
}