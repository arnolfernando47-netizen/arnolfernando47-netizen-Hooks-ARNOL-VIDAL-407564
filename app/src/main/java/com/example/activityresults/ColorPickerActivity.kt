package com.example.activityresults // Asegúrate de que el paquete coincida con tu proyecto

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.activityresults.ui.theme.ActivityResultsTheme

class ColorPickerActivity : ComponentActivity() {

    // Constantes de Intent y Colores (Pasos 5 y 11)
    companion object {
        const val RAINBOW_COLOR_NAME = "RAINBOW_COLOR_NAME"
        const val RAINBOW_COLOR = "RAINBOW_COLOR"
        const val TRANSPARENT = 0x00FFFFFFL // Color por defecto

        // Valores hexadecimales de los colores
        const val RED = 0xFFFF0000L
        const val ORANGE = 0xFFFFFA500L
        const val YELLOW = 0xFFFFFFE00L
        const val GREEN = 0xFF00FF00L
        const val BLUE = 0xFF0000FFL
        const val INDIGO = 0xFF4B0082L
        const val VIOLET = 0xFF9400D3L
    }

    // Función para devolver el resultado (Paso 12)
    private fun setRainbowColor(color: Long, colorName: String) {
        Intent().let { pickedColorIntent ->
            pickedColorIntent.putExtra(RAINBOW_COLOR_NAME, colorName)
            pickedColorIntent.putExtra(RAINBOW_COLOR, color)
            setResult(Activity.RESULT_OK, pickedColorIntent)
            finish() // Cierra esta actividad
        }
    }

    // Composable para un botón de color (Paso 9)
    @Composable
    fun RainbowColor(color: Long, colorName: String, onClickButton: (Long, String) -> Unit) {
        Button(
            onClick = { onClickButton(color, colorName) },
            colors = ButtonDefaults.buttonColors(containerColor = Color(color), contentColor = Color.White),
            modifier = Modifier
                .padding(horizontal = 20.dp, vertical = 4.dp)
                .fillMaxWidth()
        ) {
            Text(
                text = colorName,
                color = Color.White,
                fontSize = 22.sp,
                textAlign = TextAlign.Center,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.fillMaxWidth()
            )
        }
    }

    // Composable para la pantalla completa de selección (Paso 13)
    @Composable
    private fun ColorPickerScreen() {
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
                val clickHandler: (color: Long, colorName: String) -> Unit = { color, colorName ->
                    setRainbowColor(color, colorName)
                }

                // Encabezado
                Text(
                    text = stringResource(id = R.string.header_text_picker),
                    fontSize = 24.sp,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.padding(20.dp).fillMaxWidth()
                )

                // Botones de colores
                RainbowColor(RED, stringResource(R.string.RED), clickHandler)
                RainbowColor(ORANGE, stringResource(R.string.ORANGE), clickHandler)
                RainbowColor(YELLOW, stringResource(R.string.YELLOW), clickHandler)
                RainbowColor(GREEN, stringResource(R.string.GREEN), clickHandler)
                RainbowColor(BLUE, stringResource(R.string.BLUE), clickHandler)
                RainbowColor(INDIGO, stringResource(R.string.INDIGO), clickHandler)
                RainbowColor(VIOLET, stringResource(R.string.VIOLET), clickHandler)

                // Pie de página
                Text(
                    text = stringResource(id = R.string.footer_text_picker),
                    fontSize = 18.sp,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.padding(12.dp).fillMaxWidth()
                )
            }
        }
    }

    // Actualización de onCreate (Paso 14)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ActivityResultsTheme {
                ColorPickerScreen()
            }
        }
    }
}