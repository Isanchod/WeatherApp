package com.weatherApp.core.domain.usecase

import com.weatherApp.core.domain.repository.WeatherRepository

class GetLocationUseCase(private val repository: WeatherRepository) {

    operator fun invoke(): Triple<String?, Float?, Float?> {
        return repository.getSavedLocation()
    }
}