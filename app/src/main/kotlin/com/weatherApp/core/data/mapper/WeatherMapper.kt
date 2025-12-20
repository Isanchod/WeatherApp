package com.weatherApp.core.data.mapper

import com.weatherApp.core.data.local.db.WeatherEntity
import com.weatherApp.core.domain.model.Weather

fun WeatherEntity.toDomain() = Weather(
    id = this.id,
    time = this.time,
    temperature = this.temperature
)

fun Weather.toEntity() = WeatherEntity(
    id = this.id,
    time = this.time,
    temperature = this.temperature
)