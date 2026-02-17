package com.example.myfulgora.ui.viewmodel

// Uma caixa simples para guardar a info do utilizador atual
data class ProfileState(
    val name: String = "A carregar...",
    val email: String = "...",
    val bikeName: String = "Nenhuma mota associada",
    val bikeVin: String = "---",
    val isBikeConnected: Boolean = false
)