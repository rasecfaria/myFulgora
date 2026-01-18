package com.example.myfulgora.data.model

data class Motorcycle(
    val vin: String,
    var battery: Int,
    var kilometers: Int,
    var latitude: Double,
    var longitude: Double,
    var isSynced: Boolean = false
)