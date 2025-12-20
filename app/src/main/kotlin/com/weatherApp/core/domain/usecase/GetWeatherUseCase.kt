package com.weatherApp.core.domain.usecase

import com.weatherApp.core.domain.model.Weather
import com.weatherApp.core.domain.repository.WeatherRepository
import com.weatherApp.core.utils.ChartMode

class GetWeatherUseCase(private val repository: WeatherRepository) {

    suspend operator fun invoke(mode: ChartMode): List<Weather> {
        return if(mode == ChartMode.HISTORIC){
            repository.getHistoricWeather()
        }else{
            repository.getTodaysWeather()
        }
    }
}