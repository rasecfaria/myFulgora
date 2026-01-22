package com.example.myfulgora.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.rounded.Autorenew
import androidx.compose.material.icons.rounded.BatteryStd
import androidx.compose.material.icons.rounded.Bolt
import androidx.compose.material.icons.rounded.Favorite
import androidx.compose.material.icons.rounded.Thermostat
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.myfulgora.ui.components.FulgoraBackground
import com.example.myfulgora.ui.theme.GreenFresh

@Composable
fun BatteryScreen() {
    FulgoraBackground {
        // 👇 ESTA É A CORREÇÃO MÁGICA: BoxWithConstraints dá-te o tamanho real do ecrã
        BoxWithConstraints(modifier = Modifier.fillMaxSize()) {

            // Agora sim, 'maxWidth' e 'maxHeight' são valores reais em Dp
            val screenW = maxWidth
            val screenH = maxHeight

            // Margens dinâmicas
            val horizontalPadding = screenW * 0.06f
            val verticalPadding = screenH * 0.03f

            // Tamanhos dinâmicos
            val iconSizeStandard = screenW * 0.07f

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 24.dp) // Podes usar horizontalPadding aqui se quiseres
                    .padding(top = 16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                // 1. CABEÇALHO (Usa a função local definida em baixo)
                BatteryScreenHeader(iconSize = iconSizeStandard)

                Spacer(modifier = Modifier.height(verticalPadding))

                // 2. TÍTULO
                Text(
                    text = "Battery Info",
                    textAlign = TextAlign.Left,
                    color = Color.White,
                    fontSize = 28.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.fillMaxWidth()
                )

                Spacer(modifier = Modifier.height(32.dp))

                // 3. A BATERIA GRANDE E INFO
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    BigBatteryIndicator(percentage = 0.78f)

                    Spacer(modifier = Modifier.width(24.dp))

                    BatteryInfoCard()
                }


                Spacer(modifier = Modifier.height(48.dp))

                // 4. A GRELHA DE ESTATÍSTICAS
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
                            icon = Icons.Rounded.Autorenew,
                            title = "Charging cycles",
                            value = "124 cycles",
                            modifier = Modifier.weight(1f)
                        )
                    }
                }
            }
        }
    }
}

// --- COMPONENTES AUXILIARES ---

// Mudei o nome para 'BatteryScreenHeader' para não entrar em conflito com o 'HomeHeader' do outro ecrã
@Composable
fun BatteryScreenHeader(iconSize: Dp) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column {
            Text(text = "Hi, Alex!", color = Color.Gray, fontSize = 14.sp)
            Text(text = "Ready to ride?", color = Color.White, fontSize = 18.sp, fontWeight = FontWeight.Bold)
        }
        Row {
            // Aqui usamos o iconSize dinâmico que calculaste lá em cima
            Icon(Icons.Default.Notifications, contentDescription = null, tint = Color.White, modifier = Modifier.size(iconSize))
            Spacer(modifier = Modifier.width(16.dp))
            Icon(Icons.Default.Menu, contentDescription = null, tint = Color.White, modifier = Modifier.size(iconSize))
        }
    }
}

@Composable
fun BigBatteryIndicator(percentage: Float) {
    Box(
        modifier = Modifier
            .width(80.dp)
            .height(160.dp)
            .border(2.dp, Color(0xFF333333), RoundedCornerShape(16.dp))
            .padding(6.dp),
        contentAlignment = Alignment.BottomCenter
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(percentage)
                .clip(RoundedCornerShape(10.dp))
                .background(GreenFresh)
        )
        Text(
            text = "${(percentage * 100).toInt()}%",
            color = Color.White,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.align(Alignment.Center).padding(top = 80.dp)
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

@Composable
fun BatteryInfoCard() {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(IntrinsicSize.Min),
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color(0xFF1E1E1E)
        )
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            Text(
                text = "Charging",
                color = GreenFresh,
                fontWeight = FontWeight.Bold
            )

            Text(
                text = "Time to full charge",
                color = Color.Gray,
                fontSize = 12.sp
            )

            Text(
                text = "03h 7m",
                color = Color.White,
                fontSize = 32.sp,
                fontWeight = FontWeight.Bold
            )

            Spacer(modifier = Modifier.height(16.dp))

            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(
                    Icons.Rounded.BatteryStd,
                    contentDescription = null,
                    tint = Color.Gray,
                    modifier = Modifier.size(16.dp)
                )
                Text(" 78%", color = Color.Gray, fontSize = 12.sp)

                Spacer(modifier = Modifier.width(16.dp))

                Icon(
                    Icons.Rounded.Bolt,
                    contentDescription = null,
                    tint = GreenFresh,
                    modifier = Modifier.size(16.dp)
                )
                Text(" Charging", color = Color.Gray, fontSize = 12.sp)
            }
        }
    }
}
