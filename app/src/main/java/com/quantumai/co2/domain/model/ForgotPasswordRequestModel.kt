package com.quantumai.co2.domain.model

import com.google.gson.annotations.SerializedName

data class ForgotPasswordRequestModel(
    @SerializedName("emailAddress") val emailAddress: String,
)

