package com.project.puppyplace.util.appTopBar

import androidx.lifecycle.ViewModel
import androidx.navigation.NavController
import com.project.puppyplace.navigation.Destination
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class AppTopBarViewModel @Inject constructor():ViewModel(){
        // TOP BAR ACTIONS
        fun onTopBarActionsPressed(navController: NavController) {
                navController.navigate(Destination.settings.route)
        }
        fun onTopBarNavigationPressed(navController: NavController){
                navController.popBackStack()
        }
}