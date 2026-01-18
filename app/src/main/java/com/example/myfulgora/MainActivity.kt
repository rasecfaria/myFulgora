package com.example.myfulgora

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
// O import abaixo deve corresponder à pasta onde criaste o HomeScreen.
// Se ficar vermelho, apaga a linha e volta a escrever "HomeScreen()" lá em baixo para o Android Studio sugerir o import.
import com.example.myfulgora.ui.screens.HomeScreen
import com.example.myfulgora.ui.theme.MyFulgoraTheme
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.myfulgora.ui.screens.SplashScreen
import com.example.myfulgora.ui.screens.OnboardingScreen
import com.example.myfulgora.ui.screens.LoginScreen

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {
            MyFulgoraTheme {
                // Sistema de Navegação simples
                val navController = rememberNavController()

                // Define as rotas (caminhos) da app
                NavHost(navController = navController, startDestination = "splash") {

                    // 1. Ecrã de Splash (Novo)
                    composable("splash") {
                        SplashScreen(
                            onSplashFinished = {
                                // Quando passarem 2 segundos, vai para o Onboarding
                                navController.navigate("onboarding") {
                                    popUpTo("splash") { inclusive = true } // Remove a splash da memória
                                }
                            }
                        )
                    }

                    // 2. Ecrã de Onboarding
                    composable("onboarding") {
                        OnboardingScreen(
                            onFinish = {
                                navController.navigate("login") {
                                    // Não deixa voltar ao onboarding depois de acabar
                                    popUpTo("onboarding") { inclusive = true }
                                }
                            }
                        )
                    }

                    // 3. Ecrã de Login
                    composable("login") {
                        LoginScreen(
                            onLoginSuccess = {
                                navController.navigate("home") {
                                    popUpTo("login") { inclusive = true }
                                }
                            }
                        )
                    }

                    // 4. Ecrã Home
                    composable("home") {
                        HomeScreen()
                    }
                }
            }
        }
    }
}