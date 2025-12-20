package com.weatherApp.core.domain.repository

import com.weatherApp.core.domain.model.Weather
interface WeatherRepository {
    fun getSavedLocation(): Triple<String?, Float?, Float?>
    fun saveLocation(name: String,latitude: Double,longitude: Double)
    suspend fun fetchWeather()
    suspend fun getTodaysWeather(): List<Weather>
    suspend fun getHistoricWeather(): List<Weather>
}