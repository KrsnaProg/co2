package com.quantumai.co2.database

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    entities = [DeviceSettingsEntity::class, DeviceEntity::class],
    version = 2,
    exportSchema = false
)
abstract class CO2Database : RoomDatabase() {
    abstract fun deviceSettingsDao(): DeviceSettingsDao
    abstract fun deviceDao(): DeviceDao
}
