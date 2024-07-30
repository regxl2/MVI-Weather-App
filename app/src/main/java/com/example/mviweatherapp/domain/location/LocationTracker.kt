package com.example.mviweatherapp.domain.location

import com.example.mviweatherapp.domain.util.Resource

interface LocationTracker {
    suspend fun getCurrentLocation(): Resource<LocationData>
}