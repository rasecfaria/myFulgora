package com.example.myfulgora.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myfulgora.data.model.Motorcycle
import com.example.myfulgora.data.remote.MotaClient
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

// O estado agora guarda uma LISTA de motas, não apenas uma
sealed class HomeUiState {
    object Loading : HomeUiState()
    data class Success(val motas: List<Motorcycle>) : HomeUiState()
    data class Error(val message: String) : HomeUiState()
}

class MotaViewModel : ViewModel() {

    private val client = MotaClient()
    private val _uiState = MutableStateFlow<HomeUiState>(HomeUiState.Loading)
    val uiState = _uiState.asStateFlow()

    // Os VINs que estavam no projeto antigo
    private val meusVins = listOf("MOTO999999999999", "AJP1RB5SXLB123456", "AJP7XZ8MWQ2Y1R3K")

    fun carregarMinhasMotas() {
        viewModelScope.launch {
            _uiState.value = HomeUiState.Loading

            val listaMotas = mutableListOf<Motorcycle>()
            var erroEncontrado = false

            // Vamos buscar os dados de cada mota da lista
            for (vin in meusVins) {
                val resposta = client.getMotaInfo(vin)

                if (resposta != null) {
                    listaMotas.add(
                        Motorcycle(
                            vin = vin,
                            battery = resposta.battery,
                            kilometers = resposta.kilometers,
                            latitude = resposta.latitude,
                            longitude = resposta.longitude,
                            isSynced = true
                        )
                    )
                } else {
                    // Se falhar, adiciona uma mota "vazia" ou marca erro (como no antigo)
                    listaMotas.add(Motorcycle(vin, 0, 0, 0.0, 0.0, false))
                }
            }

            if (listaMotas.isNotEmpty()) {
                _uiState.value = HomeUiState.Success(listaMotas)
            } else {
                _uiState.value = HomeUiState.Error("Não foi possível carregar as motas.")
            }
        }
    }
}