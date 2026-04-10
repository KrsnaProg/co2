package com.quantumai.co2.ui.devicedetailscreen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.quantumai.co2.database.DeviceDao
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn

data class DeviceDetailUiState(
    val id: String = "",
    val name: String = "",
    val location: String = "",
    val isOnline: Boolean = false,
)

class DeviceDetailViewModel(
    deviceId: String,
    deviceDao: DeviceDao,
) : ViewModel() {

    val state = deviceDao.observeById(deviceId)
        .map { entity ->
            entity?.let {
                DeviceDetailUiState(
                    id = it.id,
                    name = it.name,
                    location = it.location,
                    isOnline = it.isOnline,
                )
            } ?: DeviceDetailUiState(id = deviceId)
        }
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5_000), DeviceDetailUiState())
}

