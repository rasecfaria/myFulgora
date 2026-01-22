package com.example.myfulgora.ui.screens.tabs

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.rounded.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.myfulgora.R
import com.example.myfulgora.ui.components.FulgoraBackground // O teu componente
import com.example.myfulgora.ui.theme.GreenFresh

@Composable
fun HomeScreen() {
    // 👇 AQUI ESTÁ ELE: O teu componente background a envolver tudo
    FulgoraBackground {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 24.dp)
                .padding(top = 24.dp, bottom = 16.dp), // Ajuste de margens
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            // 1. HEADER
            HomeHeader()

            Spacer(modifier = Modifier.height(24.dp))

            // 2. NOME DA MOTA E BATERIA
            Column(modifier = Modifier.fillMaxWidth()) {
                Text(
                    text = "Bike Name",
                    color = Color.White,
                    fontSize = 28.sp,
                    fontWeight = FontWeight.Bold
                )
                Spacer(modifier = Modifier.height(8.dp))
                // Pílula da Bateria
                Surface(
                    color = Color(0xFF1E1E1E),
                    shape = RoundedCornerShape(50),
                    modifier = Modifier.height(32.dp)
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier.padding(horizontal = 12.dp)
                    ) {
                        Icon(
                            imageVector = Icons.Rounded.Bolt,
                            contentDescription = null,
                            tint = GreenFresh,
                            modifier = Modifier.size(16.dp)
                        )
                        Spacer(modifier = Modifier.width(4.dp))
                        Text(text = "77%", color = Color.White, fontSize = 14.sp)
                    }
                }
            }

            // Espaço Elástico (Empurra a mota para o centro)
            Spacer(modifier = Modifier.weight(1f))

            // 3. A MOTA E O CÍRCULO (ANTI-OVO) 🥚🚫
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier
                    .fillMaxWidth(0.95f) // Ocupa quase a largura toda
                    .aspectRatio(1f)     // ⚠️ OBRIGATÓRIO: Garante que é Quadrado Perfeito
            ) {
                // O Arco Verde
                Canvas(modifier = Modifier.fillMaxSize()) {
                    drawArc(
                        color = Color(0xFF333333),
                        startAngle = 140f,
                        sweepAngle = 260f,
                        useCenter = false,
                        style = Stroke(width = 15.dp.toPx(), cap = StrokeCap.Round)
                    )
                    drawArc(
                        color = GreenFresh,
                        startAngle = 140f,
                        sweepAngle = 180f,
                        useCenter = false,
                        style = Stroke(width = 15.dp.toPx(), cap = StrokeCap.Round)
                    )
                }

                // A Imagem da Mota
                Image(
                    painter = painterResource(id = R.drawable.mota), // Verifica se o nome está certo
                    contentDescription = "Mota",
                    modifier = Modifier.fillMaxSize(0.85f) // Ajusta a escala da imagem dentro do círculo
                )

                // Setas laterais
                Icon(Icons.Rounded.ChevronLeft, contentDescription = null, tint = Color.Gray, modifier = Modifier.align(Alignment.CenterStart).size(32.dp))
                Icon(Icons.Rounded.ChevronRight, contentDescription = null, tint = GreenFresh, modifier = Modifier.align(Alignment.CenterEnd).size(32.dp))
            }

            // Espaço Elástico
            Spacer(modifier = Modifier.weight(1f))

            // 4. CONTROLOS (SWIPE + POWER)
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                // Botão Power
                IconButton(
                    onClick = { },
                    modifier = Modifier
                        .size(56.dp)
                        .background(GreenFresh, CircleShape)
                ) {
                    Icon(Icons.Rounded.PowerSettingsNew, contentDescription = "Power", tint = Color.White)
                }

                Spacer(modifier = Modifier.width(16.dp))

                // Swipe Area
                Box(
                    modifier = Modifier
                        .weight(1f)
                        .height(56.dp)
                        .background(Color(0xFF1E1E1E), RoundedCornerShape(50)),
                    contentAlignment = Alignment.CenterStart
                ) {
                    Text(
                        text = "Swipe to unlock >>",
                        color = Color.Gray,
                        fontSize = 14.sp,
                        modifier = Modifier.align(Alignment.Center)
                    )
                    Box(
                        modifier = Modifier
                            .padding(4.dp)
                            .size(48.dp)
                            .background(GreenFresh, CircleShape),
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(Icons.Rounded.Lock, contentDescription = null, tint = Color.White, modifier = Modifier.size(20.dp))
                    }
                }
            }

            Spacer(modifier = Modifier.height(32.dp))

            // 5. RODAPÉ
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                DashboardStat(Icons.Rounded.BatteryStd, "75%", "Charge")
                DashboardStat(Icons.Rounded.Bolt, "3,72", "kWh/100")
                DashboardStat(Icons.Rounded.Speed, "245", "km/h")
                DashboardStat(Icons.Rounded.Map, "Loc", "Location")
            }
        }
    }
}

// --- SUB-COMPONENTES (Podem ficar no mesmo ficheiro ou noutro) ---

@Composable
fun HomeHeader() {
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
            Icon(Icons.Default.Notifications, contentDescription = null, tint = Color.White)
            Spacer(modifier = Modifier.width(16.dp))
            Icon(Icons.Default.Menu, contentDescription = null, tint = Color.White)
        }
    }
}

@Composable
fun DashboardStat(icon: ImageVector, value: String, label: String) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Icon(imageVector = icon, contentDescription = null, tint = GreenFresh, modifier = Modifier.size(24.dp))
        Spacer(modifier = Modifier.height(8.dp))
        Text(text = value, color = Color.White, fontWeight = FontWeight.Bold, fontSize = 16.sp)
        Text(text = label, color = Color.Gray, fontSize = 10.sp)
    }
}