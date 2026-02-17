package com.example.myfulgora.ui.screens.tabs

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
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
import androidx.appcompat.app.AppCompatDelegate
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.material.icons.filled.Check
import androidx.core.os.LocaleListCompat
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.ui.res.stringResource
import com.example.myfulgora.R
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.platform.LocalConfiguration

@Composable
fun SettingsScreen(
    onMenuClick: () -> Unit = {}
) {
    var isMetric by remember { mutableStateOf(true) }
    var notificationsEnabled by remember { mutableStateOf(true) }
    var lowBatteryAlertEnabled by remember { mutableStateOf(true) }

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
                // 1. TOP BAR
                FulgoraTopBar(
                    iconSize = iconSize,
                    onMenuClick = onMenuClick
                )

                Spacer(modifier = Modifier.height(Dimens.PaddingExtraLarge))

                // 2. HEADER TEXT
                Column(modifier = Modifier.fillMaxWidth()) {
                    Text(
                        text = stringResource(id = R.string.settings_title),
                        fontSize = Dimens.TextSizeHeader,
                        fontWeight = FontWeight.Bold,
                        color = Color.White
                    )
                }

                Spacer(modifier = Modifier.height(Dimens.PaddingLarge))

                // 6. PREFERENCES
                FulgoraInfoCard {
                    Text(
                        text = stringResource(id = R.string.settings_preferences),
                        color = GreenFresh,
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Medium
                    )

                    Spacer(modifier = Modifier.height(Dimens.PaddingMedium))

                    // Units Toggle
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 8.dp),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(text = "Units", color = Color.White.copy(alpha = 0.8f), fontSize = 14.sp)
                        
                        // Custom Unit Switcher
                        Row(
                            modifier = Modifier
                                .clip(RoundedCornerShape(4.dp))
                                .background(Color(0xFF2D2D2D))
                        ) {
                            Box(
                                modifier = Modifier
                                    .background(if (isMetric) GreenFresh else Color.Transparent)
                                    .clickable { isMetric = true }
                                    .padding(horizontal = 12.dp, vertical = 4.dp),
                                contentAlignment = Alignment.Center
                            ) {
                                Text(
                                    text = "km",
                                    color = if (isMetric) Color.Black else Color.Gray,
                                    fontSize = 12.sp,
                                    fontWeight = FontWeight.Bold
                                )
                            }
                            Box(
                                modifier = Modifier
                                    .background(if (!isMetric) GreenFresh else Color.Transparent)
                                    .clickable { isMetric = false }
                                    .padding(horizontal = 12.dp, vertical = 4.dp),
                                contentAlignment = Alignment.Center
                            ) {
                                Text(
                                    text = stringResource(id = R.string.settings_units_miles),
                                    color = if (!isMetric) Color.Black else Color.Gray,
                                    fontSize = 12.sp,
                                    fontWeight = FontWeight.Bold
                                )
                            }
                        }
                    }

                    HorizontalDivider(color = Color.Gray.copy(alpha = 0.1f))

                    // Notifications Switch
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 8.dp),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(text = stringResource(id = R.string.settings_notifications), color = Color.White.copy(alpha = 0.8f), fontSize = 14.sp)
                        Switch(
                            modifier = Modifier.scale(0.85f),
                            checked = notificationsEnabled,
                            onCheckedChange = { notificationsEnabled = it },
                            colors = SwitchDefaults.colors(
                                checkedThumbColor = Color.Black,
                                checkedTrackColor = GreenFresh,
                                uncheckedThumbColor = Color.Gray,
                                uncheckedTrackColor = Color(0xFF2D2D2D),
                                uncheckedBorderColor = Color.Transparent
                            )
                        )
                    }

                    HorizontalDivider(color = Color.Gray.copy(alpha = 0.1f))

                    // Low battery Alert Switch
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 8.dp),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(text = stringResource(id = R.string.settings_low_battery), color = Color.White.copy(alpha = 0.8f), fontSize = 14.sp)
                        Switch(
                            modifier = Modifier.scale(0.85f),
                            checked = lowBatteryAlertEnabled,
                            onCheckedChange = { lowBatteryAlertEnabled = it },
                            colors = SwitchDefaults.colors(
                                checkedThumbColor = Color.Black,
                                checkedTrackColor = GreenFresh,
                                uncheckedThumbColor = Color.Gray,
                                uncheckedTrackColor = Color(0xFF2D2D2D),
                                uncheckedBorderColor = Color.Transparent
                            )
                        )
                    }
                }

                Spacer(modifier = Modifier.height(Dimens.PaddingMedium))

                // 7. STYLES
                FulgoraInfoCard {
                    Column(modifier = Modifier.padding(16.dp)) {
                        Text(
                            text = stringResource(id = R.string.settings_style),
                            color = GreenFresh,
                            fontSize = 18.sp,
                            fontWeight = FontWeight.Medium
                        )

                        Spacer(modifier = Modifier.height(Dimens.PaddingMedium))

                        // --- ITEM 1: LANGUAGE (COM DROPDOWN) ---
                        LanguageSelectorRow()

                        HorizontalDivider(color = Color.Gray.copy(alpha = 0.1f))

                        // --- ITEM 2: THEME (Ainda estático por enquanto) ---
                        ThemeSelectorRow()
                    }
                }

                Spacer(modifier = Modifier.height(Dimens.ScrollBottomPadding))
            }
        }
    }
}

