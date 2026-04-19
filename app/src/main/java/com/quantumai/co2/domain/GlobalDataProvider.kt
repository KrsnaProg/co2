package com.quantumai.co2.domain

import com.quantumai.co2.domain.model.AddDeviceRequest
import com.quantumai.co2.domain.model.DevicePreviewDto
import com.quantumai.co2.domain.model.ForgotPasswordRequestModel
import com.quantumai.co2.domain.model.LoginRequestModel
import com.quantumai.co2.domain.model.LoginResponseModel
import com.quantumai.co2.domain.model.RegisterRequestModel
import com.quantumai.co2.domain.model.ResetPasswordRequestModel
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface GlobalDataProvider {

    @POST("Customer/Login")
    suspend fun loginUser(
        @Body body: LoginRequestModel,
    ): LoginResponseModel

    @POST("Customer/CustomerRegistration")
    suspend fun registerUser(
        @Body body: RegisterRequestModel,
    ): String

    @POST("Customer/ForgotPassword")
    suspend fun forgotPassword(
        @Body body: ForgotPasswordRequestModel,
    ): String

    @POST("Customer/ResetPassword")
    suspend fun resetPassword(
        @Body body: ResetPasswordRequestModel,
    ): String

    @GET("Device/GetDevicePreviewDataByCustomerId")
    suspend fun getDevicesByCustomerId(
        @Query("customerId") customerId: String,
    ): List<DevicePreviewDto>

    @POST("Device/AddDeviceByImei")
    suspend fun addDeviceByImei(
        @Query("Imei") imei: String,
        @Body body: AddDeviceRequest,
    ): String
}
