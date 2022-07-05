package com.sahil.whetherapplication.data.mappers

import android.os.Build
import androidx.annotation.RequiresApi
import com.sahil.whetherapplication.data.remote.WhetherDataDto
import com.sahil.whetherapplication.data.remote.WhetherDto
import com.sahil.whetherapplication.domain.whether.WhetherData
import com.sahil.whetherapplication.domain.whether.WhetherInfo
import com.sahil.whetherapplication.domain.whether.WhetherType
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter


private data class IndexedWhetherData(
    val index:Int,
    val data: WhetherData
)

fun WhetherDataDto.toWhetherDataMap(): Map<Int,List<WhetherData>>{
    return time.mapIndexed{ index,time ->
        val temprature = temperatures[index]
        val whetherCode =weatherCodes[index]
        val pressure = pressures[index]
        val windSpeed = windSpeeds[index]
        val   humidity = humidities[index]
      IndexedWhetherData(index,
          WhetherData(
              time = LocalDateTime.parse(time),
              humidity = humidity,
              whetherType = WhetherType.fromWMO(whetherCode),
              pressure = pressure,
              temperatureCelsius = temprature,
              windSpeed = windSpeed
          ))
    }.groupBy {
        it.index /24
    }.mapValues {
        it.value.map {
            it.data
        }
    }.also {
        println("${it.keys} --  ${it.values}")
    }
}


fun WhetherDto.toWhetherInfo(): WhetherInfo? {
   val whetherDataMap = weatherData.toWhetherDataMap()
    val now = LocalDateTime.now()
    val currentWhetherData = whetherDataMap[0]?.find {
        val hour = if(now.minute<30) now.hour else now.hour +1
        it.time.hour == hour
    }
    return currentWhetherData?.let {
        WhetherInfo(
        whetherDataPerDay = whetherDataMap,
        currentWhetherData = it,

            )
    }

}