package com.quantumai.co2.database

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import kotlinx.coroutines.flow.Flow

@Dao
interface DeviceSettingsDao {

    @Query("SELECT * FROM device_settings WHERE deviceId = :deviceId")
    fun observeById(deviceId: String): Flow<DeviceSettingsEntity?>

    @Query("SELECT * FROM device_settings WHERE deviceId = :deviceId")
    suspend fun getById(deviceId: String): DeviceSettingsEntity?

    @Upsert
    suspend fun upsert(entity: DeviceSettingsEntity)

    @Query("DELETE FROM device_settings WHERE deviceId = :deviceId")
    suspend fun deleteById(deviceId: String)
}

