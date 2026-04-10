package com.quantumai.co2.ui.devicedetailscreen

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.quantumai.co2.R
import com.quantumai.co2.ui.colors.AppColors
import com.quantumai.co2.ui.components.CO2Button

data class ReadingUi(val icon: String, val label: String, val value: String, val status: String)

private val mockReadings = listOf(
    ReadingUi("☁", "Gas", "200 ppm", "Normal"),
    ReadingUi("◑", "CO", "150 ppm", "Normal"),
    ReadingUi("≋", "Cigarette", "50 ppm", "Normal"),
    ReadingUi("🔥", "Flame", "Not Detected", "Normal"),
)

private data class MockDevice(
    val id: String,
    val name: String,
    val location: String,
    val isOnline: Boolean,
)

private val mockDeviceMap = mapOf(
    "1" to MockDevice("1", "Main Sensor Hub", "Home Office", true),
    "2" to MockDevice("2", "Smart Sensor", "Living Room", false),
)

@Composable
fun DeviceDetailScreen(navController: NavController, deviceId: String) {
    val device = mockDeviceMap[deviceId]
        ?: MockDevice(deviceId, "Unknown Device", "Unknown", false)

    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 24.dp, vertical = 20.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(bottom = 68.dp)
                .verticalScroll(rememberScrollState())
        ) {
            // Device header
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color.White, RoundedCornerShape(12.dp))
                    .border(1.dp, AppColors.primaryGray, RoundedCornerShape(12.dp))
                    .padding(16.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Box(
                    modifier = Modifier
                        .size(60.dp)
                        .background(AppColors.primaryLightBlue, RoundedCornerShape(12.dp)),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = device.name.first().toString(),
                        color = AppColors.primaryText,
                        fontWeight = FontWeight.Bold,
                        fontSize = 24.sp
                    )
                }

                Spacer(modifier = Modifier.width(14.dp))

                Column {
                    Text(
                        text = device.name,
                        color = AppColors.primaryText,
                        fontWeight = FontWeight.Bold,
                        fontSize = 18.sp
                    )
                    Text(
                        text = device.location,
                        color = AppColors.secondaryText,
                        fontSize = 14.sp
                    )
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Box(
                            modifier = Modifier
                                .size(8.dp)
                                .background(
                                    if (device.isOnline) Color(0xFF00C853) else Color(0xFFDB143C),
                                    RoundedCornerShape(99.dp)
                                )
                        )
                        Spacer(modifier = Modifier.width(4.dp))
                        Text(
                            text = if (device.isOnline)
                                stringResource(R.string.devices_feature_status_online)
                            else
                                stringResource(R.string.devices_feature_status_offline),
                            color = AppColors.secondaryText,
                            fontSize = 13.sp
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(20.dp))

            HorizontalDivider(color = AppColors.primaryGray)

            Spacer(modifier = Modifier.height(20.dp))

            // Readings grid
            mockReadings.chunked(2).forEach { pair ->
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    pair.forEach { reading ->
                        ReadingCard(reading = reading, modifier = Modifier.weight(1f))
                    }
                    if (pair.size == 1) Spacer(modifier = Modifier.weight(1f))
                }
                Spacer(modifier = Modifier.height(12.dp))
            }
        }

        CO2Button(
            text = stringResource(R.string.device_detail_feature_settings_button),
            onClick = { },
            modifier = Modifier.align(Alignment.BottomCenter)
        )
    }
}

@Composable
private fun ReadingCard(reading: ReadingUi, modifier: Modifier = Modifier) {
    Column(
        modifier = modifier
            .background(Color.White, RoundedCornerShape(12.dp))
            .border(1.dp, AppColors.primaryGray, RoundedCornerShape(12.dp))
            .padding(14.dp)
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Text(text = reading.icon, fontSize = 16.sp)
            Spacer(modifier = Modifier.width(6.dp))
            Text(
                text = reading.label,
                color = AppColors.secondaryText,
                fontSize = 14.sp,
                fontWeight = FontWeight.Medium
            )
        }
        Spacer(modifier = Modifier.height(6.dp))
        Text(
            text = reading.value,
            color = AppColors.primaryText,
            fontWeight = FontWeight.Bold,
            fontSize = 22.sp
        )
        Text(
            text = reading.status,
            color = AppColors.secondaryText,
            fontSize = 13.sp
        )
    }
}

