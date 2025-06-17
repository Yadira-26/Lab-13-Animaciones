package com.example.lab13_animaciones

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.*
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.lab13_animaciones.ui.theme.Lab13AnimacionesTheme

enum class EstadoPantalla {
    CARGANDO,
    CONTENIDO,
    ERROR
}

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Lab13AnimacionesTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    CambioDeContenido()
                }
            }
        }
    }
}

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun CambioDeContenido() {
    var estado by remember { mutableStateOf(EstadoPantalla.CARGANDO) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        AnimatedContent(
            targetState = estado,
            transitionSpec = {
                fadeIn(animationSpec = tween(500)) togetherWith
                        fadeOut(animationSpec = tween(500))
            },
            label = "animacionContenido"
        ) { targetEstado ->
            when (targetEstado) {
                EstadoPantalla.CARGANDO -> Text("Cargando datos... por favor espera")
                EstadoPantalla.CONTENIDO -> Text("Contenido cargado correctamente ✅")
                EstadoPantalla.ERROR -> Text("¡Error al cargar! ❌")
            }
        }

        Spacer(modifier = Modifier.height(40.dp))

        Row(horizontalArrangement = Arrangement.spacedBy(10.dp)) {
            Button(onClick = { estado = EstadoPantalla.CARGANDO }) {
                Text("Cargando")
            }
            Button(onClick = { estado = EstadoPantalla.CONTENIDO }) {
                Text("Contenido")
            }
            Button(onClick = { estado = EstadoPantalla.ERROR }) {
                Text("Error")
            }
        }
    }
}
