package com.example.myfulgora.ui.screens.tabs.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myfulgora.data.remote.FulgoraMqttClient
import com.example.myfulgora.data.model.BikeState
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class HomeViewModel : ViewModel() {

    // A UI vai ouvir esta variável para atualizar o estado da mota em tempo real
    val bikeState: StateFlow<BikeState> = FulgoraMqttClient.bikeState

    init {
        connectToMqtt()
    }

    private fun connectToMqtt() {
        viewModelScope.launch {
            FulgoraMqttClient.connect()
        }
    }
}