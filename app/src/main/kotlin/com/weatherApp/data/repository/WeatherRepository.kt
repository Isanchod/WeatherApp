package com.weatherApp.data.repository

import androidx.lifecycle.LiveData
import com.weatherApp.data.api.WeatherApiService
import com.weatherApp.data.db.WeatherDao
import com.weatherApp.data.db.WeatherEntity
import com.weatherApp.data.firebase.FirebaseService
import com.weatherApp.utils.PreferencesManager

class WeatherRepository(
    private val dao: WeatherDao,
    private val api: WeatherApiService,
    private val prefs: PreferencesManager,
    private val firebase: FirebaseService
) {

    fun getSavedLocation(): Triple<String?, Float?, Float?> {
        val name = prefs.getCityName()
        val latitude = prefs.getLatitude()
        val longitude = prefs.getLongitude()
        return Triple(name,latitude,longitude)
    }

    fun saveLocation(name: String,latitude: Double,longitude: Double) {
        prefs.saveLocation(name,latitude,longitude)
    }

    suspend fun fetchWeather(latitude: Float?, longitude: Float?) {
        val response = api.getWeather(latitude, longitude)
        val temperatures = response.hourly.temperature_2m.mapIndexed { index, temp ->
            WeatherEntity(
                id = 0,
                temperature = temp,
                time = response.hourly.time[index]
            )
        }
        dao.clearDatabase()
        dao.insertWeatherData(temperatures)
        syncWithFirebase(temperatures)
    }

    fun getTodayWeather():List<WeatherEntity> {
        return dao.retrieveWeatherData()
    }

    suspend fun getWeatherData(): List<WeatherEntity> {
        return firebase.getWeatherData()
    }

    private suspend fun syncWithFirebase(data: List<WeatherEntity>) {
        val existingIds = firebase.getExistingWeatherIds()

        data.forEach { entity ->
            val id = entity.time
            if (id in existingIds) {
                firebase.addWeatherData(entity)
            }
        }
    }
}