@Composable
fun LanguageSelectorRow() {
    // 1. Estado do Menu
    var expanded by remember { mutableStateOf(false) }

    // 2. Animação da rotação da seta
    val rotationState by animateFloatAsState(
        targetValue = if (expanded) 180f else 0f,
        label = "ArrowRotation"
    )

    // 3. Lista de Idiomas
    val languages = mapOf(
        "English" to "en",
        "Português" to "pt",
        "Chinese" to "zh"
    )

    // 4. Detetar língua atual
    val currentLocale = AppCompatDelegate.getApplicationLocales().toLanguageTags()
    val displayLanguage = when {
        currentLocale.contains("pt") -> "Português"
        currentLocale.contains("zh") -> "Chinese"
        else -> "English"
    }

    // CONTAINER PRINCIPAL (Agora é uma Coluna com animação de tamanho)
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .animateContentSize() // 👈 Faz a animação suave ao abrir/fechar
    ) {

        // --- CABEÇALHO (O que está sempre visível) ---
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .clickable { expanded = !expanded } // Alterna entre aberto/fechado
                .padding(vertical = 12.dp), // Padding do clique
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = stringResource(id = R.string.settings_language), // Cria esta string se não existir, ou usa "Language"
                color = Color.White.copy(alpha = 0.8f),
                fontSize = 14.sp
            )
            Text(
                text = displayLanguage,
                color = GreenFresh,
                fontSize = 12.sp
            )

            // Lado Direito: Seta que roda
            Icon(
                painter = painterResource(id = AppIcons.Actions.DropDown), // Confirma se tens este ícone
                contentDescription = null,
                tint = if (expanded) GreenFresh else Color.Gray,
                modifier = Modifier
                    .size(24.dp)
                    .rotate(rotationState) // 👈 Aplica a rotação animada
            )
        }

        // --- CORPO (Opções que aparecem quando expanded = true) ---
        if (expanded) {
            // Uma linha separadora subtil
            HorizontalDivider(color = Color.Gray.copy(alpha = 0.1f))

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp)
            ) {
                languages.forEach { (name, code) ->
                    val isSelected = name == displayLanguage

                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable {
                                changeAppLanguage(code)
                                expanded = false
                            }
                            .padding(vertical = 12.dp, horizontal = 8.dp),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = name,
                            color = if (isSelected) GreenFresh else Color.White,
                            fontSize = 14.sp
                        )

                        // Mostra um "Visto" se estiver selecionado
                        if (isSelected) {
                            Icon(
                                imageVector = androidx.compose.material.icons.Icons.Default.Check, // Ou usa um ícone teu
                                contentDescription = "Selected",
                                tint = GreenFresh,
                                modifier = Modifier.size(16.dp)
                            )
                        }
                    }
                }
            }
        }
    }
}

// Função Auxiliar para mudar o idioma nativamente (Android 13+)
fun changeAppLanguage(languageCode: String) {
    val appLocale = LocaleListCompat.forLanguageTags(languageCode)
    AppCompatDelegate.setApplicationLocales(appLocale)
}

@Composable
fun ThemeSelectorRow() {
    // 1. Estado de Expansão
    var expanded by remember { mutableStateOf(false) }

    // 2. Animação da Seta
    val rotationState by animateFloatAsState(
        targetValue = if (expanded) 180f else 0f,
        label = "ArrowRotation"
    )

    // 3. Opções de Tema
    // Mapeamos o Nome para o Código do Android
    val themeOptions = listOf(
        "System Default" to AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM,
        "Light Mode" to AppCompatDelegate.MODE_NIGHT_NO,
        "Dark Mode" to AppCompatDelegate.MODE_NIGHT_YES
    )

    // 4. Detetar o modo atual
    val currentMode = AppCompatDelegate.getDefaultNightMode()

    // Converter o código atual para texto bonito
    val displayTheme = when (currentMode) {
        AppCompatDelegate.MODE_NIGHT_NO -> "Light Mode"
        AppCompatDelegate.MODE_NIGHT_YES -> "Dark Mode"
        else -> "System Default"
    }

    // CONTAINER PRINCIPAL (Coluna com animação)
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .animateContentSize() // 👈 A magia da animação suave
    ) {

        // --- CABEÇALHO (Sempre visível) ---
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .clickable { expanded = !expanded }
                .padding(vertical = 12.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "Theme", // Podes usar stringResource(R.string.settings_theme)
                color = Color.White.copy(alpha = 0.8f),
                fontSize = 14.sp
            )
            Text(
                text = displayTheme,
                color = GreenFresh,
                fontSize = 12.sp
            )

            Icon(
                painter = painterResource(id = AppIcons.Actions.DropDown), // O mesmo ícone da seta
                contentDescription = null,
                tint = if (expanded) GreenFresh else Color.Gray,
                modifier = Modifier
                    .size(24.dp)
                    .rotate(rotationState)
            )
        }

        // --- LISTA DE OPÇÕES (Visível apenas se expanded = true) ---
        if (expanded) {
            HorizontalDivider(color = Color.Gray.copy(alpha = 0.1f))

            Column(modifier = Modifier.padding(vertical = 8.dp)) {
                themeOptions.forEach { (name, mode) ->
                    val isSelected = name == displayTheme

                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable {
                                // 👇 AÇÃO DE MUDANÇA DE TEMA
                                AppCompatDelegate.setDefaultNightMode(mode)
                                expanded = false
                            }
                            .padding(vertical = 12.dp, horizontal = 8.dp),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = name,
                            color = if (isSelected) GreenFresh else Color.White,
                            fontSize = 14.sp
                        )

                        if (isSelected) {
                            Icon(
                                imageVector = androidx.compose.material.icons.Icons.Default.Check,
                                contentDescription = "Selected",
                                tint = GreenFresh,
                                modifier = Modifier.size(16.dp)
                            )
                        }
                    }
                }
            }
        }
    }
}