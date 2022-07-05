package com.sahil.whetherapplication.presentation

import com.sahil.whetherapplication.domain.whether.WhetherInfo

data class WhetherState(
    val whetherInfo: WhetherInfo? = null,
    val isLoading:Boolean = false,
    val error:String?= null,
    val latitude:Double?= null,
    val longitude:Double?= null
)
