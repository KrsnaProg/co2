package com.quantumai.co2.domain.model

import com.google.gson.annotations.SerializedName

data class RegisterRequestModel(
    @SerializedName("fullName") val fullName: String,
    @SerializedName("phoneNumber") val phoneNumber: String,
    @SerializedName("email") val email: String,
    @SerializedName("password") val password: String,
)

