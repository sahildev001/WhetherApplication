package com.sahil.whetherapplication.presentation

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.width
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.sahil.whetherapplication.domain.whether.WhetherData
import java.time.format.DateTimeFormatter


@Composable
fun HourlyWeatherDisplay(
    whetherData: WhetherData,
    modifier: Modifier = Modifier,
    textColor: Color = Color.White
) {
   val formattedTime = remember(whetherData) {
       whetherData.time.format(
           DateTimeFormatter.ofPattern("HH:mm")
       )
   }
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        Text(text = formattedTime,
        color = Color.LightGray)
        Image(painter = painterResource(id = whetherData.whetherType.iconRes),
            contentDescription = whetherData.whetherType.whetherDesc,
        modifier = Modifier.width(40.dp))
        Text(
            text = "${whetherData.temperatureCelsius}°C",
            color = textColor,
            fontWeight = FontWeight.Bold
        )

    }
}
/*

            painter = painterResource(id = weatherData.weatherType.iconRes),
            contentDescription = null,
            modifier = Modifier.width(40.dp)
        )
        Text(
            text = "${weatherData.temperatureCelsius}°C",
            color = textColor,
            fontWeight = FontWeight.Bold
        )
    }
}*/
