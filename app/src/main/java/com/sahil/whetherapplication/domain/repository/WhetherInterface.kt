package com.sahil.whetherapplication.domain.repository

import com.sahil.whetherapplication.domain.util.Resourse
import com.sahil.whetherapplication.domain.whether.WhetherInfo

interface WhetherInterface {

    suspend fun getWhetherData( latitude:Double,longitude:Double):Resourse<WhetherInfo>

}