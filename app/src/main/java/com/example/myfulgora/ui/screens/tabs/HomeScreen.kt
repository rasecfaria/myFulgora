package com.example.myfulgora.ui.screens.tabs

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Bolt
import androidx.compose.material.icons.rounded.DirectionsBike
import androidx.compose.material.icons.rounded.LocationOn
import androidx.compose.material.icons.rounded.Lock
import androidx.compose.material.icons.rounded.LockOpen
import androidx.compose.material.icons.rounded.PowerSettingsNew
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.myfulgora.ui.components.FulgoraBackground
import com.example.myfulgora.ui.components.FulgoraInfoCard
import com.example.myfulgora.ui.components.FulgoraTopBar
import com.example.myfulgora.ui.theme.AppIcons
import com.example.myfulgora.ui.theme.Dimens
import com.example.myfulgora.ui.theme.GreenFresh

@Composable
fun HomeScreen(
    onMenuClick: () -> Unit = {}
) {
    // Estado para simular o bloqueio/desbloqueio (como no mockup)
    var isLocked by remember { mutableStateOf(true) }

    FulgoraBackground {
        BoxWithConstraints(modifier = Modifier.fillMaxSize()) {

            val screenW = maxWidth
            val iconSize = screenW * Dimens.IconScaleRatio
            val paddingSide = screenW * Dimens.SideMarginRatio
            val circleSize = screenW * Dimens.HomeCircleRatio
            val strokeWidth = 12.dp

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(top = Dimens.TopPadding)
                    .padding(horizontal = paddingSide),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                // 1. TOP BAR
                FulgoraTopBar(
                    title = "Hi, Alex!",
                    subtitle = "Ready to ride?",
                    iconSize = iconSize,
                    onMenuClick = onMenuClick
                )

                Spacer(modifier = Modifier.height(24.dp))

                // 2. BIKE NAME & MINI STATUS
                Column(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalAlignment = Alignment.Start
                ) {
                    Text(
                        text = "1FA6P8CF3E5100000",
                        color = Color.White,
                        fontSize = 28.sp,
                        fontWeight = FontWeight.ExtraBold
                    )
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(
                            painter = painterResource(id = AppIcons.Dashboard.BatteryTop),
                            contentDescription = null,
                            tint = GreenFresh,
                            modifier = Modifier.size(34.dp)
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        Text(
                            text = if (isLocked) "77%" else "1hr 10m 4s remaining",
                            color = Color.White,
                            fontSize = 14.sp
                        )
                    }
                }

                Spacer(modifier = Modifier.weight(1f))

                // 3. CÍRCULO CENTRAL COM SETAS
                Box(
                    modifier = Modifier.fillMaxWidth(),
                    contentAlignment = Alignment.Center
                ) {
                    // Setas de navegação (Mockup)
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(
                            painter = painterResource(id = AppIcons.Dashboard.ArrowLeft0),
                            contentDescription = null,
                            tint = Color.Gray.copy(alpha = 0.5f),
                            modifier = Modifier.size(24.dp)
                        )
                        Icon(
                            painter = painterResource(id = AppIcons.Dashboard.ArrowRight0),
                            contentDescription = null,
                            tint = GreenFresh,
                            modifier = Modifier.size(24.dp)
                        )
                    }

                    // Círculo e Mota
                    Box(
                        modifier = Modifier.size(circleSize),
                        contentAlignment = Alignment.Center
                    ) {
                        // A. Arcos
                        Canvas(modifier = Modifier.fillMaxSize()) {
                            // Fundo
                            drawArc(
                                color = Color.Gray.copy(alpha = 0.2f),
                                startAngle = 0f,
                                sweepAngle = 360f,
                                useCenter = false,
                                style = Stroke(width = strokeWidth.toPx())
                            )
                            // Progresso (Verde)
                            drawArc(
                                color = GreenFresh,
                                startAngle = -90f,
                                sweepAngle = 280f, // 78% simulado
                                useCenter = false,
                                style = Stroke(width = strokeWidth.toPx(), cap = StrokeCap.Round)
                            )
                        }

                        // B. A Mota (Sozinha no meio)
                        Image(
                            painter = painterResource(id = AppIcons.Dashboard.MainBike),
                            contentDescription = "My Bike",
                            modifier = Modifier
                                .fillMaxWidth(1f)
                                .aspectRatio(1.4f)
                        )
                    }
                }

                Spacer(modifier = Modifier.weight(1f))

                // 4. BOTÕES DE ACÇÃO (Power & Lock Slider)
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(56.dp), // Altura fixa dos botões
                    horizontalArrangement = Arrangement.spacedBy(16.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {

                    // --- BOTÃO POWER (40%) ---
                    Surface(
                        modifier = Modifier
                            .weight(0.4f)      // 👈 Ocupa 40% da largura disponível
                            .fillMaxHeight(),  // Ocupa toda a altura da linha (56dp)
                        shape = RoundedCornerShape(28.dp), // Forma de "Pílula" para acompanhar o esticão
                        color = if (isLocked) GreenFresh else Color(0xFF1E1E1E),
                        // Adicionei onClick vazio ou com lógica se quiseres que ele faça algo
                        onClick = { /* Lógica do Power */ }
                    ) {
                        Box(contentAlignment = Alignment.Center) {
                            Icon(
                                imageVector = Icons.Rounded.PowerSettingsNew,
                                contentDescription = "Power",
                                tint = if (isLocked) Color.Black else Color.Gray,
                                modifier = Modifier.size(28.dp)
                            )
                        }
                    }

                    // --- SLIDER LOCK/UNLOCK (60%) ---
                    Surface(
                        modifier = Modifier
                            .weight(0.6f)      // 👈 Ocupa os restantes 60%
                            .fillMaxHeight(),
                        shape = RoundedCornerShape(28.dp),
                        color = if (isLocked) Color(0xFF1E1E1E) else GreenFresh,
                        onClick = { isLocked = !isLocked }
                    ) {
                        Row(
                            modifier = Modifier
                                .fillMaxSize()
                                .padding(horizontal = 8.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            if (isLocked) {
                                // Estado: BLOQUEADO (Bola à esquerda)
                                Surface(
                                    modifier = Modifier.size(40.dp), // A bola interna
                                    shape = CircleShape,
                                    color = Color(0xFF2D2D2D)
                                ) {
                                    Box(contentAlignment = Alignment.Center) {
                                        Icon(Icons.Rounded.Lock, null, tint = Color.White, modifier = Modifier.size(18.dp))
                                    }
                                }

                                // Texto empurrado para a direita
                                Box(
                                    modifier = Modifier.weight(1f),
                                    contentAlignment = Alignment.Center
                                ) {
                                    Text("Swipe to unlock >>", color = Color.Gray, fontSize = 12.sp, maxLines = 1)
                                }
                            } else {
                                // Estado: DESBLOQUEADO (Bola à direita)
                                Box(
                                    modifier = Modifier.weight(1f),
                                    contentAlignment = Alignment.Center
                                ) {
                                    Text("<< Swipe to lock", color = Color.Black, fontSize = 12.sp, fontWeight = FontWeight.Bold, maxLines = 1)
                                }

                                Surface(
                                    modifier = Modifier.size(40.dp),
                                    shape = CircleShape,
                                    color = Color.White
                                ) {
                                    Box(contentAlignment = Alignment.Center) {
                                        Icon(Icons.Rounded.LockOpen, null, tint = Color.Black, modifier = Modifier.size(18.dp))
                                    }
                                }
                            }
                        }
                    }
                }

                Spacer(modifier = Modifier.height(18.dp))

                // 5. INFOCARD DE STATUS (4 colunas conforme o mockup)
                FulgoraInfoCard(modifier = Modifier.fillMaxWidth()) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        HomeStatItem(painterResource(id = AppIcons.Dashboard.Battery), "75%", "")
                        HomeStatItem(painterResource(id = AppIcons.Dashboard.Power), "3,72", "kW/100")
                        HomeStatItem(painterResource(id = AppIcons.Dashboard.Bike), "245", "km")
                        HomeStatItem(painterResource(id = AppIcons.Dashboard.Status), "Offline", "")
                    }
                }

                Spacer(modifier = Modifier.height(Dimens.PaddingMedium))
            }
        }
    }
}

@Composable
fun HomeStatItem(icon: Any, value: String, label: String) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        when (icon) {
            is androidx.compose.ui.graphics.vector.ImageVector -> Icon(icon, null, tint = GreenFresh, modifier = Modifier.size(24.dp))
            is Painter -> Icon(icon, null, tint = GreenFresh, modifier = Modifier.size(24.dp))
        }
        Spacer(modifier = Modifier.height(4.dp))
        Row(verticalAlignment = Alignment.Bottom) {
            Text(text = value, color = Color.White, fontWeight = FontWeight.Bold, fontSize = 16.sp)
            if (label.isNotEmpty()) {
                Spacer(modifier = Modifier.width(2.dp))
                Text(text = label, color = Color.Gray, fontSize = 10.sp)
            }
        }
    }
}