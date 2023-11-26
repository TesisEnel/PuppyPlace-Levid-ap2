package com.project.puppyplace.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.project.puppyplace.ui.LoadingScreen.LoadingScreen
import com.project.puppyplace.ui.dogDetail.DogDetailScreen
import com.project.puppyplace.ui.home.HomeScreen
import com.project.puppyplace.ui.like.LikeScreen
import com.project.puppyplace.ui.login.LoginScreen
import com.project.puppyplace.ui.signup.SignUpScreen

@Composable
fun NavigationHost() {
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = Destination.login.route
    ){
        composable(Destination.loading.route){
            LoadingScreen(navController = navController)
        }
        composable(Destination.login.route){
            LoginScreen(navController = navController)
        }
        composable(Destination.signUp.route){
            SignUpScreen(navController = navController)
        }
        composable(Destination.home.route){
            HomeScreen(navController = navController)
        }
        composable(Destination.dogDetail.route){
            DogDetailScreen(navController = navController)
        }
        composable(Destination.like.route){
            LikeScreen(navController = navController)
        }
    }
}