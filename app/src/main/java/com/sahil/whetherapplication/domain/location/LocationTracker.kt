package com.sahil.whetherapplication.domain.location

import android.location.Location

interface LocationTracker {
    suspend fun getCurrentLocation():Location?
}