package com.example.myfulgora.ui.screens.tabs

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Autorenew
import androidx.compose.material.icons.rounded.Bolt
import androidx.compose.material.icons.rounded.Favorite
import androidx.compose.material.icons.rounded.Thermostat
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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.myfulgora.ui.components.FulgoraBackground
import com.example.myfulgora.ui.components.FulgoraTopBar
import com.example.myfulgora.ui.theme.AppIcons
import com.example.myfulgora.ui.theme.CardBackgroundColor
import com.example.myfulgora.ui.theme.Dimens
import com.example.myfulgora.ui.theme.GreenFresh

@Composable
fun BatteryScreen(
    onMenuClick: () -> Unit = {}
) {
    FulgoraBackground {
        BoxWithConstraints(modifier = Modifier.fillMaxSize()) {

            val screenW = maxWidth
            val iconSize = screenW * Dimens.IconScaleRatio
            val paddingSide = screenW * Dimens.SideMarginRatio

            val scrollState = rememberScrollState()

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .verticalScroll(scrollState)
                    .padding(horizontal = paddingSide)
                    .padding(top = Dimens.TopPadding),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                // 1. CABEÇALHO PARTILHADO
                FulgoraTopBar(
                    title = "Hi, Alex!",
                    subtitle = "Ready to ride?",
                    iconSize = iconSize,
                    onMenuClick = onMenuClick
                )

                Spacer(modifier = Modifier.height(Dimens.PaddingExtraLarge))

                // 2. TÍTULO
                Text(
                    text = "Battery Info",
                    textAlign = TextAlign.Start,
                    color = Color.White,
                    fontSize = Dimens.TextSizeHeader,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.fillMaxWidth()
                )

                Spacer(modifier = Modifier.height(Dimens.PaddingExtraLarge))

                // 3. BATERIA GRANDE + INFO
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(IntrinsicSize.Min),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    // Ícone da Bateria Grande
                    BigBatteryIndicator(
                        modifier = Modifier
                            .weight(0.35f)
                            .fillMaxHeight()
                    )

                    Spacer(modifier = Modifier.width(Dimens.PaddingMedium))

                    // O Cartão de Texto
                    Box(modifier = Modifier.weight(0.65f)) {
                        BatteryInfoCard()
                    }
                }

                Spacer(modifier = Modifier.height(48.dp))

                // 4. GRELHA DE ESTATÍSTICAS
                Column(verticalArrangement = Arrangement.spacedBy(Dimens.PaddingMedium)) {
                    // Linha 1
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(Dimens.PaddingMedium)
                    ) {
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
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(Dimens.PaddingMedium)
                    ) {
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

                // 5. MARGEM FINAL SCROLL
                Spacer(modifier = Modifier.height(Dimens.ScrollBottomPadding))
            }
        }
    }
}

@Composable
fun BigBatteryIndicator(modifier: Modifier = Modifier) {
    Box(
        modifier = modifier,
        contentAlignment = Alignment.Center
    ) {
        Icon(
            painter = painterResource(id = AppIcons.Battery.BigBatteryCharging),
            contentDescription = "Battery Status",
            tint = GreenFresh,
            modifier = Modifier
                .fillMaxSize()
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
            containerColor = CardBackgroundColor
        )
    ) {
        Column(
            modifier = Modifier.padding(Dimens.SpacingSmall)
        ) {
            // Topo
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = Dimens.PaddingMedium)
            ) {
                Text(text = "Charging", color = GreenFresh, fontWeight = FontWeight.Bold)

                Text(
                    text = "Time left",
                    color = Color.Gray,
                    fontSize = Dimens.TextSizeSmall
                )

                Text(
                    text = "03h 7m",
                    color = Color.White,
                    fontSize = 32.sp,
                    fontWeight = FontWeight.Bold
                )
            }

            Spacer(modifier = Modifier.height(Dimens.SpacingSmall))

            // Linha de Ícones
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                // Item 1
                Column(
                    modifier = Modifier
                        .weight(1f)
                        .aspectRatio(1f)
                        .padding(start = Dimens.PaddingMedium),
                    horizontalAlignment = Alignment.Start,
                    verticalArrangement = Arrangement.Center
                ) {
                    Icon(
                        painter = painterResource(id = AppIcons.Battery.Battery),
                        contentDescription = "Battery",
                        tint = GreenFresh,
                        modifier = Modifier.size(24.dp)
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    Text("78%", color = Color.Gray, fontSize = Dimens.TextSizeSmall, fontWeight = FontWeight.Medium)
                }

                // Item 2 (Centro)
                Column(
                    modifier = Modifier.weight(1f).aspectRatio(1f),
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
                    Text("92 km", color = Color.Gray, fontSize = Dimens.TextSizeSmall, fontWeight = FontWeight.Medium)
                }

                // Item 3 (Direita)
                Column(
                    modifier = Modifier.weight(1f).aspectRatio(1f),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    Icon(
                        painter = painterResource(id = AppIcons.Battery.Charging),
                        contentDescription = "Charging",
                        tint = GreenFresh,
                        modifier = Modifier.size(24.dp)
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    Text("Charging", color = Color.Gray, fontSize = Dimens.TextSizeSmall, fontWeight = FontWeight.Medium)
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
            .background(CardBackgroundColor, RoundedCornerShape(16.dp))
            .padding(Dimens.PaddingMedium)
    ) {
        Icon(
            imageVector = icon,
            contentDescription = null,
            tint = GreenFresh,
            modifier = Modifier.size(24.dp)
        )
        Spacer(modifier = Modifier.height(Dimens.PaddingMedium))
        Text(text = value, color = Color.White, fontWeight = FontWeight.Bold, fontSize = 16.sp)
        Text(text = title, color = Color.Gray, fontSize = Dimens.TextSizeSmall)
    }
}