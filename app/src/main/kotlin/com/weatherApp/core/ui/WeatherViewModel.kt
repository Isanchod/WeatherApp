package com.weatherApp.core.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.weatherApp.core.domain.model.Weather
import com.weatherApp.core.domain.usecase.FetchWeatherUseCase
import com.weatherApp.core.domain.usecase.GetLocationUseCase
import com.weatherApp.core.domain.usecase.GetWeatherUseCase
import com.weatherApp.core.domain.usecase.SaveLocationUseCase
import com.weatherApp.core.utils.ChartMode
import kotlinx.coroutines.launch
import kotlinx.coroutines.Dispatchers

class WeatherViewModel(
    private val getWeatherUseCase: GetWeatherUseCase,
    private val fetchWeatherUseCase: FetchWeatherUseCase,
    private val getLocationUseCase: GetLocationUseCase,
    private val saveLocationUseCase: SaveLocationUseCase
)
    : ViewModel() {

    private val _weather = MutableLiveData<List<Weather>>(emptyList())
    val weather: LiveData<List<Weather>> = _weather
    fun loadWeather(mode: ChartMode) {
        viewModelScope.launch(Dispatchers.IO) {
            _weather.postValue(getWeatherUseCase(mode))
        }
    }
    fun refreshWeather() {
        viewModelScope.launch {
            fetchWeatherUseCase()
        }
    }

    fun getLocation(): Triple<String?, Float?, Float?>{
        return getLocationUseCase()
    }

    fun saveLocation(name: String,latitude: Double,longitude: Double){
        saveLocationUseCase(name,latitude,longitude)
        return
    }
}
