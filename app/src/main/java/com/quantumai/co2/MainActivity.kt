package com.quantumai.co2

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.material3.Text

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d("luka", "Activity onCreate called")
        enableEdgeToEdge()
        setContent {
            Text(
                text = "Hello, CO2!",
            )
        }
    }
}
