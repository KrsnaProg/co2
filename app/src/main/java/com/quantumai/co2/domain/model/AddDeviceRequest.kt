package com.quantumai.co2.domain.model

import com.google.gson.annotations.SerializedName

data class AddDeviceRequest(
    @SerializedName("deviceName") val deviceName: String,
    @SerializedName("deviceAddress") val deviceAddress: String,
    @SerializedName("latitude") val latitude: Double,
    @SerializedName("longitude") val longitude: Double,
)

