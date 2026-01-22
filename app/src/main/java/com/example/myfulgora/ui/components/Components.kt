package com.example.myfulgora.ui.components

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.myfulgora.ui.theme.BlackBrand
import com.example.myfulgora.ui.theme.GreenDeep
import com.example.myfulgora.ui.theme.GreenFresh

@Composable
fun FulgoraBackground(
    content: @Composable BoxScope.() -> Unit
) {
    // Foco 1: O brilho Verde no canto inferior esquerdo (Forte)
    val bottomGlow = Brush.radialGradient(
        colors = listOf(
            GreenDeep.copy(alpha = 0.5f), // Verde transparente no centro
            Color.Transparent             // Desaparece para fora
        ),
        center = Offset(x = 0f, y = Float.POSITIVE_INFINITY), // Canto Inferior Esquerdo
        radius = 1500f // Tamanho do brilho
    )

    // Foco 2: O brilho Azulado/Cinza no topo direito (Subtil)
    val topGlow = Brush.radialGradient(
        colors = listOf(
            Color(0xFF1E293B).copy(alpha = 0.6f), // Azul escuro subtil
            Color.Transparent
        ),
        center = Offset(x = Float.POSITIVE_INFINITY, y = 0f), // Canto Superior Direito
        radius = 1200f
    )

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(BlackBrand) // 1. Base Preta Absoluta
            .background(bottomGlow) // 2. Adiciona o Foco Verde em baixo
            .background(topGlow)    // 3. Adiciona o Foco Azul em cima
    ) {
        content() // O conteúdo do ecrã (botões, textos) fica por cima disto tudo
    }
}
@Composable
fun CircularBatteryArc(percentage: Float) {
    Canvas(modifier = Modifier.size(300.dp)) {
        // Arco de Fundo (Mais fino e escuro)
        drawArc(
            color = Color(0xFF1E293B),
            startAngle = 140f,
            sweepAngle = 260f,
            useCenter = false,
            style = Stroke(width = 15f, cap = StrokeCap.Round)
        )
        // Arco de Progresso (Verde Vibrante)
        drawArc(
            color = GreenFresh, // Cor sólida verde, como no mockup novo
            startAngle = 140f,
            sweepAngle = 260f * percentage,
            useCenter = false,
            style = Stroke(width = 15f, cap = StrokeCap.Round)
        )
    }
}

@Composable
fun DashboardStat(icon: ImageVector, value: String, label: String) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Icon(imageVector = icon, contentDescription = null, tint = GreenFresh, modifier = Modifier.size(24.dp))
        Spacer(modifier = Modifier.height(4.dp))
        Text(text = value, color = Color.White, fontWeight = FontWeight.Bold, fontSize = 14.sp)
        Text(text = label, color = Color.Gray, fontSize = 12.sp)
    }
}