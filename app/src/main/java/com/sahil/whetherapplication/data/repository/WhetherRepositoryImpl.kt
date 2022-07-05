package com.sahil.whetherapplication.data.repository

import com.sahil.whetherapplication.data.mappers.toWhetherInfo
import com.sahil.whetherapplication.data.remote.WhetherApi
import com.sahil.whetherapplication.domain.repository.WhetherInterface
import com.sahil.whetherapplication.domain.util.Resourse
import com.sahil.whetherapplication.domain.whether.WhetherInfo
import javax.inject.Inject


class WhetherRepositoryImpl @Inject constructor( private val api:WhetherApi):WhetherInterface {

    override suspend fun getWhetherData(
        latitude: Double,
        longitude: Double
    ): Resourse<WhetherInfo> {
      return try{
         Resourse.Success(data = api.getWhetherData(
             latitude,longitude
         ).toWhetherInfo()
         )
      }catch (e:Exception){
          e.printStackTrace()
          Resourse.Error(e.message ?:"An unknown error")
      }
    }
}