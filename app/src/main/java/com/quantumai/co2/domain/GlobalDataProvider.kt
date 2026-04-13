package com.quantumai.co2.domain

import com.quantumai.co2.domain.model.ForgotPasswordRequestModel
import com.quantumai.co2.domain.model.LoginRequestModel
import com.quantumai.co2.domain.model.LoginResponseModel
import com.quantumai.co2.domain.model.RegisterRequestModel
import retrofit2.http.Body
import retrofit2.http.POST

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
}
