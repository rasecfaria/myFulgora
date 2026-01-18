package com.example.myfulgora.data.remote

import android.util.Log
import com.example.myfulgora.data.auth.TokenStore
import io.grpc.ManagedChannelBuilder
import io.grpc.Metadata
import io.grpc.CallCredentials
import java.util.concurrent.Executor
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
// Os imports do gRPC gerado (confirma se não ficam vermelhos)
import com.example.grpc.MotaRequest
import com.example.grpc.MotaResponse
import com.example.grpc.MotasServiceGrpc

class MotaClient {

    private val address = "172.20.0.202" // O IP do servidor gRPC
    private val port = 5154

    // Configura o canal de comunicação
    private val channel = ManagedChannelBuilder
        .forAddress(address, port)
        .usePlaintext()
        .build()

    // Esta função cria o "Stub" (o cliente) JÁ COM O TOKEN INJETADO
    private fun getAuthenticatedStub(): MotasServiceGrpc.MotasServiceBlockingStub {
        val token = TokenStore.accessToken ?: "" // Vai buscar o token guardado no Login

        // Cria as credenciais para o cabeçalho (Header)
        val creds = object : CallCredentials() {
            override fun applyRequestMetadata(requestInfo: RequestInfo?, appExecutor: Executor?, applier: MetadataApplier?) {
                val metadata = Metadata()
                val key = Metadata.Key.of("Authorization", Metadata.ASCII_STRING_MARSHALLER)
                metadata.put(key, "Bearer $token")
                appExecutor?.execute { applier?.apply(metadata) }
            }

            override fun thisUsesUnstableApi() {} // Necessário para evitar avisos do gRPC
        }

        // Devolve o cliente com as credenciais anexadas
        return MotasServiceGrpc.newBlockingStub(channel).withCallCredentials(creds)
    }

    suspend fun getMotaInfo(vin: String): MotaResponse? {
        return withContext(Dispatchers.IO) {
            try {
                Log.d("MotaClient", "A pedir dados para $vin com Token...")

                val request = MotaRequest.newBuilder().setVin(vin).build()

                // Usa o stub autenticado aqui
                val response = getAuthenticatedStub().getMotaInfo(request)

                return@withContext response
            } catch (e: Exception) {
                Log.e("MotaClient", "Erro gRPC: ${e.message}")
                return@withContext null
            }
        }
    }
}