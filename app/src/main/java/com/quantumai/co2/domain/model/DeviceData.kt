package com.quantumai.co2.domain.model

data class DeviceData(
    val imei: String,
    val readings: List<Reading>
)