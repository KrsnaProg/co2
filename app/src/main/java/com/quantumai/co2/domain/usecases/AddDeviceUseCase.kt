package com.quantumai.co2.domain.usecases

import com.quantumai.co2.domain.GlobalDataProvider
import com.quantumai.co2.domain.model.AddDeviceRequest

class AddDeviceUseCase(
    private val globalDataProvider: GlobalDataProvider,
) {
    suspend operator fun invoke(
        imei: String,
        deviceName: String,
        deviceAddress: String,
        latitude: Double,
        longitude: Double,
    ): String {
        return globalDataProvider.addDeviceByImei(
            imei = imei,
            body = AddDeviceRequest(
                deviceName = deviceName,
                deviceAddress = deviceAddress,
                latitude = latitude,
                longitude = longitude,
            )
        )
    }
}

