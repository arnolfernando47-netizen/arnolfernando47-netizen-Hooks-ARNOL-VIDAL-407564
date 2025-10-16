package com.example.remembersaveable

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.runtime.mutableStateOf
// 👈 Importación clave para guardar el estado en cambios de configuración
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
// Usando tu nombre de tema
import com.example.remembersaveable.ui.theme.RememberSaveableTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            RememberSaveableTheme {
                Scaffold( modifier = Modifier.fillMaxSize() ) { innerPadding ->
                    // Llamamos a la nueva Composable del formulario
                    GreetingFormScreen(
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

// -----------------------------------------------------------------------------
// Función Composable que implementa la lógica del Ejercicio 2.03
// -----------------------------------------------------------------------------
@Composable
fun GreetingFormScreen(modifier: Modifier = Modifier) {

    // 1. Usamos rememberSaveable para conservar el estado
    //    de los campos de texto al girar el dispositivo.
    var firstName by rememberSaveable { mutableStateOf("") }
    var lastName by rememberSaveable { mutableStateOf("") }
    var fullName by rememberSaveable { mutableStateOf("") }

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {

        // Campo de texto para el nombre
        OutlinedTextField(
            value = firstName,
            onValueChange = { firstName = it },
            label = { Text("First name") }
        )

        Spacer(modifier = Modifier.height(8.dp))

        // Campo de texto para el apellido
        OutlinedTextField(
            value = lastName,
            onValueChange = { lastName = it },
            label = { Text("Last name") }
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Botón
        Button(
            onClick = {
                if (firstName.isNotBlank() && lastName.isNotBlank()) {
                    fullName = "Welcome ${firstName} ${lastName}! State saved!"
                } else {
                    fullName = ""
                }
            },
            // El botón está habilitado si al menos un campo tiene texto
            enabled = firstName.isNotBlank() || lastName.isNotBlank()
        ) {
            Text("Enter")
        }

        Spacer(modifier = Modifier.height(24.dp))

        // Muestra el saludo completo si existe
        if (fullName.isNotBlank()) {
            Text(text = fullName)
        }
    }
}

// -----------------------------------------------------------------------------
// Función de vista previa
// -----------------------------------------------------------------------------
@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    RememberSaveableTheme {
        // Llama a la nueva Composable en la vista previa
        GreetingFormScreen()
    }
}