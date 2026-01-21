package com.weatherApp.core.data.remote.firebase

import com.google.firebase.database.FirebaseDatabase
import com.weatherApp.core.domain.model.Weather
import kotlinx.coroutines.tasks.await

class FirebaseService {

    //TODO definir referencia e instancia de BBDD

    /**
     * TODO obtiene todos los registros almacenados en la BBDD, ordenados por fecha y hora
     * Devuelve un listado de Weather
     */
    suspend fun getWeatherData(): List<Weather> {
        return emptyList()
    }

    /**
     * TODO obtiene todos los IDs (fecha/hora) de los registros almacenados en la BBDD
     * No es necesario implementarla si no la necesitáis en vuestra solución
     */
    suspend fun getExistingWeatherIds(): Set<String> {
        return emptySet()
    }

    /**
     * TODO añade un nuevo registro a la BBDD
     * La clave del registro debe ser la fecha y hora
     */
    suspend fun addWeatherData(weatherData: Weather) {
    }
}

