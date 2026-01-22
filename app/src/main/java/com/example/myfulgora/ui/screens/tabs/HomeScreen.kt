package com.example.myfulgora.ui.screens

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.myfulgora.R
import com.example.myfulgora.ui.components.FulgoraBackground
import com.example.myfulgora.ui.theme.GreenFresh // Certifica-te que tens esta cor

@Composable
fun HomeScreen() {
    FulgoraBackground {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 24.dp)
                .padding(top = 16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            // 1. HEADER (Topo)
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column {
                    Text(
                        "Hi, Alex!",
                        color = Color.White,
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold
                    )
                    Text("Ready to ride?", color = Color.Gray, fontSize = 14.sp)
                }
                IconButton(onClick = { }) {
                    Icon(
                        Icons.Default.Notifications,
                        contentDescription = "Alerts",
                        tint = Color.Gray
                    )
                }
            }

            Spacer(modifier = Modifier.height(24.dp))

            // 2. NOME DA MOTA E STATUS
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.Top
            ) {
                Column {
                    Text(
                        "Bike Name",
                        color = Color.White,
                        fontSize = 28.sp,
                        fontWeight = FontWeight.Bold
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(
                            Icons.Default.Bolt,
                            contentDescription = null,
                            tint = GreenFresh,
                            modifier = Modifier.size(18.dp)
                        )
                        Spacer(modifier = Modifier.width(4.dp))
                        Text("77%", color = Color.White, fontWeight = FontWeight.Bold)
                    }
                }

                // Botão de atalho para o Mapa (Canto direito)
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Icon(
                        Icons.Default.Map,
                        contentDescription = null,
                        tint = GreenFresh,
                        modifier = Modifier.size(24.dp)
                    )
                    Text("Map", color = Color.Gray, fontSize = 10.sp)
                }
            }

            Spacer(modifier = Modifier.height(20.dp))

            // 3. HERO SECTION (Mota + Arco)
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier.weight(1f) // Ocupa o espaço disponível no meio
            ) {
                // Arco de Progresso
                CircularBatteryArc(percentage = 0.77f)

                // Imagem da Mota
                Image(
                    painter = painterResource(id = R.drawable.logo_app), // ⚠️ Troca pela imagem da mota real
                    contentDescription = "Motorcycle",
                    modifier = Modifier.size(250.dp)
                )

                // Setas laterais
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Icon(
                        Icons.Default.ChevronLeft,
                        contentDescription = null,
                        tint = Color.Gray,
                        modifier = Modifier.size(32.dp)
                    )
                    Icon(
                        Icons.Default.ChevronRight,
                        contentDescription = null,
                        tint = Color.Gray,
                        modifier = Modifier.size(32.dp)
                    )
                }
            }

            // 4. CONTROLO (Power + Unlock)
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                // Botão Power (Quadrado Verde)
                Button(
                    onClick = { /* Lógica Power */ },
                    shape = RoundedCornerShape(16.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = GreenFresh),
                    contentPadding = PaddingValues(0.dp),
                    modifier = Modifier.size(60.dp)
                ) {
                    Icon(
                        Icons.Default.PowerSettingsNew,
                        contentDescription = "Power",
                        tint = Color.White,
                        modifier = Modifier.size(32.dp)
                    )
                }

                // Slider de Desbloqueio (Visual)
                // Simulamos o visual do mockup: fundo escuro, texto e ícone de cadeado
                Button(
                    onClick = { /* Lógica Unlock */ },
                    shape = RoundedCornerShape(16.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF1E293B)), // Fundo cinza escuro
                    modifier = Modifier
                        .height(60.dp)
                        .weight(1f) // Ocupa o resto da largura
                ) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Spacer(modifier = Modifier.width(8.dp)) // Espaço vazio à esquerda
                        Text("Swipe to unlock >>", color = Color.Gray, fontSize = 14.sp)

                        // Ícone do Cadeado dentro de um círculo branco/transparente
                        Box(
                            modifier = Modifier
                                .size(40.dp)
                                .clip(CircleShape)
                                .background(Color.White.copy(alpha = 0.1f)),
                            contentAlignment = Alignment.Center
                        ) {
                            Icon(
                                Icons.Default.Lock,
                                contentDescription = "Locked",
                                tint = Color.White
                            )
                        }
                    }
                }
            }

            Spacer(modifier = Modifier.height(32.dp))

            // 5. STATS ROW (Fundo do ecrã)
            // 4 Colunas como no mockup: Bateria, Velocidade, Peso/Info, Status
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 16.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                DashboardStat(icon = Icons.Default.BatteryStd, value = "75%", label = "Charge")
                DashboardStat(
                    icon = Icons.Default.Speed,
                    value = "12.3",
                    label = "km/h"
                ) // Ou km Range
                DashboardStat(icon = Icons.Default.TwoWheeler, value = "12.8", label = "kg")
                DashboardStat(icon = Icons.Default.Info, value = "Offline", label = "Status")
            }
        }
    }
}

// --- COMPONENTES AUXILIARES ---

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
fun DashboardStat(icon: androidx.compose.ui.graphics.vector.ImageVector, value: String, label: String) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Icon(imageVector = icon, contentDescription = null, tint = GreenFresh, modifier = Modifier.size(24.dp))
        Spacer(modifier = Modifier.height(4.dp))
        Text(text = value, color = Color.White, fontWeight = FontWeight.Bold, fontSize = 14.sp)
        Text(text = label, color = Color.Gray, fontSize = 12.sp)
    }
}