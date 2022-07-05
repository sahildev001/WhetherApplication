package com.sahil.whetherapplication.presentation

import android.content.Context
import android.location.Address
import android.location.Geocoder
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.sahil.whetherapplication.R
import java.time.format.DateTimeFormatter
import java.util.*
import kotlin.math.roundToInt


fun getLocationName(lat:Double,lng:Double,context:Context):String {
    val myLocation = Geocoder(context, Locale.getDefault())
    val myList: List<Address> =
        myLocation.getFromLocation(lat.toDouble(), lng.toDouble(), 1)
    val address: Address = myList[0] as Address
    return address.locality
}

@Composable
fun WhetherCard(
    state: WhetherState,
    backgroundColor:Color,
    context: Context
) {
    state.whetherInfo?.currentWhetherData?.let { data ->
        Card(
            backgroundColor = backgroundColor,
            shape = RoundedCornerShape(10.dp),
            modifier = Modifier.padding(16.dp)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Row(
                    horizontalArrangement = Arrangement.SpaceBetween,
                    modifier = Modifier.fillMaxWidth()
                ) {

                    Text(text = "${state.latitude?.let { state.longitude?.let { it1 ->
                        getLocationName(it,
                            it1,context)
                    } }}",

                        fontSize = 9.sp,

                        color = Color.White,
                    textAlign = TextAlign.Center)

                    Text(
                        "Today ${
                            data.time.format(
                                DateTimeFormatter.ofPattern("HH:mm")
                            )
                        }",
                        textAlign = TextAlign.Center,

                        color = Color.White
                    )
                }

                Spacer(modifier = Modifier.height(16.dp))

                Image(
                    painter = painterResource(id = data.whetherType.iconRes),
                    contentDescription = data.whetherType.whetherDesc,
                 modifier = Modifier.width(200.dp))

                Spacer(modifier = Modifier.height(16.dp))

                Text(text = "${data.temperatureCelsius} C",
                fontSize = 50.sp,
                color = Color.White)

                Spacer(modifier = Modifier.height(16.dp))

                Text(text = "${data.whetherType.whetherDesc}",
                fontSize = 20.sp,
                color = Color.White)

                Spacer(modifier = Modifier.height(32.dp))

              Row(
                  modifier = Modifier.fillMaxWidth(),
                 horizontalArrangement = Arrangement.SpaceAround
              ){
                  WhetherDataDisplay(
                      value = data.pressure.roundToInt(),
                      unit = "hpa",
                      icon = ImageVector.vectorResource(id = R.drawable.ic_pressure),
                  iconTint = Color.White,
                      textStyle = TextStyle(color = Color.White)
                  )
                  WhetherDataDisplay(
                      value = data.humidity.roundToInt(),
                      unit = "%",
                      icon = ImageVector.vectorResource(id = R.drawable.ic_drop),
                      iconTint = Color.White,
                      textStyle = TextStyle(color = Color.White)
                  )
                  WhetherDataDisplay(
                      value = data.windSpeed.roundToInt(),
                      unit = "km/h",
                      icon = ImageVector.vectorResource(id = R.drawable.ic_wind),
                      iconTint = Color.White,
                      textStyle = TextStyle(color = Color.White)
                  )

              }






            }

        }
    }


    
}