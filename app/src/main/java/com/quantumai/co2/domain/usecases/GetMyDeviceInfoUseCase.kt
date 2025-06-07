package com.quantumai.co2.domain.usecases

import com.quantumai.co2.domain.GlobalDataProvider
import com.quantumai.co2.domain.model.ApiResponse

class GetMyDeviceInfoUseCase(private val globalDataProvider: GlobalDataProvider) {
    suspend fun invoke(deviceId: String): ApiResponse {
        return globalDataProvider.getMyDeviceInfo(deviceId)
    }
}
