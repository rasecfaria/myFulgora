package com.example.myfulgora.ui.screens.auth

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.myfulgora.R
import com.example.myfulgora.ui.components.FulgoraBackground
import kotlinx.coroutines.launch

// Modelo de dados de cada página
data class OnboardingPage(
    val title: String,
    val description: String,
    val imageRes: Int
)

@Composable
fun OnboardingScreen(onFinish: () -> Unit) {

    val pages = listOf(
        OnboardingPage(
            "All Bikes,\nOne App",
            "Manage all your electric bikes in one place.",
            R.drawable.img_onboarding_2
        ),
        OnboardingPage(
            "Ride Safe,\nRide Smart",
            "Track battery, range, and bike status in a smart dashboard for safer rides.",
            R.drawable.img_onboarding_2
        ),
        OnboardingPage(
            "Drive Green,\nEarn Rewards",
            "Ride sustainably, earn E-Points, and redeem them for real rewards. The more efficient you ride, the more you earn.",
            R.drawable.img_onboarding_1
        ),
        OnboardingPage(
            "Join the\nCommunity",
            "Share routes, achievements, and improvements. Compete with friends and climb the green community rankings.",
            R.drawable.img_onboarding_3
        )
    )

    val pagerState = rememberPagerState(pageCount = { pages.size })
    val scope = rememberCoroutineScope()

    FulgoraBackground {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .statusBarsPadding()      // Protege o topo (relógio/bateria)
                .navigationBarsPadding(), // Protege o fundo (botões do Android)
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            // --- TOPO: LOGO ---
            // Adicionei um Spacer para dar "ar" no topo como no mockup
            Spacer(modifier = Modifier.height(20.dp))

//            Image(
//                painter = painterResource(R.drawable.logo_app),
//                contentDescription = "Logo",
//                modifier = Modifier
//                    .width(280.dp)
//                    .aspectRatio(160f / 50f), // mantém proporção original
//                contentScale = ContentScale.Fit
//            )

            // --- CENTRO: CARROSSEL (PAGER) ---
            // O weight(1f) empurra o topo para cima e o rodapé para baixo, ocupando o meio
            HorizontalPager(
                state = pagerState,
                modifier = Modifier
                    .weight(1f)
                    .fillMaxWidth()
            ) { pageIndex ->
                OnboardingPageContent(pages[pageIndex])
            }

            // --- RODAPÉ: INDICADORES E BOTÃO ---
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 24.dp)
                    .padding(bottom = 50.dp), // Espaço extra no fundo para não colar à borda
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                // INDICATORS (Bolinhas)
                // INDICATORS (Bolinhas mais subtis)
                Row(
                    modifier = Modifier.padding(bottom = 12.dp),
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    repeat(pages.size) { index ->
                        val isSelected = pagerState.currentPage == index
                        Box(
                            modifier = Modifier
                                .padding(4.dp)
                                // 1. TAMANHO REDUZIDO:
                                .size(if (isSelected) 8.dp else 6.dp)
                                .clip(CircleShape)
                                .background(
                                    if (isSelected)
                                        Color(0xFF00D84F) // Verde vivo na selecionada
                                    else
                                        Color.White.copy(alpha = 0.2f) // 2. TRANSPARÊNCIA: Cinzento muito subtil nas outras
                                )
                        )
                    }
                }

                // BOTÃO ou TEXTO NEXT
                if (pagerState.currentPage == pages.lastIndex) {
                    Button(
                        onClick = onFinish,
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color(0xFF00D84F) // Verde Fresh
                        ),
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(50.dp)
                    ) {
                        Text(
                            "Get Started",
                            fontSize = 18.sp,
                            color = Color.White,
                            fontWeight = FontWeight.Bold
                        )
                    }
                } else {
                    // Botão Texto Simples "Next" (Seguinte)
                    TextButton(
                        onClick = {
                            scope.launch {
                                pagerState.animateScrollToPage(pagerState.currentPage + 1)
                            }
                        },
                        modifier = Modifier.height(50.dp) // Mantém a altura consistente com o botão
                    ) {
                        Text(
                            "Next",
                            color = Color.White.copy(alpha = 0.9f),
                            fontSize = 16.sp
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun OnboardingPageContent(page: OnboardingPage) {
    BoxWithConstraints(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 32.dp)
    ) {
        val availableHeight = maxHeight

        val titleFontSize = (availableHeight.value * 0.045f)
            .coerceIn(22f, 30f)
            .sp

        val descriptionFontSize = (availableHeight.value * 0.022f)
            .coerceIn(13f, 17f)
            .sp

        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Spacer(modifier = Modifier.height(availableHeight * 0.08f))

            Image(
                painter = painterResource(page.imageRes),
                contentDescription = null,
                modifier = Modifier
                    .fillMaxWidth(0.9f)
                    .height(availableHeight * 0.35f),
                contentScale = ContentScale.Fit
            )

            Spacer(modifier = Modifier.height(availableHeight * 0.04f))

            Text(
                text = page.title,
                color = Color.White,
                fontSize = titleFontSize,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center,
                lineHeight = (titleFontSize.value * 1.25f).sp
            )

            Spacer(modifier = Modifier.height(availableHeight * 0.02f))

            Text(
                text = page.description,
                color = Color(0xFFB0BEC5),
                fontSize = descriptionFontSize,
                textAlign = TextAlign.Center,
                lineHeight = (descriptionFontSize.value * 1.4f).sp
            )

            Spacer(modifier = Modifier.weight(1f))
        }
    }
}


