package com.example.myfulgora.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.myfulgora.ui.theme.GreenFresh
import com.example.myfulgora.ui.theme.CardBackgroundColor
import androidx.compose.ui.graphics.painter.Painter



// --- 1. O CABEÇALHO REUTILIZÁVEL (TOP BAR) ---
@Composable
fun FulgoraTopBar(
    title: String = "Hi, Alex!",
    subtitle: String = "Ready to ride?",
    iconSize: Dp = 24.dp, // 👈 Novo parâmetro com valor por defeito
    onNotificationClick: () -> Unit = {},
    onMenuClick: () -> Unit = {}
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        // Textos
        Column {
            Text(text = title, color = Color.White, fontSize = 18.sp, fontWeight = FontWeight.Bold)
            Text(text = subtitle, color = Color.Gray, fontSize = 14.sp)
        }

        // Ícones Dinâmicos
        Row {
            Icon(
                imageVector = Icons.Default.Notifications,
                contentDescription = "Notifications",
                tint = Color.White,
                modifier = Modifier
                    .size(iconSize) // 👈 Usa o tamanho calculado
                    .clickable { onNotificationClick() }
            )

            Spacer(modifier = Modifier.width(16.dp))

            Icon(
                imageVector = Icons.Default.Menu,
                contentDescription = "Menu",
                tint = Color.White,
                modifier = Modifier
                    .size(iconSize) // 👈 Usa o tamanho calculado
                    .clickable { onMenuClick() }
            )
        }
    }
}

// --- 2. O CARTÃO CINZENTO BASE (INFO CARD) ---
// Este é o "contentor" que vamos usar para Pneus, Performance, etc.
@Composable
fun FulgoraInfoCard(
    modifier: Modifier = Modifier,
    backgroundColor: Color = CardBackgroundColor,
    content: @Composable ColumnScope.() -> Unit
) {
    Card(
        modifier = modifier.fillMaxWidth(),
        shape = RoundedCornerShape(16.dp), // Cantos arredondados consistentes
        colors = CardDefaults.cardColors(containerColor = backgroundColor),
        elevation = CardDefaults.cardElevation(defaultElevation = 0.dp)
    ) {
        Column(
            modifier = Modifier.padding(16.dp) // Padding interno padrão
        ) {
            content()
        }
    }
}


@Composable
fun FulgoraDrawerItem(
    label: String,
    painter: Painter, // 👈 Agora aceita os teus ícones (R.drawable...)
    onClick: () -> Unit,
    selected: Boolean = false,
    textColor: Color = Color.White,
    iconColor: Color = Color.White
) {
    NavigationDrawerItem(
        icon = {
            Icon(
                painter = painter,
                contentDescription = null,
                tint = iconColor,
                modifier = Modifier.size(24.dp) // Tamanho fixo para alinhar
            )
        },
        label = { Text(label, color = textColor, fontSize = 16.sp) },
        selected = selected,
        onClick = onClick,
        colors = NavigationDrawerItemDefaults.colors(
            unselectedContainerColor = Color.Transparent,
            selectedContainerColor = Color(0xFF2D2D2D) // Cinza escuro para seleção
        ),
        modifier = Modifier.padding(horizontal = 12.dp, vertical = 4.dp)
    )
}