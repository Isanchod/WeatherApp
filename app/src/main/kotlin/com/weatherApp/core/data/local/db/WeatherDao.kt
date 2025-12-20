package com.weatherApp.core.data.local.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface WeatherDao {
    @Insert()
    suspend fun insertWeatherData(data: List<WeatherEntity>)

    @Query("SELECT * FROM weather_data ORDER BY time ASC")
    fun retrieveWeatherData(): List<WeatherEntity>

    @Query("DELETE FROM weather_data")
    suspend fun clearDatabase()
}
