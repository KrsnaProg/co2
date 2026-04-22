package com.quantumai.co2.ui.devicesscreen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.quantumai.co2.database.DeviceDao
import com.quantumai.co2.domain.usecases.GetDevicesUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class DevicesViewModel(
    private val deviceDao: DeviceDao,
    private val getDevicesUseCase: GetDevicesUseCase,
) : ViewModel() {

    // Cached device list — UI always observes Room DB (local-first)
    val devices = deviceDao.observeAll()
        .map { list -> list.map { it.toUi() } }
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5_000), emptyList())

    private val _isRefreshing = MutableStateFlow(false)
    val isRefreshing = _isRefreshing.asStateFlow()

    private val _errorMessage = MutableStateFlow<String?>(null)
    val errorMessage = _errorMessage.asStateFlow()

    init {
        refreshDevices()
    }

    /** Fetches devices from the API and upserts them into the local Room DB. */
    fun refreshDevices() {
        viewModelScope.launch {
            _isRefreshing.value = true
            _errorMessage.value = null
            try {
                val apiDevices = getDevicesUseCase()
                deviceDao.upsertAll(apiDevices)
            } catch (e: Exception) {
                _errorMessage.value = e.message
            } finally {
                _isRefreshing.value = false
            }
        }
    }

    private fun com.quantumai.co2.database.DeviceEntity.toUi() = DeviceItemUi(
        id = id,
        name = name,
        location = location,
        isOnline = isOnline,
    )
}