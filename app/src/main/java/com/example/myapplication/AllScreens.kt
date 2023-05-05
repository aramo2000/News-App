package com.example.myapplication

sealed class AllScreens(val route: String) {
    object HomeScreen : AllScreens("home_screen")
    object DetailScreen : AllScreens("detail_screen")
}