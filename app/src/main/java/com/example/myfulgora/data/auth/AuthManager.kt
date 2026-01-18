package com.example.myfulgora.data.auth

import android.content.Context
import android.util.Log
import okhttp3.FormBody
import okhttp3.OkHttpClient
import okhttp3.Request
import org.json.JSONObject
import java.io.IOException
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class AuthManager(context: Context) {

    private val storage = AuthStorage(context)
    private val client = OkHttpClient()

    companion object {
        // Confirma se este IP está correto para a tua VPN/Configuração
        private const val KEYCLOAK_URL = "http://172.20.0.201:8081/realms/Amover/protocol/openid-connect/token"
        private const val CLIENT_ID = "mota-mobile"
        // Se o teu cliente no keycloak exigir 'client_secret', tens de adicionar aqui.
        // Normalmente para mobile apps é "public" e não precisa.
    }

    // Função que faz o login direto (user + pass)
    suspend fun loginDireto(user: String, pass: String): Boolean {
        return withContext(Dispatchers.IO) {
            try {
                // 1. Construir o corpo do pedido
                val formBody = FormBody.Builder()
                    .add("client_id", CLIENT_ID)
                    .add("username", user)
                    .add("password", pass)
                    .add("grant_type", "password") // O segredo para o login direto
                    .add("scope", "openid profile email")
                    .build()

                // 2. Construir o pedido
                val request = Request.Builder()
                    .url(KEYCLOAK_URL)
                    .post(formBody)
                    .build()

                // 3. Executar
                val response = client.newCall(request).execute()
                val responseBody = response.body?.string()

                if (response.isSuccessful && responseBody != null) {
                    val json = JSONObject(responseBody)
                    val accessToken = json.getString("access_token")

                    // Guardar o token (podes melhorar o AuthStorage depois para guardar só a string)
                    // Por agora, para não partir o resto, vamos guardar de forma simples ou adaptar o storage
                    // Nota: O AuthStorage antigo esperava um objeto complexo.
                    // Vamos simplificar: guarda o token numa variavel estática ou SharedPrefs simples aqui.
                    TokenStore.accessToken = accessToken

                    Log.d("AuthManager", "Login com sucesso! Token: $accessToken")
                    return@withContext true
                } else {
                    Log.e("AuthManager", "Erro no login: ${response.code} - $responseBody")
                    return@withContext false
                }

            } catch (e: Exception) {
                Log.e("AuthManager", "Exceção de rede: ${e.message}")
                return@withContext false
            }
        }
    }

    fun isLoggedIn(): Boolean {
        return !TokenStore.accessToken.isNullOrEmpty()
    }
}

// Um objeto simples para guardar o token em memória enquanto a app está aberta
object TokenStore {
    var accessToken: String? = null
}