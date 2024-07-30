package com.example.mviweatherapp.data.location

import android.Manifest
import android.app.Application
import android.content.Context
import android.content.pm.PackageManager
import android.location.LocationManager
import androidx.core.content.ContextCompat
import com.example.mviweatherapp.domain.location.LocationData
import com.example.mviweatherapp.domain.location.LocationTracker
import com.example.mviweatherapp.domain.util.Resource
import com.google.android.gms.location.FusedLocationProviderClient
import kotlinx.coroutines.suspendCancellableCoroutine
import javax.inject.Inject
import kotlin.coroutines.resume

class DefaultLocationTracker@Inject constructor(
    private val locationClient: FusedLocationProviderClient,
    private val applicationContext: Application
): LocationTracker{
    override suspend fun getCurrentLocation(): Resource<LocationData> {
        val hasAccessCoarseLocation = ContextCompat.checkSelfPermission(
            applicationContext,
            Manifest.permission.ACCESS_COARSE_LOCATION
        ) == PackageManager.PERMISSION_GRANTED

        val hasAccessFineLocation = ContextCompat.checkSelfPermission(
            applicationContext,
            Manifest.permission.ACCESS_FINE_LOCATION
        ) == PackageManager.PERMISSION_GRANTED

        val locationManager = applicationContext.getSystemService(Context.LOCATION_SERVICE) as LocationManager
        val isGPSEnabled = locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER)||
                locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)

        if(!hasAccessFineLocation || !hasAccessCoarseLocation || !isGPSEnabled){
            return Resource.Error(
                message = "GPS Location is not enabled"
            )
        }
        return suspendCancellableCoroutine { cont ->
            locationClient.lastLocation.apply {
                if(isComplete){
                    if(isSuccessful){
                        cont.resume(value = Resource.Success(
                            data = LocationData(latitude = result.latitude, longitude = result.longitude)
                        ))
                    }
                    else{
                        cont.resume(value = Resource.Error(
                            message = "Something went wrong in retrieving the location"
                        ))
                    }
                }
                addOnSuccessListener{
                    cont.resume(value = Resource.Success(
                        data = LocationData(latitude = it.latitude, longitude = it.longitude)
                    ))
                }
                addOnFailureListener{
                    cont.resume(value = Resource.Error(
                        message = "Something went wrong in retrieving the location"
                    ))
                }
                addOnCanceledListener {
                    cont.cancel()
                }
            }
        }
    }
}