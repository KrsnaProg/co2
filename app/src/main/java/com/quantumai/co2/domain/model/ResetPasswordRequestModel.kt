package com.quantumai.co2.domain.model

import com.google.gson.annotations.SerializedName

data class ResetPasswordRequestModel(
    @SerializedName("verificationCode") val verificationCode: String,
    @SerializedName("newPassword") val newPassword: String,
    @SerializedName("confirmPassword") val confirmPassword: String,
)

