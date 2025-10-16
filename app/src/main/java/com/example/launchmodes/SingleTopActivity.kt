package com.example.launchmodes

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.launchmodes.ui.theme.LaunchModesTheme

// NOTA: Asegúrate de reemplazar "StandardActivity::class.java" si tienes otro nombre.
// También podrías crear un archivo "StandardActivity.kt" para un ejemplo completo.

class SingleTopActivity : ComponentActivity() {
    // 1. Sobrescribimos onResume para mostrar que la Activity vuelve a primer plano
    //    en lugar de ser recreada cuando se lanza una nueva instancia 'singleTop'.
    override fun onResume() {
        super.onResume()
        // Normalmente usarías un logcat, pero para el ejemplo, podríamos actualizar un Text.
        // Aquí no podemos hacerlo fácilmente sin State, pero mantenemos el método por la demostración.
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            LaunchModesTheme {
                MainScreen()
            }
        }
    }
}

@Composable
fun MainScreen(modifier: Modifier = Modifier) {
    val context = LocalContext.current
    val currentActivityName = "SingleTopActivity (launchMode='singleTop')"

    Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(16.dp, Alignment.CenterVertically)
        ) {
            Text(
                text = "ESTA ES LA $currentActivityName",
                fontSize = 20.sp,
                textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxWidth()
            )

            // Botón para lanzarse a sí misma (prueba SingleTop)
            Button(onClick = {
                val intent = Intent(context, SingleTopActivity::class.java)
                context.startActivity(intent)
            }) {
                Text("Lanzar SingleTopActivity (a sí misma)")
            }

            // Botón para lanzar una Standard Activity (si existe)
            Button(onClick = {
                // Asegúrate de que tienes una StandardActivity en tu proyecto
                try {
                    val intent = Intent(context, Class.forName("com.example.launchmodes.StandardActivity"))
                    context.startActivity(intent)
                } catch (e: ClassNotFoundException) {
                    // Muestra un error si la clase no existe.
                    println("ERROR: No se encontró 'StandardActivity'. Crea una.")
                }
            }) {
                Text("Lanzar StandardActivity (Normal)")
            }
        }
    }
}

// Puedes mantener el Preview si lo deseas, pero no es crucial para la funcionalidad
/*
@Preview(showBackground = true)
@Composable
fun MainScreenPreview() {
    LaunchModesTheme {
        MainScreen()
    }
}
*/