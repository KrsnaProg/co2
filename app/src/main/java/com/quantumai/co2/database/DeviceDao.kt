package com.quantumai.co2.database

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import kotlinx.coroutines.flow.Flow

@Dao
interface DeviceDao {

    @Query("SELECT * FROM devices ORDER BY name ASC")
    fun observeAll(): Flow<List<DeviceEntity>>

    @Query("SELECT * FROM devices WHERE id = :id")
    fun observeById(id: String): Flow<DeviceEntity?>

    @Query("SELECT * FROM devices WHERE id = :id")
    suspend fun getById(id: String): DeviceEntity?

    @Upsert
    suspend fun upsert(entity: DeviceEntity)

    @Upsert
    suspend fun upsertAll(entities: List<DeviceEntity>)

    @Query("SELECT COUNT(*) FROM devices")
    suspend fun count(): Int

    @Query("DELETE FROM devices WHERE id = :id")
    suspend fun deleteById(id: String)
}

