package com.quantumai.co2.domain.model

import com.google.gson.annotations.SerializedName

data class DevicePreviewDto(
    @SerializedName("id") val id: String = "",
    @SerializedName("name") val name: String = "",
    @SerializedName("location") val location: String = "",
    @SerializedName("isOnline") val isOnline: Boolean = false,
    @SerializedName("imei") val imei: String = "",
)

