package com.quantumai.co2.domain

import com.quantumai.co2.domain.model.DeviceData
import retrofit2.http.GET
import retrofit2.http.Path

interface GlobalDataProvider {
    @GET("IoT/GetMyDeviceData/{imei}")
    suspend fun getMyDeviceInfo(
        @Path("imei") imei: String
    ): DeviceData
}
