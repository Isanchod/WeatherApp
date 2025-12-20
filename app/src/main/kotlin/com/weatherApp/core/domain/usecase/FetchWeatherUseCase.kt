package com.weatherApp.core.domain.usecase

import com.weatherApp.core.domain.repository.WeatherRepository

class FetchWeatherUseCase(private val repository: WeatherRepository) {

    suspend operator fun invoke() {
        repository.fetchWeather()
        return
    }
}