package com.sahil.whetherapplication.di

import android.app.Application
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.sahil.whetherapplication.data.remote.WhetherApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.create
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun ProvideWhetherApi():WhetherApi{
        return Retrofit.Builder()
            .baseUrl("https://api.open-meteo.com")
            .addConverterFactory(MoshiConverterFactory.create())
            .build()
            .create()
    }


    @Provides
    @Singleton
    fun provideFusedLocationProvoderClient(app:Application):FusedLocationProviderClient{
        return LocationServices.getFusedLocationProviderClient(app)
    }

}