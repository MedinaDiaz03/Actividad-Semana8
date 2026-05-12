package com.example.autenticationestadopersitente.ui2.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.autenticationestadopersitente.ui2.viewmodels.AuthViewModel

@Composable
fun HomeScreen(
    navController: NavController,
    viewModel: AuthViewModel = viewModel()
) {
    val email by viewModel.userEmail.collectAsState()

    Scaffold { padding ->
        Column(
            modifier = Modifier.padding(padding).padding(16.dp)
        ) {
            Text("Hola $email")

            Button(onClick = {
                viewModel.logout()
                navController.navigate("login") {
                    popUpTo("home") { inclusive = true }
                }
            }) {
                Text("Cerrar Sesión")
            }
        }
    }
}