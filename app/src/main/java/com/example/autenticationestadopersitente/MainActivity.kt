package com.example.autenticationestadopersitente

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.autenticationestadopersitente.ui.theme.AutenticationEstadoPersitenteTheme
import com.example.autenticationestadopersitente.ui2.screens.HomeScreen
import com.example.autenticationestadopersitente.ui2.screens.LoginScreen
import com.example.autenticationestadopersitente.ui2.screens.SplashScreen

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {

            val navController = rememberNavController()

            NavHost(
                navController = navController,
                startDestination = "splash"
            ) {
                composable("splash") {
                    SplashScreen(navController)
                }
                composable("login") {
                    LoginScreen(navController)
                }
                composable("home") {
                    HomeScreen(navController)
                }
            }

        }
    }
}