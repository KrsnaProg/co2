package com.quantumai.co2.ui.devicesscreen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.quantumai.co2.R
import com.quantumai.co2.ui.CO2Routes
import com.quantumai.co2.ui.components.CO2Button
import com.quantumai.co2.ui.loginscreen.LoginViewModel

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
        CO2Button(
            text = stringResource(R.string.devices_feature_add_new_device),
            onClick = {
                navController.navigate(CO2Routes.AddNewDeviceScreenRoute)
            },
            modifier = Modifier.align(Alignment.BottomCenter)
        )
    }
}
