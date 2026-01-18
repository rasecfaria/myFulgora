package com.example.myfulgora.ui.screens

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
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.myfulgora.R // Confirma se este R é do teu pacote
import kotlinx.coroutines.launch

// Estrutura dos dados de cada página
data class OnboardingPage(
    val title: String,
    val description: String,
    val imageRes: Int
)

@Composable
fun OnboardingScreen(onFinish: () -> Unit) {
    // Definimos as cores do design (Fundo Escuro com Verde)
    val darkGradient = Brush.verticalGradient(
        colors = listOf(
            Color(0xFF0F172A), // Azul escuro quase preto (topo)
            Color(0xFF1E293B), // Um pouco mais claro (meio)
            Color(0xFF052e16)  // Verde muito escuro (fundo)
        )
    )

    // O conteúdo das 3 páginas de swipe
    val pages = listOf(
        OnboardingPage(
            "Drive Green,\nEarn Rewards",
            "Conduz de forma sustentável, ganha E-Points e troca por benefícios reais. Quanto mais eficiente fores, mais ganhas.",
            R.drawable.img_onboarding_1 // Tens de ter esta imagem na pasta drawable
        ),
        OnboardingPage(
            "All Bikes,\nOne App",
            "Gere todas as tuas motas elétricas num só lugar. Monitoriza bateria, autonomia e estado num dashboard universal.",
            R.drawable.img_onboarding_2
        ),
        OnboardingPage(
            "Join the\nCommunity",
            "Partilha rotas, conquistas e melhorias. Compete com amigos e sobe nos rankings da comunidade verde.",
            R.drawable.img_onboarding_3
        )
    )

    val pagerState = rememberPagerState(pageCount = { pages.size })
    val scope = rememberCoroutineScope()

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(darkGradient) // O fundo degradê
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .statusBarsPadding(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            // LOGO NO TOPO (myFulgora)
            Spacer(modifier = Modifier.height(20.dp))
            Row(verticalAlignment = Alignment.CenterVertically) {
                // Aqui podes por o teu logo pequeno
                Image(
                    painter = painterResource(R.drawable.logo_noname),
                    contentDescription = "Logo",
                    modifier = Modifier.size(32.dp)
                    // Nota: Não usamos 'tint' aqui para manter as cores originais
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    text = "myFulgora",
                    color = Color.White,
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold
                )
            }

            // O CARROSSEL (PAGER)
            HorizontalPager(
                state = pagerState,
                modifier = Modifier
                    .weight(1f) // Ocupa o espaço todo disponível no meio
                    .fillMaxWidth()
            ) { position ->
                OnboardingPageContent(page = pages[position])
            }

            // INDICADORES (BOLINHAS)
            Row(
                modifier = Modifier
                    .height(50.dp)
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.Center
            ) {
                repeat(pages.size) { iteration ->
                    val color = if (pagerState.currentPage == iteration) Color(0xFF4CAF50) else Color.Gray
                    Box(
                        modifier = Modifier
                            .padding(4.dp)
                            .clip(CircleShape)
                            .background(color)
                            .size(if (pagerState.currentPage == iteration) 10.dp else 8.dp)
                    )
                }
            }

            // BOTÃO "COMEÇAR" (Só aparece na última página)
            Box(modifier = Modifier.padding(24.dp)) {
                if (pagerState.currentPage == pages.size - 1) {
                    Button(
                        onClick = onFinish,
                        colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF4CAF50)),
                        modifier = Modifier.fillMaxWidth().height(50.dp)
                    ) {
                        Text("Começar", fontSize = 18.sp, color = Color.White)
                    }
                } else {
                    // Botão "Next" invisível ou texto simples para avançar
                    TextButton(
                        onClick = {
                            scope.launch {
                                pagerState.animateScrollToPage(pagerState.currentPage + 1)
                            }
                        }
                    ) {
                        Text("Seguinte", color = Color.White.copy(alpha = 0.7f))
                    }
                }
            }

            Spacer(modifier = Modifier.height(20.dp)) // Espaço extra no fundo
        }
    }
}

@Composable
fun OnboardingPageContent(page: OnboardingPage) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(32.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        // IMAGEM CENTRAL
        Image(
            painter = painterResource(id = page.imageRes),
            contentDescription = null,
            modifier = Modifier
                .size(250.dp) // Ajusta o tamanho conforme a imagem
                .padding(bottom = 32.dp),
            contentScale = ContentScale.Fit
        )

        // TÍTULO
        Text(
            text = page.title,
            color = Color.White,
            fontSize = 28.sp,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center,
            lineHeight = 36.sp
        )

        Spacer(modifier = Modifier.height(16.dp))

        // DESCRIÇÃO
        Text(
            text = page.description,
            color = Color(0xFFB0BEC5), // Cinzento claro
            fontSize = 14.sp,
            textAlign = TextAlign.Center,
            lineHeight = 22.sp
        )
    }
}