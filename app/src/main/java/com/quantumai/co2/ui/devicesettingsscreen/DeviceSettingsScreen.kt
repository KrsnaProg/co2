package com.quantumai.co2.ui.devicesettingsscreen

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
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
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
import com.quantumai.co2.ui.colors.AppColors
import com.quantumai.co2.ui.components.CO2Button

data class AlertToggleUi(val title: String, val subtitle: String, val enabledByDefault: Boolean)

@Composable
fun DeviceSettingsScreen(
    navController: NavController,
    viewModel: DeviceSettingsViewModel,
) {
    val savedState by viewModel.state.collectAsState()

    // Local editable state initialised from saved state
    var deviceName by remember(savedState.name) { mutableStateOf(savedState.name) }
    var deviceLocation by remember(savedState.location) { mutableStateOf(savedState.location) }
    var alertGas by remember(savedState.alertGas) { mutableStateOf(savedState.alertGas) }
    var alertCo by remember(savedState.alertCo) { mutableStateOf(savedState.alertCo) }
    var alertSmoke by remember(savedState.alertSmoke) { mutableStateOf(savedState.alertSmoke) }
    var alertFlame by remember(savedState.alertFlame) { mutableStateOf(savedState.alertFlame) }

    var showEditNameDialog by remember { mutableStateOf(false) }
    var showEditLocationDialog by remember { mutableStateOf(false) }

    val alertToggles = listOf(
        Triple(stringResource(R.string.device_settings_alert_gas_title), stringResource(R.string.device_settings_alert_gas_subtitle), alertGas),
        Triple(stringResource(R.string.device_settings_alert_co_title), stringResource(R.string.device_settings_alert_co_subtitle), alertCo),
        Triple(stringResource(R.string.device_settings_alert_smoke_title), stringResource(R.string.device_settings_alert_smoke_subtitle), alertSmoke),
        Triple(stringResource(R.string.device_settings_alert_flame_title), stringResource(R.string.device_settings_alert_flame_subtitle), alertFlame),
    )

    // Edit Name dialog
    if (showEditNameDialog) {
        var draft by remember { mutableStateOf(deviceName) }
        AlertDialog(
            onDismissRequest = { showEditNameDialog = false },
            title = {
                Text(
                    text = stringResource(R.string.device_settings_dialog_edit_name_title),
                    fontWeight = FontWeight.Bold,
                    color = AppColors.primaryText
                )
            },
            text = {
                OutlinedTextField(
                    value = draft,
                    onValueChange = { draft = it },
                    singleLine = true,
                    shape = RoundedCornerShape(10.dp),
                    colors = TextFieldDefaults.colors(
                        focusedContainerColor = Color.White,
                        unfocusedContainerColor = Color.White,
                        focusedIndicatorColor = AppColors.primaryGreen,
                        unfocusedIndicatorColor = AppColors.primaryGray,
                        cursorColor = AppColors.primaryText,
                        focusedTextColor = AppColors.primaryText,
                        unfocusedTextColor = AppColors.primaryText,
                    ),
                    modifier = Modifier.fillMaxWidth()
                )
            },
            confirmButton = {
                TextButton(onClick = { deviceName = draft; showEditNameDialog = false }) {
                    Text(stringResource(R.string.device_settings_dialog_save), color = AppColors.primaryGreen, fontWeight = FontWeight.Bold)
                }
            },
            dismissButton = {
                TextButton(onClick = { showEditNameDialog = false }) {
                    Text(stringResource(R.string.device_settings_dialog_cancel), color = AppColors.secondaryText)
                }
            },
            containerColor = Color.White,
            shape = RoundedCornerShape(16.dp)
        )
    }

    // Edit Location dialog
    if (showEditLocationDialog) {
        var draft by remember { mutableStateOf(deviceLocation) }
        AlertDialog(
            onDismissRequest = { showEditLocationDialog = false },
            title = {
                Text(
                    text = stringResource(R.string.device_settings_dialog_edit_location_title),
                    fontWeight = FontWeight.Bold,
                    color = AppColors.primaryText
                )
            },
            text = {
                OutlinedTextField(
                    value = draft,
                    onValueChange = { draft = it },
                    singleLine = true,
                    shape = RoundedCornerShape(10.dp),
                    colors = TextFieldDefaults.colors(
                        focusedContainerColor = Color.White,
                        unfocusedContainerColor = Color.White,
                        focusedIndicatorColor = AppColors.primaryGreen,
                        unfocusedIndicatorColor = AppColors.primaryGray,
                        cursorColor = AppColors.primaryText,
                        focusedTextColor = AppColors.primaryText,
                        unfocusedTextColor = AppColors.primaryText,
                    ),
                    modifier = Modifier.fillMaxWidth()
                )
            },
            confirmButton = {
                TextButton(onClick = { deviceLocation = draft; showEditLocationDialog = false }) {
                    Text(stringResource(R.string.device_settings_dialog_save), color = AppColors.primaryGreen, fontWeight = FontWeight.Bold)
                }
            },
            dismissButton = {
                TextButton(onClick = { showEditLocationDialog = false }) {
                    Text(stringResource(R.string.device_settings_dialog_cancel), color = AppColors.secondaryText)
                }
            },
            containerColor = Color.White,
            shape = RoundedCornerShape(16.dp)
        )
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 24.dp, vertical = 20.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(bottom = 136.dp)
                .verticalScroll(rememberScrollState()),
            verticalArrangement = Arrangement.spacedBy(20.dp)
        ) {
            // Device Name row
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column(modifier = Modifier.weight(1f)) {
                    Text(
                        text = stringResource(R.string.device_settings_device_name),
                        color = AppColors.primaryText,
                        fontWeight = FontWeight.Bold,
                        fontSize = 18.sp
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(text = deviceName, color = AppColors.secondaryText, fontSize = 15.sp)
                }
                Icon(
                    painter = painterResource(R.drawable.ic_edit),
                    contentDescription = stringResource(R.string.device_settings_edit_cd),
                    tint = Color(0xFF8B5CF6),
                    modifier = Modifier.size(22.dp).clickable { showEditNameDialog = true }
                )
            }

            HorizontalDivider(color = AppColors.primaryGray)

            // Device Location row
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column(modifier = Modifier.weight(1f)) {
                    Text(
                        text = stringResource(R.string.device_settings_device_location),
                        color = AppColors.primaryText,
                        fontWeight = FontWeight.Bold,
                        fontSize = 18.sp
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .background(Color.White, RoundedCornerShape(10.dp))
                            .border(1.dp, AppColors.primaryGray, RoundedCornerShape(10.dp))
                            .padding(horizontal = 12.dp, vertical = 14.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(
                            painter = painterResource(R.drawable.ic_location),
                            contentDescription = null,
                            tint = AppColors.secondaryText,
                            modifier = Modifier.size(16.dp)
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        Text(text = deviceLocation, color = AppColors.primaryText, fontSize = 15.sp)
                    }
                }
                Spacer(modifier = Modifier.width(12.dp))
                Icon(
                    painter = painterResource(R.drawable.ic_edit),
                    contentDescription = stringResource(R.string.device_settings_edit_cd),
                    tint = Color(0xFF8B5CF6),
                    modifier = Modifier.size(22.dp).clickable { showEditLocationDialog = true }
                )
            }

            // Alerts section
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .border(1.dp, Color(0xFF8B5CF6), RoundedCornerShape(12.dp))
                    .padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(0.dp)
            ) {
                Text(
                    text = stringResource(R.string.device_settings_alerts_section),
                    color = AppColors.primaryText,
                    fontWeight = FontWeight.Bold,
                    fontSize = 18.sp
                )
                Spacer(modifier = Modifier.height(12.dp))

                alertToggles.forEachIndexed { index, (title, subtitle, checked) ->
                    val toggleState = when (index) {
                        0 -> alertGas to { v: Boolean -> alertGas = v }
                        1 -> alertCo to { v: Boolean -> alertCo = v }
                        2 -> alertSmoke to { v: Boolean -> alertSmoke = v }
                        else -> alertFlame to { v: Boolean -> alertFlame = v }
                    }
                    Row(
                        modifier = Modifier.fillMaxWidth().padding(vertical = 10.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Column(modifier = Modifier.weight(1f)) {
                            Text(text = title, color = AppColors.primaryText, fontWeight = FontWeight.SemiBold, fontSize = 15.sp)
                            Text(text = subtitle, color = AppColors.secondaryText, fontSize = 13.sp)
                        }
                        Switch(
                            checked = toggleState.first,
                            onCheckedChange = toggleState.second,
                            colors = SwitchDefaults.colors(
                                checkedThumbColor = Color.White,
                                checkedTrackColor = Color(0xFF8B5CF6),
                                uncheckedThumbColor = Color.White,
                                uncheckedTrackColor = AppColors.primaryGray,
                            )
                        )
                    }
                    if (index < alertToggles.lastIndex) {
                        HorizontalDivider(color = AppColors.primaryGray.copy(alpha = 0.5f))
                    }
                }
            }
        }

        // Bottom buttons
        Column(
            modifier = Modifier.align(Alignment.BottomCenter).fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            CO2Button(
                text = stringResource(R.string.device_settings_save_button),
                onClick = {
                    viewModel.saveSettings(
                        name = deviceName,
                        location = deviceLocation,
                        alertGas = alertGas,
                        alertCo = alertCo,
                        alertSmoke = alertSmoke,
                        alertFlame = alertFlame,
                    )
                    navController.navigateUp()
                }
            )
            CO2Button(
                text = stringResource(R.string.device_settings_remove_button),
                onClick = { viewModel.deleteDevice { navController.navigateUp() } },
                colors = androidx.compose.material3.ButtonDefaults.buttonColors(
                    containerColor = Color(0xFFDB143C)
                )
            )
        }
    }
}
