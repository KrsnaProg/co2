package com.quantumai.co2.database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "devices")
data class DeviceEntity(
    @PrimaryKey val id: String,
    val name: String,
    val location: String,
    val isOnline: Boolean,
    val imei: String = "",
)

