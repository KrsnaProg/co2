package com.quantumai.co2.database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "device_settings")
data class DeviceSettingsEntity(
    @PrimaryKey val deviceId: String,
    val name: String,
    val location: String,
    val alertGas: Boolean,
    val alertCo: Boolean,
    val alertSmoke: Boolean,
    val alertFlame: Boolean,
)

