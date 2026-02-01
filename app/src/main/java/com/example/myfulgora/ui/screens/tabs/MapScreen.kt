package com.example.myfulgora.ui.screens.tabs

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.myfulgora.ui.theme.GreenFresh
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MapStyleOptions
import com.google.maps.android.compose.*

@Composable
fun MapScreen() {
    // 1. Posição Inicial (Ex: Lisboa - Marquês de Pombal)
    val bikeLocation = LatLng(38.7253, -9.1500)

    val cameraPositionState = rememberCameraPositionState {
        position = CameraPosition.fromLatLngZoom(bikeLocation, 15f)
    }

    Box(modifier = Modifier.fillMaxSize()) {

        // 2. O MAPA GOOGLE
        GoogleMap(
            modifier = Modifier.fillMaxSize(),
            cameraPositionState = cameraPositionState,
            properties = MapProperties(
                // 👇 AQUI ESTÁ O TRUQUE: Carregar o estilo escuro
                mapStyleOptions = MapStyleOptions(MapStyleDark)
            ),
            uiSettings = MapUiSettings(
                zoomControlsEnabled = false, // Esconde os botões +/- feios
                myLocationButtonEnabled = false
            )
        ) {
            // 3. O Marcador da Mota
            Marker(
                state = MarkerState(position = bikeLocation),
                title = "My Fulgora",
                snippet = "Parked 2h ago"
            )
        }

        // Botão flutuante "Voltar à mota"
        FloatingActionButton(
            onClick = { cameraPositionState.position = CameraPosition.fromLatLngZoom(bikeLocation, 15f) },
            containerColor = GreenFresh,
            contentColor = Color.White,
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .padding(24.dp)
        ) {
            Icon(Icons.Filled.ArrowBack, contentDescription = "Center")
        }
    }
}

// 👇 O CODIGO JSON QUE PINTA O MAPA DE PRETO (Estilo "Midnight Commander")
const val MapStyleDark = """
[
  {
    "elementType": "geometry",
    "stylers": [{ "color": "#212121" }]
  },
  {
    "elementType": "labels.icon",
    "stylers": [{ "visibility": "off" }]
  },
  {
    "elementType": "labels.text.fill",
    "stylers": [{ "color": "#757575" }]
  },
  {
    "elementType": "labels.text.stroke",
    "stylers": [{ "color": "#212121" }]
  },
  {
    "featureType": "administrative",
    "elementType": "geometry",
    "stylers": [{ "color": "#757575" }]
  },
  {
    "featureType": "poi",
    "elementType": "labels.text.fill",
    "stylers": [{ "color": "#757575" }]
  },
  {
    "featureType": "road",
    "elementType": "geometry.fill",
    "stylers": [{ "color": "#2c2c2c" }]
  },
  {
    "featureType": "road",
    "elementType": "labels.text.fill",
    "stylers": [{ "color": "#8a8a8a" }]
  },
  {
    "featureType": "road.arterial",
    "elementType": "geometry",
    "stylers": [{ "color": "#373737" }]
  },
  {
    "featureType": "road.highway",
    "elementType": "geometry",
    "stylers": [{ "color": "#3c3c3c" }]
  },
  {
    "featureType": "water",
    "elementType": "geometry",
    "stylers": [{ "color": "#000000" }]
  }
]
"""