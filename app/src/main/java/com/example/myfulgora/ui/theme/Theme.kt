package com.example.myfulgora.ui.theme

import android.app.Activity
import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowCompat

// Esquema Escuro (O oficial da myFulgora)
private val DarkColorScheme = darkColorScheme(
    primary = GreenFresh,        // Botões e destaques
    onPrimary = BlackBrand,      // Texto em cima do botão verde
    secondary = GreenDeep,       // Elementos secundários
    background = BlackBrand,     // Fundo preto absoluto
    surface = BlackBrand,        // Fundo de cartões (podes usar GreenDeep muito escuro se preferires)
    onBackground = White,        // Texto branco no fundo preto
    onSurface = White,
    error = RedError
)

// Esquema Claro (Caso um dia precises, mas a app é dark-first)
private val LightColorScheme = lightColorScheme(
    primary = GreenDeep,
    onPrimary = White,
    secondary = GreenFresh,
    background = White,
    surface = White
    // ...
)

@Composable
fun MyFulgoraTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    // Dynamic color desligado para FORÇAR as cores da tua marca
    dynamicColor: Boolean = false,
    content: @Composable () -> Unit
) {
    val colorScheme = when {
        dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
            val context = LocalContext.current
            if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
        }
        // Forçamos sempre o Dark Scheme se quiseres que a app seja sempre escura,
        // ou deixamos o utilizador decidir com 'darkTheme -> DarkColorScheme'
        true -> DarkColorScheme // <--- DICA: Mudei isto para 'true' para testares sempre com as cores certas
        else -> LightColorScheme
    }

    val view = LocalView.current
    if (!view.isInEditMode) {
        SideEffect {
            val window = (view.context as Activity).window
            window.statusBarColor = colorScheme.background.toArgb()
            WindowCompat.getInsetsController(window, view).isAppearanceLightStatusBars = !true // Ícones da barra brancos
        }
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}