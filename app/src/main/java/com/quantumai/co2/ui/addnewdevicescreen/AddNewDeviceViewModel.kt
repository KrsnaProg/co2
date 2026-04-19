package com.quantumai.co2.ui.addnewdevicescreen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.quantumai.co2.domain.usecases.AddDeviceUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

sealed class AddDeviceState {
    object Idle : AddDeviceState()
    object Loading : AddDeviceState()
    object Success : AddDeviceState()
    data class Error(val message: String) : AddDeviceState()
}

class AddNewDeviceViewModel(
    private val addDeviceUseCase: AddDeviceUseCase,
) : ViewModel() {

    private val _state = MutableStateFlow<AddDeviceState>(AddDeviceState.Idle)
    val state = _state.asStateFlow()

    fun addDevice(
        imei: String,
        deviceName: String,
        deviceAddress: String,
        latitude: Double,
        longitude: Double,
    ) {
        viewModelScope.launch {
            _state.value = AddDeviceState.Loading
            try {
                addDeviceUseCase(
                    imei = imei,
                    deviceName = deviceName,
                    deviceAddress = deviceAddress,
                    latitude = latitude,
                    longitude = longitude,
                )
                _state.value = AddDeviceState.Success
            } catch (e: Exception) {
                _state.value = AddDeviceState.Error(e.message ?: "Unknown error")
            }
        }
    }

    fun resetState() {
        _state.value = AddDeviceState.Idle
    }
}

