package com.weatherApp.core.domain.datasource

import android.content.Context
import android.content.SharedPreferences

class PreferencesManager(context: Context) {
    private val prefs: SharedPreferences =
        context.getSharedPreferences("weather_prefs", Context.MODE_PRIVATE)

    fun saveLocation(city: String, latitude: Double, longitude: Double) {
        prefs.edit().apply {
            putString("city_name", city)
            putFloat("latitude", latitude.toFloat())
            putFloat("longitude", longitude.toFloat())
            apply()
        }
    }

    fun getCityName(): String? = prefs.getString("city_name", null)
    fun getLatitude(): Float? = if (prefs.contains("latitude")) prefs.getFloat("latitude", 0f) else null
    fun getLongitude(): Float? = if (prefs.contains("longitude")) prefs.getFloat("longitude", 0f) else null
}