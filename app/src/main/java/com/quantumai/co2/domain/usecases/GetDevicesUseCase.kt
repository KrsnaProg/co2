package com.quantumai.co2.domain.usecases

import com.quantumai.co2.data.TokenManager
import com.quantumai.co2.database.DeviceEntity
import com.quantumai.co2.domain.GlobalDataProvider

class GetDevicesUseCase(
    private val globalDataProvider: GlobalDataProvider,
    private val tokenManager: TokenManager,
) {
    suspend operator fun invoke(): List<DeviceEntity> {
        val customerId = tokenManager.getCustomerId()
            ?: error("Customer ID not found. Please log in again.")
        return globalDataProvider.getDevicesByCustomerId(customerId).map { dto ->
            DeviceEntity(
                id = dto.id,
                name = dto.name,
                location = dto.location,
                isOnline = dto.isOnline,
                imei = dto.imei,
            )
        }
    }
}

