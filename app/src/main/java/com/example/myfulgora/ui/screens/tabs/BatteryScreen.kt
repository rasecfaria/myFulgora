package com.example.myfulgora.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.BatteryStd
import androidx.compose.material.icons.rounded.Bolt
import androidx.compose.material.icons.rounded.Favorite
import androidx.compose.material.icons.rounded.Thermostat
import androidx.compose.material.icons.rounded.Speed
import androidx.compose.material.icons.rounded.Autorenew
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.myfulgora.ui.components.FulgoraBackground
import com.example.myfulgora.ui.theme.GreenFresh // A tua cor verde

@Composable
fun BatteryScreen() {
    FulgoraBackground {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 24.dp)
                .padding(top = 16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
        // 1. TÍTULO
        Text(
            text = "Battery Info",
            color = Color.White,
            fontSize = 28.sp,
            fontWeight = FontWeight.Bold
        )

        Spacer(modifier = Modifier.height(32.dp))

        // 2. A BATERIA GRANDE E INFO (Lado a Lado)
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Desenho da Bateria Grande
            BigBatteryIndicator(percentage = 0.78f) // 78%

            Spacer(modifier = Modifier.width(24.dp))

            // Informações de Texto (Lado Direito)
            Column {
                Text(text = "Charging", color = GreenFresh, fontWeight = FontWeight.Bold)
                Text(text = "Time to full charge", color = Color.Gray, fontSize = 12.sp)
                Text(
                    text = "03h 7m",
                    color = Color.White,
                    fontSize = 32.sp,
                    fontWeight = FontWeight.Bold
                )

                Spacer(modifier = Modifier.height(16.dp))

                // Pequenos ícones de resumo
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(Icons.Rounded.BatteryStd, contentDescription = null, tint = Color.Gray, modifier = Modifier.size(16.dp))
                    Text(" 78%", color = Color.Gray, fontSize = 12.sp)
                    Spacer(modifier = Modifier.width(16.dp))
                    Icon(Icons.Rounded.Bolt, contentDescription = null, tint = GreenFresh, modifier = Modifier.size(16.dp))
                    Text(" Charging", color = Color.Gray, fontSize = 12.sp)
                }
            }
        }

        Spacer(modifier = Modifier.height(48.dp))

        // 3. A GRELHA DE ESTATÍSTICAS (Grid 2x2)
        Column(verticalArrangement = Arrangement.spacedBy(16.dp)) {
            // Linha 1
            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(16.dp)) {
                BatteryStatCard(
                    icon = Icons.Rounded.Favorite,
                    title = "Battery health",
                    value = "Good",
                    modifier = Modifier.weight(1f)
                )
                BatteryStatCard(
                    icon = Icons.Rounded.Thermostat,
                    title = "Temperature",
                    value = "32°C",
                    modifier = Modifier.weight(1f)
                )
            }
            // Linha 2
            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(16.dp)) {
                BatteryStatCard(
                    icon = Icons.Rounded.Bolt,
                    title = "Avg. consumption",
                    value = "45 Wh/km",
                    modifier = Modifier.weight(1f)
                )
                BatteryStatCard(
                    icon = Icons.Rounded.Autorenew, // Ícone de ciclos
                    title = "Charging cycles",
                    value = "124 cycles",
                    modifier = Modifier.weight(1f)
                )
            }
        }
    }
    }

}

// --- COMPONENTES AUXILIARES ---

@Composable
fun BigBatteryIndicator(percentage: Float) {
    // O Retângulo exterior da bateria
    Box(
        modifier = Modifier
            .width(80.dp)
            .height(160.dp)
            .border(2.dp, Color(0xFF333333), RoundedCornerShape(16.dp))
            .padding(6.dp), // Espaço entre a borda e o recheio
        contentAlignment = Alignment.BottomCenter
    ) {
        // O "Sumo" da bateria (Parte Verde)
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(percentage) // Enche conforme a percentagem (0.78)
                .clip(RoundedCornerShape(10.dp))
                .background(GreenFresh)
        )

        // Texto da percentagem no meio
        Text(
            text = "${(percentage * 100).toInt()}%",
            color = Color.White,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.align(Alignment.Center).padding(top = 80.dp) // Ajuste visual
        )
    }
}

@Composable
fun BatteryStatCard(
    icon: ImageVector,
    title: String,
    value: String,
    modifier: Modifier = Modifier
) {
    // O Cartão Cinzento Escuro
    Column(
        modifier = modifier
            .background(Color(0xFF1E1E1E), RoundedCornerShape(16.dp))
            .padding(16.dp)
    ) {
        Icon(
            imageVector = icon,
            contentDescription = null,
            tint = GreenFresh,
            modifier = Modifier.size(24.dp)
        )
        Spacer(modifier = Modifier.height(16.dp))
        Text(text = value, color = Color.White, fontWeight = FontWeight.Bold, fontSize = 16.sp)
        Text(text = title, color = Color.Gray, fontSize = 12.sp)
    }
}