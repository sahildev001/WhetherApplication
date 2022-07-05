package com.sahil.whetherapplication.domain.whether

data class WhetherInfo(
    val whetherDataPerDay: Map<Int,List<WhetherData>>,
    val currentWhetherData:WhetherData
)
