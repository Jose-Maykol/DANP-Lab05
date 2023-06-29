package com.example.danp_lab05

sealed class AppScreens (
    val route: String
) {
    ///object LoginScreen: AppScreens("login_screen")
    ///object HomeScreen: AppScreens("home_screen")
    object RegisterScreen: AppScreens("register_screen")
    ///object DetailRegister: AppScreens("detail_screen/{id}")
}

