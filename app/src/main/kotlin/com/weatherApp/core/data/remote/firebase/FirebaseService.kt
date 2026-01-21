package com.weatherApp.core.data.remote.firebase

import com.google.firebase.database.FirebaseDatabase
import com.weatherApp.core.domain.model.Weather
import kotlinx.coroutines.tasks.await

class FirebaseService {

    private val database = FirebaseDatabase.getInstance("https://weatherapp-3b321-default-rtdb.europe-west1.firebasedatabase.app/")
    private val weatherRef = database.getReference("weather")

    suspend fun getWeatherData(): List<Weather> {
        val snapshot = weatherRef.get().await()
        return snapshot.children.mapNotNull {
            it.getValue(Weather::class.java)
        }.sortedBy { it.time }
    }

    suspend fun getExistingWeatherIds(): Set<String> {
        val snapshot = weatherRef.get().await()
        return snapshot.children.mapNotNull {
            it.key
        }.toSet()
    }

    suspend fun addWeatherData(weatherData: Weather) {
        try {
            val time = weatherData.time
            if (time != null) {
                weatherRef.child(time).setValue(weatherData).await()
            }
        } catch (e: Exception) {
            throw e
            }
        }
    }

