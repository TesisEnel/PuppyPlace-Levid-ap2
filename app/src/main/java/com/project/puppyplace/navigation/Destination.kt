package com.project.puppyplace.navigation

sealed class Destination(val route: String){
    object loading : Destination("loadingScreen")
    object login: Destination("loginScreen")
    object signUp: Destination("signUpScreen")
    object home: Destination("homeScreen")
}