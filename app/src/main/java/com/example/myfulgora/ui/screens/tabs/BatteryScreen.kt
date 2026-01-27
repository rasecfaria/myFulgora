package com.example.myfulgora.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.rounded.Autorenew
import androidx.compose.material.icons.rounded.Favorite
import androidx.compose.material.icons.rounded.Thermostat
import androidx.compose.material.icons.rounded.Bolt
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.myfulgora.ui.components.FulgoraBackground
import com.example.myfulgora.ui.theme.AppIcons
import com.example.myfulgora.ui.theme.GreenFresh

@Composable
fun BatteryScreen() {
    FulgoraBackground {
        // BoxWithConstraints para obter tamanhos reais do ecrã
        BoxWithConstraints(modifier = Modifier.fillMaxSize()) {

            val screenW = maxWidth
            val screenH = maxHeight

            // Tamanhos dinâmicos para o Header
            val iconSizeStandard = screenW * 0.07f
            val verticalPadding = screenH * 0.03f

            // Estado do Scroll
            val scrollState = rememberScrollState()

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .verticalScroll(scrollState) // Ativa o Scroll
                    .padding(horizontal = 24.dp)
                    .padding(top = 16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                // 1. CABEÇALHO
                BatteryScreenHeader(iconSize = iconSizeStandard)

                Spacer(modifier = Modifier.height(verticalPadding))

                // 2. TÍTULO
                Text(
                    text = "Battery Info",
                    textAlign = TextAlign.Start,
                    color = Color.White,
                    fontSize = 28.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.fillMaxWidth()
                )

                Spacer(modifier = Modifier.height(32.dp))

                // 3. A BATERIA GRANDE E INFO (Lado a Lado Sincronizados)
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(IntrinsicSize.Min), // ⚠️ Força a Bateria a ter a mesma altura do Cartão
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    // Ícone da Bateria Grande
                    BigBatteryIndicator(
                        modifier = Modifier
                            .weight(0.35f)   // Ocupa 35% da largura
                            .fillMaxHeight() // Estica para acompanhar a altura do texto
                    )

                    Spacer(modifier = Modifier.width(16.dp))

                    // O Cartão de Texto (que define a altura)
                    Box(modifier = Modifier.weight(0.65f)) {
                        BatteryInfoCard()
                    }
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

                // 5. MARGEM DE SEGURANÇA FINAL
                // Garante que o último conteúdo fica acima da navbar quando fazes scroll total
                Spacer(modifier = Modifier.height(100.dp))
            }
        }
    }
}

// --- COMPONENTES AUXILIARES ---

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
            Icon(Icons.Default.Notifications, contentDescription = null, tint = Color.White, modifier = Modifier.size(iconSize))
            Spacer(modifier = Modifier.width(16.dp))
            Icon(Icons.Default.Menu, contentDescription = null, tint = Color.White, modifier = Modifier.size(iconSize))
        }
    }
}

@Composable
fun BigBatteryIndicator(modifier: Modifier = Modifier) {
    // Contentor da imagem/ícone grande
    Box(
        modifier = modifier,
        contentAlignment = Alignment.Center
    ) {
        Icon(
            painter = painterResource(id = AppIcons.Battery.BigBatteryCharging), // Verifica se o ID está correto no teu AppIcons
            contentDescription = "Battery Status",
            tint = GreenFresh,
            modifier = Modifier
                .fillMaxSize() // Ocupa o espaço que o 'weight' lhe der
                .scale(1.3f)
        )
    }
}

@Composable
fun BatteryInfoCard() {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color(0xFF1E1E1E)
        )
    ) {
        Column(
            modifier = Modifier.padding(12.dp)
        ) {
            // --- PARTE DE CIMA (Texto) ---
            // Adicionei este Column com padding para alinhar com o ícone de baixo
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 16.dp) // 👈 O empurrãozinho para a direita (IGUAL AO DE BAIXO)
            ) {
                Text(text = "Charging", color = GreenFresh, fontWeight = FontWeight.Bold)

                Text(
                    text = "Time left",
                    color = Color.Gray,
                    fontSize = 12.sp
                )

                Text(
                    text = "03h 7m",
                    color = Color.White,
                    fontSize = 32.sp,
                    fontWeight = FontWeight.Bold
                )
            }

            Spacer(modifier = Modifier.height(12.dp))

            // --- A LINHA DOS 3 ÍCONES ---
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                // --- ITEM 1: ESQUERDA (Agora alinhado com o texto) ---
                Column(
                    modifier = Modifier
                        .weight(1f)
                        .aspectRatio(1f)
                        // 👇 MUDANÇA CRÍTICA:
                        .padding(start = 16.dp), // 1. Mesmo padding do texto de cima
                    horizontalAlignment = Alignment.Start, // 2. Alinhado à esquerda (Start)
                    verticalArrangement = Arrangement.Center
                ) {
                    Icon(
                        painter = painterResource(id = AppIcons.Battery.Battery),
                        contentDescription = "Battery",
                        tint = GreenFresh,
                        modifier = Modifier.size(24.dp)
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    Text("78%", color = Color.Gray, fontSize = 12.sp, fontWeight = FontWeight.Medium)
                }

                // --- ITEM 2: CENTRO (Mantém-se igual) ---
                Column(
                    modifier = Modifier
                        .weight(1f)
                        .aspectRatio(1f),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    Icon(
                        painter = painterResource(id = AppIcons.Battery.Range),
                        contentDescription = "Range",
                        tint = GreenFresh,
                        modifier = Modifier.size(24.dp)
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    Text("92 km", color = Color.Gray, fontSize = 12.sp, fontWeight = FontWeight.Medium)
                }

                // --- ITEM 3: DIREITA (Mantém-se igual ou End) ---
                Column(
                    modifier = Modifier
                        .weight(1f)
                        .aspectRatio(1f),
                    horizontalAlignment = Alignment.CenterHorizontally, // Podes mudar para Alignment.End se preferires encostado
                    verticalArrangement = Arrangement.Center
                ) {
                    Icon(
                        painter = painterResource(id = AppIcons.Battery.Charging),
                        contentDescription = "Charging",
                        tint = GreenFresh,
                        modifier = Modifier.size(24.dp)
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    Text("Charging", color = Color.Gray, fontSize = 12.sp, fontWeight = FontWeight.Medium)
                }
            }
        }
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