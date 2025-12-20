package com.weatherApp.core.domain.usecase

import com.weatherApp.core.domain.repository.WeatherRepository

class SaveLocationUseCase (private val repository: WeatherRepository) {
    operator fun invoke(name: String,latitude: Double,longitude: Double){
        return repository.saveLocation(name,latitude,longitude)
    }
}