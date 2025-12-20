package com.weatherApp.core.di

import com.weatherApp.core.data.remote.api.WeatherApiService
import com.weatherApp.core.data.local.db.WeatherDatabase
import com.weatherApp.core.data.remote.firebase.FirebaseService
import com.weatherApp.core.ui.WeatherViewModel
import com.weatherApp.core.domain.datasource.PreferencesManager
import com.weatherApp.core.domain.repository.WeatherRepository
import com.weatherApp.core.domain.repository.WeatherRepositoryImpl
import com.weatherApp.core.domain.usecase.FetchWeatherUseCase
import com.weatherApp.core.domain.usecase.GetLocationUseCase
import com.weatherApp.core.domain.usecase.GetWeatherUseCase
import com.weatherApp.core.domain.usecase.SaveLocationUseCase
import org.koin.android.ext.koin.androidContext
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val appModule = module {
    single {
        WeatherDatabase.getInstance(androidContext())
    }
    single {
        get<WeatherDatabase>().weatherDao()
    }
    single {
        FirebaseService()
    }
    single {
        PreferencesManager(androidContext())
    }
    single {
        Retrofit.Builder()
            .baseUrl("https://api.open-meteo.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
    single<WeatherApiService> {
        get<Retrofit>().create(WeatherApiService::class.java)
    }
    single {
        WeatherRepositoryImpl(
            dao = get(),
            api = get(),
            prefs = get(),
            firebase = get()
        )as WeatherRepository
    }
    single {
        GetWeatherUseCase(
            repository = get(),
        )
    }
    single {
        FetchWeatherUseCase(
            repository = get(),
        )
    }
    single {
        GetLocationUseCase(
            repository = get(),
        )
    }

    single {
        SaveLocationUseCase(
            repository = get(),
        )
    }

    viewModel {
        WeatherViewModel(
            getWeatherUseCase = get(),
            fetchWeatherUseCase = get(),
            getLocationUseCase = get(),
            saveLocationUseCase = get()
        )
    }}
