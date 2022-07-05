package com.sahil.whetherapplication.presentation

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sahil.whetherapplication.domain.location.LocationTracker
import com.sahil.whetherapplication.domain.repository.WhetherInterface
import com.sahil.whetherapplication.domain.util.Resourse
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WhetherViewModel @Inject constructor(
    private val repository:WhetherInterface,
    private val locationTracker: LocationTracker
):ViewModel(){

    var state by mutableStateOf(WhetherState())
    private set

    fun loadWhetherInfo(){
        viewModelScope.launch {
            state = state.copy(
                isLoading = true,
                error = null,
            )
            locationTracker.getCurrentLocation()?.let { location ->
                when(val result = repository.getWhetherData(latitude = location.latitude, longitude = location.longitude)){
                    is Resourse.Success->{
                        state = state.copy(
                            isLoading = false,
                            whetherInfo = result.data,
                            error = null,
                            longitude = location.longitude,
                            latitude = location.latitude
                        )
                    }
                    is Resourse.Error ->{
                        state= state.copy(
                            isLoading = false,
                            error = result.message,
                            whetherInfo = null
                        )
                    }

                }
            }/*?.run {
                state = state.copy(
                    isLoading = false,
                    error = "Couldn't retrive location"
                )
            }*/
        }
    }
}