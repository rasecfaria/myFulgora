package com.example.myfulgora.ui.screens

import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.myfulgora.ui.screens.tabs.HomeScreen
import com.example.myfulgora.ui.theme.BlackBrand
import com.example.myfulgora.ui.theme.GreenFresh
import com.example.myfulgora.ui.screens.auth.ForgotPasswordScreen

@Composable
fun MainScreen() {
    val navController = rememberNavController()

    Scaffold(
        bottomBar = {
            NavigationBar(
                containerColor = BlackBrand, // Fundo preto oficial
                contentColor = Color.Gray,
                modifier = Modifier
                    .fillMaxWidth()
                    .windowInsetsPadding(WindowInsets.navigationBars) // Adiciona espaço para os botões do sistema
                    .height(56.dp),
            ) {
                // Definimos os itens da navegação
                val items = listOf("Map", "Battery", "Home", "Social", "Maintence")
                val icons = listOf(
                    Icons.Default.LocationOn, // Representa "Location"
                    Icons.Default.BatteryChargingFull, // Representa "Battery/Performance"
                    Icons.Default.Home,     // Representa "Home"
                    Icons.Default.Group,    // Representa "Community"
                    Icons.Default.ElectricBike  // Representa "Maintenance/Profile"
                )

                // Variável para saber qual está selecionado
                var selectedItem by remember { mutableIntStateOf(2)}

                items.forEachIndexed { index, item ->
                    NavigationBarItem(
                        icon = { Icon(icons[index], contentDescription = item) },
                        label = { Text(item) },
                        alwaysShowLabel = false,
                        selected = selectedItem == index,
                        onClick = {
                            selectedItem = index
                            navController.navigate(item.lowercase()) {
                                popUpTo("home")
                                launchSingleTop = true
                            }
                        },
                        colors = NavigationBarItemDefaults.colors(
                            selectedIconColor = GreenFresh, // O ícone fica verde elétrico
                            selectedTextColor = GreenFresh,
                            indicatorColor = Color.Transparent, // Remove a "bolha" de seleção padrão
                            unselectedIconColor = Color.Gray,
                            unselectedTextColor = Color.Gray
                        )
                    )
                }
            }
        }
    ) { innerPadding ->
        // O Espaço onde os ecrãs mudam
        NavHost(
            navController = navController,
            startDestination = "home",
            modifier = Modifier.padding(innerPadding)
        ) {
            composable("map") { MapScreen() }
            composable("battery") { BatteryScreen() }
            composable("home") { HomeScreen() }
            composable("social") { Text("Ecrã Social (Em construção)", color = Color.White) }
            composable("maintence") { Text("Ecrã Perfil (Em construção)", color = Color.White) }
            composable("forgot_password") {
                ForgotPasswordScreen(
                    onNavigateBack = { navController.popBackStack() },
                    onLoginAfterReset = {
                        navController.navigate("login") {
                            popUpTo("home") { inclusive = true }
                        }
                    }
                )
            }
        }
    }
}