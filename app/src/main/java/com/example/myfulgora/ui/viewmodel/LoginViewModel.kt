package com.example.myfulgora.ui.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.myfulgora.data.auth.AuthManager
import com.example.myfulgora.data.auth.UserManager
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

// Estados possíveis do ecrã de login
sealed class LoginState {
    object Idle : LoginState()
    object Loading : LoginState()
    object Success : LoginState()
    data class Error(val message: String) : LoginState()
}

// 👇 Mudámos para AndroidViewModel para poder usar o 'getApplication()' para o AuthManager
class LoginViewModel(application: Application) : AndroidViewModel(application) {

    private val _loginState = MutableStateFlow<LoginState>(LoginState.Idle)
    val loginState = _loginState.asStateFlow()

    // 👇 AQUI ESTÁ A CORREÇÃO: Adicionámos (user, pass) aos argumentos
    fun fazerLogin(user: String, pass: String) {

        // 1. Validação básica antes de tentar a internet
        if (user.isBlank() || pass.isBlank()) {
            _loginState.value = LoginState.Error("Por favor preenche todos os campos.")
            return
        }

        // 2. O NOSSO MODO OFFLINE / TESTES (Lê do ficheiro JSON)
        // Se a função retornar 'true', significa que encontrou o user no JSON
        // e o UserManager já guardou os dados na memória!
        if (UserManager.validateMockLogin(getApplication(), user, pass)) {
            _loginState.value = LoginState.Success
            return
        }

        // 3. O MODO REAL (Keycloak) - Só chega aqui se o user não estiver no JSON
        viewModelScope.launch {
            _loginState.value = LoginState.Loading

            // Instanciar o AuthManager usando o contexto da aplicação
            val authManager = AuthManager(getApplication())

            // Tentar login real
            val sucesso = authManager.loginDireto(user, pass)

            if (sucesso) {
                // Nota para o futuro:
                // Quando for o Keycloak a ter sucesso, também teremos de criar um
                // objeto no UserManager para os ecrãs terem dados reais para mostrar.
                _loginState.value = LoginState.Success
            } else {
                _loginState.value = LoginState.Error("Login falhou. Verifica os dados.")
            }
        }
    }
}