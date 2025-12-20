package com.weatherApp.core.data.remote.firebase

import com.google.firebase.database.FirebaseDatabase
import com.weatherApp.core.domain.model.Weather
import kotlinx.coroutines.tasks.await

class FirebaseService {

    private val db = FirebaseDatabase.getInstance("https://mi-proyecto-con-firebase-default-rtdb.europe-west1.firebasedatabase.app/")
    private val weatherRef = db.getReference("weatherData")

    suspend fun getWeatherData(): List<Weather> {
        val snapshot = weatherRef.orderByChild("time").get().await()
        return snapshot.children.mapNotNull {
            it.getValue(Weather::class.java)?.copy()
        }
    }

    suspend fun getExistingWeatherIds(): Set<String> {
        val snapshot = weatherRef
            .orderByChild("time")
            .get()
            .await()

        return snapshot.children.mapNotNull { it.key }.toSet()
    }


    suspend fun addWeatherData(weatherData: Weather) {
        try {
            weatherRef.child(weatherData.time).setValue(weatherData).await()
        } catch (e: Exception) {
            throw e
        }
    }
}

