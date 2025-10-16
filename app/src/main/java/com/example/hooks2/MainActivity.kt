package com.example.hooks2

import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import java.util.Random

class MainActivity : AppCompatActivity() {

    private lateinit var numberTextView: TextView
    private lateinit var generateButton: Button

    // Estado de la aplicación
    private var randomNumber = 0

    // Constantes para el Bundle y Logcat
    companion object {
        private const val RANDOM_NUMBER_KEY = "RANDOM_NUMBER"
        private const val TAG = "MainActivityHooks2"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Inicializar vistas por sus IDs
        numberTextView = findViewById(R.id.number_text_view)
        generateButton = findViewById(R.id.generate_button)

        // Restaurar estado: se llama si la actividad se recrea (ej. por rotación)
        if (savedInstanceState != null) {
            randomNumber = savedInstanceState.getInt(RANDOM_NUMBER_KEY, 0)
            Log.d(TAG, "onCreate: Estado restaurado -> $randomNumber")
        }

        // Actualizar la interfaz de usuario con el valor inicial o restaurado
        updateUI()

        // Configuración del Listener del botón
        generateButton.setOnClickListener {
            // Generar un número aleatorio entre 0 y 999
            randomNumber = Random().nextInt(1000)
            updateUI()
            Log.d(TAG, "Nuevo número generado: $randomNumber")
        }

        Log.d(TAG, "onCreate finalizado.")
    }

    // Guardar estado: llamado cuando la actividad está a punto de ser destruida temporalmente
    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putInt(RANDOM_NUMBER_KEY, randomNumber)
        Log.d(TAG, "onSaveInstanceState: Estado guardado -> $randomNumber")
    }

    // Función auxiliar para actualizar el TextView
    private fun updateUI() {
        // Usa el recurso de cadena formateada (%d)
        numberTextView.text = getString(R.string.random_number_message, randomNumber)
    }
}