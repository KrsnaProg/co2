package com.quantumai.co2.domain.model

import com.google.gson.annotations.SerializedName

data class LoginRequestModel(
    @SerializedName("email") val email: String,
    @SerializedName("password") val password: String,
)

