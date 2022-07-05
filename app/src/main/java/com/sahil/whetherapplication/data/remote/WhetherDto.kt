package com.sahil.whetherapplication.data.remote

import com.squareup.moshi.Json

data class WhetherDto(
    @field:Json(name = "hourly")
    val weatherData: WhetherDataDto
)
