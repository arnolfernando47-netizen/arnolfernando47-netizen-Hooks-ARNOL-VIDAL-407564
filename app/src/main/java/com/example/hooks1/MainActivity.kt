package com.example.hooks1

import android.content.res.Configuration // Para onConfigurationChanged [cite: 242]
import android.os.Bundle
import android.util.Log // 1. Añadir import para Log [cite: 75]
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.hooks1.ui.theme.Hooks1Theme

class MainActivity : ComponentActivity() {

    // 1. Añadir el objeto companion con el TAG [cite: 79, 81, 82]
    companion object {
        private const val TAG = "MainActivity" // [cite: 82]
    }

    override fun onCreate(savedInstanceState: Bundle?) { // [cite: 27]
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Hooks1Theme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Greeting(
                        name = "Android",
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
        Log.d(TAG, "onCreate") // 2. Añadir log [cite: 84]
    }

    // 10. Implementar las devoluciones de llamada restantes [cite: 160]
    override fun onRestart() { // [cite: 34, 156]
        super.onRestart()
        Log.d(TAG, "onRestart")
    }

    override fun onStart() { // [cite: 39]
        super.onStart()
        Log.d(TAG, "onStart")
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) { // [cite: 40]
        super.onRestoreInstanceState(savedInstanceState)
        Log.d(TAG, "onRestoreInstanceState")
    }

    override fun onResume() { // [cite: 41]
        super.onResume()
        Log.d(TAG, "onResume")
    }

    override fun onPause() { // [cite: 46]
        super.onPause()
        Log.d(TAG, "onPause")
    }

    override fun onSaveInstanceState(outState: Bundle) { // [cite: 43]
        super.onSaveInstanceState(outState)
        Log.d(TAG, "onSaveInstanceState")
    }

    override fun onStop() { // [cite: 47]
        super.onStop()
        Log.d(TAG, "onStop")
    }

    override fun onDestroy() { // [cite: 48]
        super.onDestroy()
        Log.d(TAG, "onDestroy")
    }

    // 17. Implementar onConfigurationChanged (opcional, para el punto 17) [cite: 237, 241]
    override fun onConfigurationChanged(newConfig: Configuration) {
        super.onConfigurationChanged(newConfig)
        Log.d(TAG, "onConfigurationChanged")
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(text = "Hello $name!", modifier = modifier)
}