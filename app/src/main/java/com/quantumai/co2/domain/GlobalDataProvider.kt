package com.quantumai.co2.domain

import com.quantumai.co2.domain.model.DeviceData
import com.quantumai.co2.domain.model.LoginResponseModel
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query

interface GlobalDataProvider {
    @GET("IoT/GetMyDeviceData/{imei}")
    suspend fun getMyDeviceInfo(
        @Path("imei") imei: String
    ): DeviceData

    @POST("/SafeHome/Customer/Login")
    suspend fun loginUser(
        @Query("email") email: String,
        @Query("password") password: String,
    ): LoginResponseModel
}
