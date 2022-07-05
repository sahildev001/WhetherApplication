package com.sahil.whetherapplication.di

import com.sahil.whetherapplication.data.location.DefaultLocationTracker
import com.sahil.whetherapplication.data.repository.WhetherRepositoryImpl
import com.sahil.whetherapplication.domain.location.LocationTracker
import com.sahil.whetherapplication.domain.repository.WhetherInterface
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    @Singleton
    abstract fun bindLocationTracker(
        whetherRepositoryImpl: WhetherRepositoryImpl
    ):WhetherInterface
}