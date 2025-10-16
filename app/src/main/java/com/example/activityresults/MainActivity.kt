package com.example.activityresults // Asegúrate de que el paquete coincida con tu proyecto

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.activityresults.ColorPickerActivity.Companion.RAINBOW_COLOR
import com.example.activityresults.ColorPickerActivity.Companion.RAINBOW_COLOR_NAME
import com.example.activityresults.ColorPickerActivity.Companion.TRANSPARENT
import com.example.activityresults.ui.theme.ActivityResultsTheme

class MainActivity : ComponentActivity() {

    // Propiedades de estado para la recomposición (Paso 6)
    private var rainbowColor by mutableStateOf(Color(TRANSPARENT))
    private var colorName by mutableStateOf("")
    private var colorMessage by mutableStateOf("")

    // Launcher para iniciar la actividad y manejar el resultado (Paso 7)
    private val startForResult = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) { activityResult ->
        // Lógica de manejo de resultados
        rainbowColor = Color(
            activityResult.data?.getLongExtra(RAINBOW_COLOR, TRANSPARENT) ?: TRANSPARENT
        )
        colorName = activityResult.data?.getStringExtra(RAINBOW_COLOR_NAME) ?: ""
        colorMessage = getString(
            R.string.color_chosen_message, colorName
        )
    }

    // Actualización de onCreate (Paso 8)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ActivityResultsTheme {
                val context = LocalContext.current
                MainScreen(
                    rainbowColor,
                    colorMessage,
                    context,
                    startForResult
                )
            }
        }
    }
}

// Composable para mostrar el color y el mensaje (Paso 3)
@Composable
fun TextWithBackgroundColor(backgroundColor: Color, colorMessage: String) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .padding(horizontal = 20.dp)
            .height(50.dp)
            .background(backgroundColor)
            .fillMaxWidth()
    ) {
        Text(
            text = colorMessage.ifEmpty { "Chosen color appears here" },
            fontSize = 22.sp,
            color = Color.White,
            textAlign = TextAlign.Center,
            modifier = Modifier.fillMaxWidth()
        )
    }
}

// Composable de la Pantalla Principal (Paso 4)
@Composable
fun MainScreen(
    backgroundColor: Color,
    colorMessage: String,
    context: Context,
    startForResult: ActivityResultLauncher<Intent>
) {
    Scaffold(
        modifier = Modifier.fillMaxSize()
    ) { innerPadding ->
        Column(
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            // Texto de encabezado
            Text(
                text = stringResource(id = R.string.header_text_main),
                fontSize = 18.sp,
                textAlign = TextAlign.Center,
                modifier = Modifier.padding(16.dp)
            )

            // Botón para lanzar ColorPickerActivity
            Button(
                onClick = {
                    val intent = Intent(context, ColorPickerActivity::class.java)
                    startForResult.launch(intent)
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp)
            ) {
                Text(
                    text = stringResource(id = R.string.submit_button_text)
                )
            }

            Spacer(modifier = Modifier.height(20.dp))

            // Componente de visualización del color
            TextWithBackgroundColor(
                backgroundColor = backgroundColor,
                colorMessage = colorMessage
            )
        }
    }
}