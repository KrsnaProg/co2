package com.quantumai.co2.ui.addnewdevicescreen

import android.Manifest
import android.content.pm.PackageManager
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.content.ContextCompat
import androidx.navigation.NavController
import com.quantumai.co2.R
import com.quantumai.co2.ui.colors.AppColors
import com.quantumai.co2.ui.components.CO2Button
import com.quantumai.co2.ui.components.CO2InputField

@Composable
fun AddNewDeviceScreen(
    navController: NavController,
    viewModel: AddNewDeviceViewModel,
) {
    val context = LocalContext.current
    var location by remember { mutableStateOf("") }
    var imei by remember { mutableStateOf("") }
    var deviceName by remember { mutableStateOf("") }
    var isScannerActive by remember { mutableStateOf(false) }

    val state by viewModel.state.collectAsState()

    val cameraPermissionLauncher = rememberLauncherForActivityResult(
        ActivityResultContracts.RequestPermission()
    ) { granted ->
        if (granted) isScannerActive = true
    }

    LaunchedEffect(state) {
        if (state is AddDeviceState.Success) {
            viewModel.resetState()
            navController.navigateUp()
        }
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 24.dp, vertical = 20.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(bottom = 130.dp)
                .verticalScroll(rememberScrollState())
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .border(1.dp, AppColors.primaryGray, RoundedCornerShape(12.dp))
                    .background(color = Color.White, shape = RoundedCornerShape(12.dp))
                    .padding(20.dp)
            ) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth(0.56f)
                            .height(160.dp)
                            .clip(RoundedCornerShape(8.dp))
                            .border(1.dp, AppColors.primaryGray, RoundedCornerShape(8.dp)),
                        contentAlignment = Alignment.Center
                    ) {
                        if (isScannerActive) {
                            QrScannerView(
                                modifier = Modifier.fillMaxSize(),
                                onQrScanned = { scannedValue ->
                                    imei = scannedValue
                                    isScannerActive = false
                                }
                            )
                        } else {
                            Box(
                                modifier = Modifier.fillMaxSize(),
                                contentAlignment = Alignment.Center
                            ) {
                                androidx.compose.foundation.Image(
                                    painter = painterResource(R.drawable.ic_qr_placeholder),
                                    contentDescription = null,
                                    modifier = Modifier
                                        .fillMaxWidth(0.8f)
                                        .height(120.dp)
                                )
                            }
                        }
                    }

                    Spacer(modifier = Modifier.height(16.dp))

                    Text(
                        text = stringResource(R.string.add_device_feature_qr_hint),
                        color = AppColors.secondaryText,
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Medium
                    )
                }
            }

            Spacer(modifier = Modifier.height(24.dp))

            Text(
                text = stringResource(R.string.add_device_feature_location_title),
                color = AppColors.primaryText,
                fontSize = 32.sp,
                fontWeight = FontWeight.Bold
            )

            Spacer(modifier = Modifier.height(12.dp))

            CO2InputField(
                value = location,
                onValueChange = { location = it },
                placeholder = stringResource(R.string.add_device_feature_location_placeholder)
            )

            Spacer(modifier = Modifier.height(12.dp))

            CO2InputField(
                value = imei,
                onValueChange = { imei = it },
                placeholder = stringResource(R.string.add_device_feature_imei_placeholder)
            )

            Spacer(modifier = Modifier.height(12.dp))

            CO2InputField(
                value = deviceName,
                onValueChange = { deviceName = it },
                placeholder = stringResource(R.string.add_device_feature_name_placeholder)
            )

            if (state is AddDeviceState.Error) {
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = (state as AddDeviceState.Error).message,
                    color = Color.Red,
                    fontSize = 14.sp
                )
            }
        }

        Column(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            CO2Button(
                text = if (isScannerActive)
                    stringResource(R.string.add_device_feature_stop_scan_button)
                else
                    stringResource(R.string.add_device_feature_scan_button),
                onClick = {
                    if (isScannerActive) {
                        isScannerActive = false
                    } else {
                        val permission = Manifest.permission.CAMERA
                        if (ContextCompat.checkSelfPermission(context, permission) == PackageManager.PERMISSION_GRANTED) {
                            isScannerActive = true
                        } else {
                            cameraPermissionLauncher.launch(permission)
                        }
                    }
                }
            )
            CO2Button(
                text = stringResource(R.string.add_device_feature_save_button),
                isLoading = state is AddDeviceState.Loading,
                onClick = {
                    viewModel.addDevice(
                        imei = imei,
                        deviceName = deviceName,
                        deviceAddress = location,
                        latitude = 0.0,
                        longitude = 0.0,
                    )
                }
            )
        }
    }
}
