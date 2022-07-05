package com.sahil.whetherapplication.presentation

import android.Manifest
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContract
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.sahil.whetherapplication.presentation.ui.theme.DarkBlue
import com.sahil.whetherapplication.presentation.ui.theme.DeepBlue
import com.sahil.whetherapplication.presentation.ui.theme.WhetherApplicationTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
  private  val viewModel:WhetherViewModel by viewModels()
    private lateinit var permissionLauncher: ActivityResultLauncher<Array<String>>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        permissionLauncher = registerForActivityResult(
            ActivityResultContracts.RequestMultiplePermissions()
        ){
            viewModel.loadWhetherInfo()
        }
        permissionLauncher.launch(arrayOf(
            Manifest.permission.ACCESS_FINE_LOCATION,
            Manifest.permission.ACCESS_COARSE_LOCATION
        ))
        setContent {
            WhetherApplicationTheme {
               Box(modifier = Modifier.fillMaxSize()){

                   Column (
                       modifier = Modifier
                           .fillMaxSize()
                           .background(color = DarkBlue)
                           ){
                       WhetherCard(state = viewModel.state, backgroundColor = DeepBlue, context = this@MainActivity)
                       Spacer(modifier = Modifier.height(16.dp))
                       WhetherForecast(state = viewModel.state)

                       if(viewModel.state.isLoading) {
                           CircularProgressIndicator(
                               modifier = Modifier.align(Alignment.CenterHorizontally)
                           )
                       }
                       viewModel.state.error?.let { error ->
                           Text(
                               text = error,
                               color = Color.Red,
                               textAlign = TextAlign.Center,
                               modifier = Modifier.align(alignment = Alignment.CenterHorizontally)
                           )
                       }
                   }
               }
            }
        }
    }
}

@Composable
fun Greeting(name: String) {
    Text(text = "Hello $name!")
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    WhetherApplicationTheme {
        Greeting("Android")
    }
}



/*
*  Box(
                    modifier = Modifier.fillMaxSize()
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .background(DarkBlue)
                    ) {
                        WeatherCard(
                            state = viewModel.state,
                            backgroundColor = DeepBlue
                        )
                        Spacer(modifier = Modifier.height(16.dp))
                        WeatherForecast(state = viewModel.state)
                    }
                    if(viewModel.state.isLoading) {
                        CircularProgressIndicator(
                            modifier = Modifier.align(Alignment.Center)
                        )
                    }
                    viewModel.state.error?.let { error ->
                        Text(
                            text = error,
                            color = Color.Red,
                            textAlign = TextAlign.Center,
                            modifier = Modifier.align(Alignment.Center)
                        )
                    }*/