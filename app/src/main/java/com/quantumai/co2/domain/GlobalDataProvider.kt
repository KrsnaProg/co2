package com.quantumai.co2.domain

import com.quantumai.co2.domain.model.LoginResponseModel
import com.quantumai.co2.domain.model.RegisterRequestModel
import retrofit2.http.Body
import retrofit2.http.POST
import retrofit2.http.Query

interface GlobalDataProvider {

    @POST("Customer/Login")
    suspend fun loginUser(
        @Query("email") email: String,
        @Query("password") password: String,
    ): LoginResponseModel

    @POST("Customer/CustomerRegistration")
    suspend fun registerUser(
        @Body body: RegisterRequestModel,
    ): String
}
