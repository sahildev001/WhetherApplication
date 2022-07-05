package com.sahil.whetherapplication.domain.whether

import java.time.LocalDateTime

data class WhetherData(
    val temperatureCelsius: Double,
    val time:LocalDateTime,
    val pressure:Double,
    val windSpeed: Double,
    val humidity :Double,
    val whetherType: WhetherType
)
/*

val temperatureCelsius: Double,
val pressure: Double,
val windSpeed: Double,
val humidity: Double,
val weatherType: WeatherType*/
