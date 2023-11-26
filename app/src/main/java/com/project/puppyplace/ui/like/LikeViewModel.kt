package com.project.puppyplace.ui.like

import androidx.lifecycle.ViewModel
import androidx.navigation.NavController
import com.project.puppyplace.navigation.Destination

class LikeViewModel(): ViewModel() {

    fun BackHome(navController: NavController){
        navController.navigate(Destination.home.route)
    }
}