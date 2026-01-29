package com.example.myfulgora.ui.screens.performance

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ChevronLeft
import androidx.compose.material.icons.rounded.ChevronRight
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.myfulgora.R
import com.example.myfulgora.ui.components.FulgoraBackground
import com.example.myfulgora.ui.components.FulgoraInfoCard
import com.example.myfulgora.ui.components.FulgoraTopBar
import com.example.myfulgora.ui.theme.Dimens
import com.example.myfulgora.ui.theme.GrayDark
import com.example.myfulgora.ui.theme.GreenFresh
import com.example.myfulgora.ui.theme.GrayLight

@Composable
fun PerformanceScreen() {
    FulgoraBackground {
        BoxWithConstraints(modifier = Modifier.fillMaxSize()) {

            val screenW = maxWidth
            val screenH = maxHeight

            // Cálculos dinâmicos
            val iconSize = screenW * Dimens.IconScaleRatio
            val bikeHeight = screenH * Dimens.BikeHeightRatio
            val paddingSide = screenW * Dimens.SideMarginRatio

            val iconSizeStandard = screenW * 0.07f // Ícones normais
            val iconSizeSmall = screenW * 0.05f    // Ícones pequenos

            val scrollState = rememberScrollState()

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .verticalScroll(scrollState)
                    .padding(horizontal = paddingSide)
                    .padding(top = Dimens.TopPadding) // 16.dp fixo no topo
            ) {

                // 1. HEADER (Agora é um item normal da lista)
                // Quando dás scroll para baixo, isto desaparece naturalmente.
                FulgoraTopBar(
                    iconSize = iconSize
                )

                Spacer(modifier = Modifier.height(Dimens.PaddingExtraLarge))

                // 2. TÍTULO E SUBTÍTULO
                Column {
                    Text(
                        text = "Maintenance",
                        fontSize = Dimens.TextSizeHeader,
                        fontWeight = FontWeight.Bold,
                        color = Color.White
                    )
                    Text(
                        text = "S10 Bike Name",
                        fontSize = Dimens.TextSizeNormal,
                        color = GreenFresh
                    )
                }

                Spacer(modifier = Modifier.height(Dimens.PaddingMedium))

                // 3. ZONA DA MOTO
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(bikeHeight)
                        .background(Color.Transparent),
                    contentAlignment = Alignment.Center
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.mota_crop),
                        contentDescription = "Mota",

                        modifier = Modifier.fillMaxSize(0.85f)
                    )
                    Icon(Icons.Rounded.ChevronLeft, contentDescription = null, tint = Color.Gray, modifier = Modifier.align(Alignment.CenterStart).size(iconSizeStandard))
                    Icon(Icons.Rounded.ChevronRight, contentDescription = null, tint = GreenFresh, modifier = Modifier.align(Alignment.CenterEnd).size(iconSizeStandard))
                }

                Spacer(modifier = Modifier.height(Dimens.PaddingLarge))

                // 4. GRELHA DE CARTÕES
                Row(modifier = Modifier.fillMaxWidth()) {
                    FulgoraInfoCard(modifier = Modifier.weight(1f)) {
                        // 1. Criamos uma Box que ocupa a largura toda
                        Box(modifier = Modifier.fillMaxWidth()) {

                            // 2. O TEU CONTEÚDO (Texto)
                            // Agrupamos numa Column para ficarem uns debaixo dos outros
                            Column(
                                // Damos um padding à direita na Column para o texto não ficar por cima do ícone
                                modifier = Modifier.padding(end = Dimens.PaddingMedium)
                            ) {
                                Text("Tyre Pressure", color = GrayLight, fontSize = Dimens.TextSizeSubTitle)
                                Text("Normal", color = GreenFresh, fontSize = Dimens.TextSizeSmall)

                                Spacer(modifier = Modifier.height(Dimens.SpacingSmallPlus))

                                Text("Front 32 PSI", color = Color.White, fontWeight = FontWeight.Bold, fontSize = Dimens.TextSizeNormal)
                                Text("Rear 32 PSI", color = Color.White, fontWeight = FontWeight.Bold, fontSize = Dimens.TextSizeNormal)
                            }

                            // 3. O ÍCONE (Canto Superior Direito)
                            Icon(
                                // Substitui pelo teu ícone (ex: R.drawable.ic_pneu)
                                painter = painterResource(id = R.drawable.arrow_bike_right),
                                contentDescription = null,
                                tint = GreenFresh, // Ou Color.Gray, conforme o design
                                modifier = Modifier
                                    .size(24.dp) // Tamanho do ícone
                                    .align(Alignment.TopEnd) // 👈 O SEGREDO: Alinha ao topo à direita
                            )
                        }
                    }

                    Spacer(modifier = Modifier.width(Dimens.PaddingMedium))

                    FulgoraInfoCard(modifier = Modifier.weight(1f)) {
                        Text("Next Service", color = Color.Gray, fontSize = Dimens.TextSizeSmall)
                        Spacer(modifier = Modifier.height(Dimens.SpacingSmall))
                        Text("320 km", color = Color.White, fontWeight = FontWeight.Bold, fontSize = Dimens.TextSizeNormal)
                    }
                }

                Spacer(modifier = Modifier.height(Dimens.PaddingSmall))

                // 5. CARTÃO DE PERFORMANCE
                FulgoraInfoCard {
                    Text("Performance", color = Color.Gray, fontSize = Dimens.TextSizeSmall)
                    Spacer(modifier = Modifier.height(Dimens.SpacingSmall))
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Column {
                            Text("Energy", color = Color.Gray, fontSize = Dimens.TextSizeSmall)
                            Text("0.9 kWh", color = Color.White, fontWeight = FontWeight.Bold)
                        }
                        Column {
                            Text("Avg Speed", color = Color.Gray, fontSize = Dimens.TextSizeSmall)
                            Text("42 km/h", color = Color.White, fontWeight = FontWeight.Bold)
                        }
                    }
                }
                Spacer(modifier = Modifier.height(Dimens.PaddingSmall))

                //RECENT TRIPS
                FulgoraInfoCard {
                    Text("Recent Trips", color = Color.Gray, fontSize = Dimens.TextSizeSmall)
                    Spacer(modifier = Modifier.height(Dimens.SpacingSmall))
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Column {
                            Text("Energy", color = Color.Gray, fontSize = Dimens.TextSizeSmall)
                            Text("0.9 kWh", color = Color.White, fontWeight = FontWeight.Bold)
                        }
                        Column {
                            Text("Avg Speed", color = Color.Gray, fontSize = Dimens.TextSizeSmall)
                            Text("42 km/h", color = Color.White, fontWeight = FontWeight.Bold)
                        }
                    }
                }
                Spacer(modifier = Modifier.height(Dimens.PaddingSmall))

                //SUSTAINABILITY
                FulgoraInfoCard {
                    Text("Sustainability", color = Color.Gray, fontSize = Dimens.TextSizeSmall)
                    Spacer(modifier = Modifier.height(Dimens.SpacingSmall))
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Column {
                            Text("Co2 emissions avoided", color = Color.Gray, fontSize = Dimens.TextSizeSmall)
                            Text("Based on your recent rides", color = Color.White, fontWeight = FontWeight.Bold)
                        }
                        Column {
                            Text("12.4 kg", color = Color.White, fontWeight = FontWeight.Bold)
                        }
                    }
                }

                // Espaço final para não bater na barra de navegação
                Spacer(modifier = Modifier.height(Dimens.ScrollBottomPadding))
            }
        }
    }
}