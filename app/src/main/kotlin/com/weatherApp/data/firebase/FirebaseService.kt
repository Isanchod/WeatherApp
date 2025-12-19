package com.weatherApp.data.firebase

import com.google.firebase.database.FirebaseDatabase
import com.weatherApp.data.db.WeatherEntity
import kotlinx.coroutines.tasks.await

class FirebaseService {

    private val db = FirebaseDatabase.getInstance("https://mi-proyecto-con-firebase-default-rtdb.europe-west1.firebasedatabase.app/")
    private val weatherRef = db.getReference("weatherData")

    suspend fun getWeatherData(): List<WeatherEntity> {
        val snapshot = weatherRef.orderByChild("time").get().await()
        return snapshot.children.mapNotNull {
            it.getValue(WeatherEntity::class.java)?.copy()
        }
    }

    suspend fun getExistingWeatherIds(): Set<String> {
        val snapshot = weatherRef
            .orderByChild("time")
            .get()
            .await()

        return snapshot.children.mapNotNull { it.key }.toSet()
    }


    suspend fun addWeatherData(weatherEntity: WeatherEntity) {
        try {
            weatherRef.child(weatherEntity.time).setValue(weatherEntity).await()
        } catch (e: Exception) {
            throw e
        }
    }
}

