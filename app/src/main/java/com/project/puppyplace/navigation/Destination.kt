package com.project.puppyplace.navigation

sealed class Destination(val route: String){
    object loading : Destination("loadingScreen")
    object login: Destination("loginScreen")
    object signUp: Destination("signUpScreen")
    object home: Destination("homeScreen")
    object dogDetail: Destination("dogDetailScreen")
    object adoption: Destination("adoptionScreen")
    object like: Destination("likeScreen")
    object user: Destination("userScreen")
    object settings: Destination("settingsScreen")
}