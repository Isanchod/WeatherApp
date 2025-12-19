package com.weatherApp.ui


import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.weatherApp.data.db.WeatherEntity
import kotlinx.coroutines.launch
import com.weatherApp.data.repository.WeatherRepository
import com.weatherApp.utils.ChartMode
import kotlinx.coroutines.Dispatchers

class WeatherViewModel(private val repository: WeatherRepository) : ViewModel() {
    private val _weather = MutableLiveData<List<WeatherEntity>>(emptyList())
    val weather: LiveData<List<WeatherEntity>> = _weather
    fun loadWeather(mode: ChartMode) {
        viewModelScope.launch(Dispatchers.IO) {
            if (mode == ChartMode.TODAY){
                _weather.postValue(repository.getTodayWeather())
            }else{
                _weather.postValue(repository.getWeatherData())
            }
        }
    }
    fun refreshWeather(lat: Float?, lon: Float?) {
        viewModelScope.launch {
            repository.fetchWeather(lat, lon)
        }
    }

    fun getLocation(): Triple<String?, Float?, Float?>{
        return repository.getSavedLocation()
    }
}
