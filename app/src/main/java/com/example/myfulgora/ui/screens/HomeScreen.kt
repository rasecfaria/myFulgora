package com.example.myfulgora.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.myfulgora.ui.viewmodel.HomeUiState
import com.example.myfulgora.ui.viewmodel.MotaViewModel
import com.example.myfulgora.data.model.Motorcycle

@Composable
fun HomeScreen(viewModel: MotaViewModel = viewModel()) {
    val state by viewModel.uiState.collectAsState()

    // Carrega os dados ao abrir o ecrã
    LaunchedEffect(Unit) {
        viewModel.carregarMinhasMotas()
    }

    Scaffold { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text("As Minhas Motas", style = MaterialTheme.typography.headlineMedium)

            Spacer(modifier = Modifier.height(16.dp))

            when (val currentState = state) {
                is HomeUiState.Loading -> {
                    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                        CircularProgressIndicator()
                    }
                }
                is HomeUiState.Error -> {
                    Text(currentState.message, color = MaterialTheme.colorScheme.error)
                    Button(onClick = { viewModel.carregarMinhasMotas() }) {
                        Text("Tentar Novamente")
                    }
                }
                is HomeUiState.Success -> {
                    // LazyColumn é a "RecyclerView" do Compose (lista com scroll)
                    LazyColumn(verticalArrangement = Arrangement.spacedBy(16.dp)) {
                        items(currentState.motas) { mota ->
                            CardMota(mota)
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun CardMota(mota: Motorcycle) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                // Podes trocar este ícone por um de mota se tiveres
                Text("🏍️", style = MaterialTheme.typography.headlineSmall)
                Spacer(modifier = Modifier.width(8.dp))
                Text(mota.vin, style = MaterialTheme.typography.titleMedium)
            }

            HorizontalDivider(modifier = Modifier.padding(vertical = 8.dp))

            if (mota.isSynced) {
                Text("🔋 Bateria: ${mota.battery}%")
                Text("🛣️ Kms: ${mota.kilometers}")
                Text("📍 Lat/Lon: ${mota.latitude}, ${mota.longitude}")
            } else {
                Text("⚠️ Não sincronizada", color = MaterialTheme.colorScheme.error)
            }
        }
    }
}