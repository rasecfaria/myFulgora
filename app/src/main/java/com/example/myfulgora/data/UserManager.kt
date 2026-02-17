package com.example.myfulgora.data

import com.example.myfulgora.ui.screens.tabs.profile.ProfileState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

object UserManager {

    // O estado inicial é "ninguém logado"
    private val _currentUser = MutableStateFlow<ProfileState?>(null)
    val currentUser = _currentUser.asStateFlow()

    // Variável para saber se está logado (atalho)
    val isLoggedIn: Boolean
        get() = _currentUser.value != null

    // Função chamada quando o login (Mock ou Real) tem sucesso
    fun setLoggedInUser(profile: ProfileState) {
        _currentUser.value = profile
    }

    // Função de Logout
    fun logout() {
        _currentUser.value = null
    }
}