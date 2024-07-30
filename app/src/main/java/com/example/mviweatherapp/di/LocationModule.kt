package com.example.mviweatherapp.di

import com.example.mviweatherapp.data.location.DefaultLocationTracker
import com.example.mviweatherapp.domain.location.LocationTracker
import dagger.Binds
import dagger.BindsInstance
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class LocationModule {
    @Singleton
    @Binds
    abstract fun bindLocationTracker(locationTracker: DefaultLocationTracker): LocationTracker
}