package com.project.puppyplace.util.BottomBar

import androidx.lifecycle.ViewModel
import androidx.navigation.NavController
import com.project.puppyplace.navigation.Destination
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class AppBottomBarViewModel @Inject constructor(
): ViewModel(){

    // BOTTOM BAR ACTIONS
    fun onBottomBarHomePressed(navController: NavController) {
        val currentDestination = navController.currentDestination?.route
        if (currentDestination != Destination.home.route) {
            navController.navigate(Destination.home.route)
        }
    }

    fun onBottomBarLikePressed(navController: NavController){
        val currentDestination = navController.currentDestination?.route
        if(currentDestination != Destination.like.route){
            navController.navigate(Destination.like.route)
        }
    }
    fun onBottomBarAppointmentPressed(navController: NavController){
        val currentDestination = navController.currentDestination?.route
        if(currentDestination != Destination.appointment.route){
            navController.navigate(Destination.appointment.route)
        }
    }
}