package com.example.myfulgora.ui.screens.tabs

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.*
import kotlinx.coroutines.launch
import com.example.myfulgora.ui.components.FulgoraDrawerItem
import com.example.myfulgora.ui.theme.AppIcons
import com.example.myfulgora.ui.theme.BlackBrand
import com.example.myfulgora.ui.theme.GreenFresh

// Classe auxiliar para organizar os itens do menu
data class DrawerItemData(
    val title: String,
    val icon: Int, // R.drawable...
    val route: String
)

@Composable
fun MainScreen() {
    val navController = rememberNavController()
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val scope = rememberCoroutineScope()

    // Lista de navegação (Igual à Navbar + Extras)
    val menuItems = listOf(
        DrawerItemData("Home", AppIcons.Navbar.Home, "home"),
        DrawerItemData("Profile", AppIcons.Navbar.Home, "profile"),
        DrawerItemData("Map", AppIcons.Navbar.Map, "map"),
        DrawerItemData("Battery", AppIcons.Navbar.Battery, "battery"),
        DrawerItemData("Performance", AppIcons.Navbar.Performance, "performance"),
        DrawerItemData("Social", AppIcons.Navbar.Social, "social")
    )

    // Detetar rota atual para marcar o item selecionado
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            ModalDrawerSheet(
                drawerContainerColor = Color(0xFF1E1E1E), // Fundo Dark
                drawerContentColor = Color.White,
                modifier = Modifier.width(300.dp)
            ) {
                // --- CABEÇALHO (Igual ao Mockup) ---
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 24.dp, end = 24.dp, top = 48.dp, bottom = 24.dp)
                ) {
                    Box(
                        modifier = Modifier
                            .size(64.dp)
                            .clip(CircleShape)
                            .background(Color.Gray),
                        contentAlignment = Alignment.Center
                    ) {
                        // Placeholder Foto
                        Icon(Icons.Default.Person, null, tint = Color.White, modifier = Modifier.size(32.dp))
                    }
                    Spacer(modifier = Modifier.height(16.dp))
                    Text("Alex Rider", fontSize = 20.sp, fontWeight = FontWeight.Bold, color = Color.White)
                    Text("alex.rider@fulgora.com", fontSize = 14.sp, color = Color.Gray)
                }

                HorizontalDivider(color = Color.Gray.copy(alpha = 0.3f), modifier = Modifier.padding(horizontal = 24.dp))
                Spacer(modifier = Modifier.height(24.dp))

                // --- LISTA DE NAVEGAÇÃO ---
                menuItems.forEach { item ->
                    FulgoraDrawerItem(
                        label = item.title,
                        painter = painterResource(id = item.icon),
                        selected = currentRoute == item.route,
                        iconColor = if (currentRoute == item.route) GreenFresh else Color.Gray,
                        textColor = if (currentRoute == item.route) GreenFresh else Color.White,
                        onClick = {
                            navController.navigate(item.route) {
                                popUpTo(navController.graph.findStartDestination().id) { saveState = true }
                                launchSingleTop = true
                                restoreState = true
                            }
                            scope.launch { drawerState.close() }
                        }
                    )
                }

                Spacer(modifier = Modifier.weight(1f)) // Empurra o resto para o fundo

                // --- RODAPÉ (Settings & Logout) ---
                FulgoraDrawerItem(
                    label = "Settings",
                    painter = painterResource(id = AppIcons.Navbar.Home), // Usa um ícone de Settings se tiveres
                    onClick = { scope.launch { drawerState.close() } },
                    iconColor = Color.Gray
                )

                FulgoraDrawerItem(
                    label = "Logout",
                    painter = painterResource(id = AppIcons.Navbar.Home), // Usa um ícone de Logout se tiveres
                    onClick = { /* Lógica Logout */ },
                    textColor = Color(0xFFFF4444),
                    iconColor = Color(0xFFFF4444)
                )

                Spacer(modifier = Modifier.height(32.dp))
            }
        }
    ) {
        // --- CONTEÚDO PRINCIPAL (Scaffold) ---
        Scaffold(
            bottomBar = {
                NavigationBar(
                    containerColor = BlackBrand,
                    contentColor = Color.Gray
                ) {
                    val bottomItems = listOf("Map", "Battery", "Home", "Social", "Performance")
                    // Lista auxiliar de ícones para a navbar
                    val bottomIcons = listOf(
                        AppIcons.Navbar.Map,
                        AppIcons.Navbar.Battery,
                        AppIcons.Navbar.Home,
                        AppIcons.Navbar.Social,
                        AppIcons.Navbar.Performance
                    )

                    bottomItems.forEachIndexed { index, item ->
                        val isSelected = currentRoute == item.lowercase()
                        NavigationBarItem(
                            icon = {
                                Icon(
                                    painter = painterResource(id = bottomIcons[index]),
                                    contentDescription = item,
                                    modifier = Modifier.size(24.dp)
                                )
                            },
                            label = { Text(item) },
                            selected = isSelected,
                            alwaysShowLabel = false,
                            onClick = {
                                navController.navigate(item.lowercase()) {
                                    popUpTo(navController.graph.findStartDestination().id) { saveState = true }
                                    launchSingleTop = true
                                    restoreState = true
                                }
                            },
                            colors = NavigationBarItemDefaults.colors(
                                selectedIconColor = GreenFresh,
                                selectedTextColor = GreenFresh,
                                indicatorColor = Color.Transparent,
                                unselectedIconColor = Color.Gray,
                                unselectedTextColor = Color.Gray
                            )
                        )
                    }
                }
            }
        ) { innerPadding ->
            NavHost(
                navController = navController,
                startDestination = "home",
                modifier = Modifier.padding(innerPadding)
            ) {
                composable("profile") {
                    ProfileScreen(onMenuClick = { scope.launch { drawerState.open() } })
                }
                composable("map") { MapScreen() }
                composable("battery") {
                    BatteryScreen(onMenuClick = { scope.launch { drawerState.open() } })
                }
                composable("home") {
                    HomeScreen(onMenuClick = { scope.launch { drawerState.open() } })
                }
                composable("social") {
                    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                        Text("Social Screen", color = Color.White)
                    }
                }
                composable("performance") {
                    PerformanceScreen(onMenuClick = { scope.launch { drawerState.open() } })
                }
            }
        }
    }
}