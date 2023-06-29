package com.example.danp_lab05

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

import com.example.danp_lab05.screens.dataRegistro

@Composable
fun AppNavigation() {
    val navController: NavHostController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = AppScreens.RegisterScreen.route
        ///startDestination = AppScreens.LoginScreen.route
    ) {
        /*composable(AppScreens.HomeScreen.route) {
            HomeScreen(navController = navController)
        }*/
        composable(AppScreens.RegisterScreen.route) {
            dataRegistro(navController = navController)
        }
    }
}

