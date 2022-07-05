package com.sahil.whetherapplication.data.remote

import retrofit2.http.GET
import retrofit2.http.Query

interface WhetherApi {
    @GET("v1/forecast?hourly=temperature_2m,weathercode,relativehumidity_2m,windspeed_10m,pressure_msl")
    suspend fun getWhetherData(
        @Query("latitude") lat:Double,
        @Query("longitude") lng:Double
    ):WhetherDto
}