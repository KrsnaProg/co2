package com.quantumai.co2.ui.devicesettingsscreen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.quantumai.co2.database.DeviceDao
import com.quantumai.co2.database.DeviceSettingsDao
import com.quantumai.co2.database.DeviceSettingsEntity
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

data class DeviceSettingsUiState(
    val name: String = "",
    val location: String = "",
    val alertGas: Boolean = true,
    val alertCo: Boolean = true,
    val alertSmoke: Boolean = false,
    val alertFlame: Boolean = true,
    val isSaved: Boolean = false,
)

private val mockDefaults = mapOf(
    "1" to DeviceSettingsUiState(
        name = "Main Sensor Hub", location = "Home Office",
        alertGas = true, alertCo = true, alertSmoke = false, alertFlame = true
    ),
    "2" to DeviceSettingsUiState(
        name = "Smart Sensor", location = "Living Room",
        alertGas = true, alertCo = true, alertSmoke = false, alertFlame = true
    ),
)

class DeviceSettingsViewModel(
    private val deviceId: String,
    private val dao: DeviceSettingsDao,
    private val deviceDao: DeviceDao,
) : ViewModel() {

    private val _state = MutableStateFlow(
        mockDefaults[deviceId] ?: DeviceSettingsUiState(name = deviceId)
    )
    val state: StateFlow<DeviceSettingsUiState> = _state.asStateFlow()

    init {
        viewModelScope.launch {
            // Load saved alert settings from device_settings table
            dao.getById(deviceId)?.let { entity ->
                _state.value = _state.value.copy(
                    alertGas = entity.alertGas,
                    alertCo = entity.alertCo,
                    alertSmoke = entity.alertSmoke,
                    alertFlame = entity.alertFlame,
                )
            }
            // Load name/location from devices table (single source of truth)
            deviceDao.getById(deviceId)?.let { entity ->
                _state.value = _state.value.copy(
                    name = entity.name,
                    location = entity.location,
                )
            }
        }
    }

    fun saveSettings(
        name: String,
        location: String,
        alertGas: Boolean,
        alertCo: Boolean,
        alertSmoke: Boolean,
        alertFlame: Boolean,
    ) {
        viewModelScope.launch {
            // Update alert preferences
            dao.upsert(
                DeviceSettingsEntity(
                    deviceId = deviceId,
                    name = name,
                    location = location,
                    alertGas = alertGas,
                    alertCo = alertCo,
                    alertSmoke = alertSmoke,
                    alertFlame = alertFlame,
                )
            )
            // Update name/location in the shared devices table so
            // DevicesScreen and DeviceDetailScreen immediately reflect changes.
            deviceDao.getById(deviceId)?.let { existing ->
                deviceDao.upsert(existing.copy(name = name, location = location))
            }
            _state.value = DeviceSettingsUiState(
                name = name,
                location = location,
                alertGas = alertGas,
                alertCo = alertCo,
                alertSmoke = alertSmoke,
                alertFlame = alertFlame,
                isSaved = true,
            )
        }
    }

    fun deleteDevice(onDeleted: () -> Unit) {
        viewModelScope.launch {
            dao.deleteById(deviceId)
            deviceDao.deleteById(deviceId)
            onDeleted()
        }
    }
}

