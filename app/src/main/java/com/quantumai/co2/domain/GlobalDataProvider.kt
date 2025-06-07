package com.quantumai.co2.domain

import com.quantumai.co2.domain.model.ApiResponse
import retrofit2.http.GET
import retrofit2.http.Path

interface GlobalDataProvider {

    @GET("/HelloController/GetMyDeviceInfo/{deviceId}")
    suspend fun getMyDeviceInfo(
        @Path("deviceId") deviceId: String
    ): ApiResponse
}
