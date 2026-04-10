package com.quantumai.co2.ui.devicesscreen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.quantumai.co2.database.DeviceDao
import com.quantumai.co2.database.DeviceEntity
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

private val mockSeed = listOf(
    DeviceEntity(id = "1", name = "Sentinel Hub", location = "Home Office", isOnline = true),
    DeviceEntity(id = "2", name = "Smart Sensor", location = "Living Room", isOnline = false),
)

class DevicesViewModel(private val deviceDao: DeviceDao) : ViewModel() {

    val devices = deviceDao.observeAll()
        .map { list -> list.map { it.toUi() } }
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5_000), emptyList())

    init {
        viewModelScope.launch {
            // Seed mock data only when the table is empty.
            // When the API is ready, replace this with a repository.syncFromApi() call.
            if (deviceDao.count() == 0) {
                deviceDao.upsertAll(mockSeed)
            }
        }
    }

    // Called from the API layer once the endpoint is ready.
    fun syncDevicesFromApi(apiDevices: List<DeviceEntity>) {
        viewModelScope.launch {
            deviceDao.upsertAll(apiDevices)
        }
    }

    private fun DeviceEntity.toUi() = DeviceItemUi(
        id = id,
        name = name,
        location = location,
        isOnline = isOnline,
    )
}