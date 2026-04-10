package com.quantumai.co2.ui.devicesscreen

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
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
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.quantumai.co2.R
import com.quantumai.co2.ui.CO2Routes
import com.quantumai.co2.ui.colors.AppColors
import com.quantumai.co2.ui.components.CO2Button
import com.quantumai.co2.ui.loginscreen.LoginViewModel

data class DeviceItemUi(
    val id: String,
    val name: String,
    val location: String,
    val isOnline: Boolean,
)

private val mockDevices = listOf(
    DeviceItemUi(id = "1", name = "Sentinel Hub", location = "Home Office", isOnline = true),
    DeviceItemUi(id = "2", name = "Smart Sensor", location = "Living Room", isOnline = false),
)

@Composable
fun DevicesScreen(
    navController: NavController,
    viewModel: LoginViewModel
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp, vertical = 20.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(bottom = 68.dp)
        ) {
            Text(
                text = stringResource(R.string.devices_feature_title),
                color = AppColors.primaryText,
                fontWeight = FontWeight.Bold,
                fontSize = 32.sp
            )

            Spacer(modifier = Modifier.height(16.dp))

            LazyColumn(verticalArrangement = Arrangement.spacedBy(12.dp)) {
                items(mockDevices) { device ->
                    DeviceCard(
                        device = device,
                        onClick = {
                            navController.navigate(CO2Routes.DeviceDetailScreenRoute(deviceId = device.id))
                        }
                    )
                }
            }
        }

        CO2Button(
            text = stringResource(R.string.devices_feature_add_new_device),
            onClick = {
                navController.navigate(CO2Routes.AddNewDeviceScreenRoute)
            },
            modifier = Modifier.align(Alignment.BottomCenter)
        )
    }
}

@Composable
private fun DeviceCard(device: DeviceItemUi, onClick: () -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.White, RoundedCornerShape(12.dp))
            .border(1.dp, AppColors.primaryGray, RoundedCornerShape(12.dp))
            .clickable { onClick() }
            .padding(horizontal = 14.dp, vertical = 14.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(
            modifier = Modifier
                .size(48.dp)
                .background(AppColors.primaryLightBlue, RoundedCornerShape(10.dp)),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = device.name.first().toString(),
                color = AppColors.primaryText,
                fontWeight = FontWeight.Bold,
                fontSize = 20.sp
            )
        }

        Spacer(modifier = Modifier.width(12.dp))

        Column(modifier = Modifier.weight(1f)) {
            Text(
                text = device.name,
                color = AppColors.primaryText,
                fontWeight = FontWeight.SemiBold,
                fontSize = 16.sp
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

        Icon(
            painter = painterResource(R.drawable.arrow_left),
            contentDescription = null,
            modifier = Modifier
                .size(20.dp)
                .then(
                    Modifier.padding(end = 0.dp)
                ),
            tint = AppColors.secondaryText
        )
    }
}
