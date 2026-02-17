package com.example.myfulgora.data

import com.example.myfulgora.ui.screens.tabs.profile.ProfileState

// Modelo de dados simples para testes
data class MockUser(
    val username: String,
    val pass: String,
    val profile: ProfileState // Reutilizamos o teu ProfileState
)

object MockDatabase {

    // A nossa "Base de Dados" falsa
    val users = listOf(
        // 1. O Piloto Pro
        MockUser(
            username = "admin",
            pass = "admin",
            profile = ProfileState(
                name = "Carlos Faria",
                email = "carlos.faria@fulgora.pt",
                bikeName = "Fulgora X1 Carbon",
                bikeVin = "V-FG-2024-X1-001",
                isBikeConnected = true
                // Adiciona aqui % bateria se tiveres no ProfileState
            )
        ),

        // 2. O Novato (Sem Mota)
        MockUser(
            username = "novo",
            pass = "1234",
            profile = ProfileState(
                name = "João Ninguém",
                email = "joao@gmail.com",
                bikeName = "No Bike",
                bikeVin = "---",
                isBikeConnected = false
            )
        ),

        // 3. O Mecânico (Mota Velha)
        MockUser(
            username = "mec",
            pass = "1234",
            profile = ProfileState(
                name = "Oficina Central",
                email = "suporte@fulgora.pt",
                bikeName = "Prototype V0",
                bikeVin = "V-FG-2020-PT-999",
                isBikeConnected = false
            )
        )
    )

    // Função para simular o Login
    fun checkLogin(user: String, pass: String): MockUser? {
        // Procura na lista se existe alguém com este user E pass
        return users.find { it.username == user && it.pass == pass }
    }
}