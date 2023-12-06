package com.project.puppyplace.ui.settings

import androidx.lifecycle.ViewModel
import androidx.navigation.NavController
import com.google.firebase.auth.FirebaseAuth
import com.project.puppyplace.di.AppModule
import com.project.puppyplace.navigation.Destination
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SettingsViewModel @Inject constructor(

) : ViewModel(){
    fun onTopBarActionsPressed(navController: NavController) {
        FirebaseAuth.getInstance().signOut()
        AppModule.userLoged = null
        navController.navigate(Destination.login.route) {
            popUpTo(Destination.home.route) {
                inclusive = true
            }
        }
    }
}