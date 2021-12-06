package com.machmudow.weatherapp.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.Scaffold
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.machmudow.weatherapp.presentation.forecast.ForecastScreen
import com.machmudow.weatherapp.presentation.theme.WeatherTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        installSplashScreen()

        setContent {
            val navController = rememberNavController()

            WeatherTheme {
                Scaffold {
                    NavHost(
                        navController = navController,
                        startDestination = Route.Forecast.route,
                    ) {
                        composable(route = Route.Forecast.route) {
                            ForecastScreen()
                        }
                    }
                }
            }
        }
    }
}
