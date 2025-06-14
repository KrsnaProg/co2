package com.quantumai.co2.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val viewModel = viewModel<MainViewModel>().value
            val state by viewModel.viewState.collectAsState()

            Column(
                modifier = Modifier
                    .fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = androidx.compose.ui.Alignment.CenterHorizontally,
            ) {
                Button(
                    onClick = {
                        viewModel.getDeviceInfo("867330021899888")
                    },
                ) {
                    Text(
                        text = "Get Device info!",
                    )
                }

                Spacer(modifier = Modifier.height(48.dp))

                Text(
                    text = state.deviceData?.let { device ->
                        buildString {
                            appendLine("IMEI: ${device.imei}")
                            if (device.readings.isNotEmpty()) {
                                appendLine("Readings:")
                                device.readings.forEach { reading ->
                                    appendLine("- ${reading.type}: ${reading.value} ${reading.unit}")
                                }
                            } else {
                                appendLine("No readings available.")
                            }
                        }
                    } ?: "Device info ..",
                )
            }
        }
    }
}
