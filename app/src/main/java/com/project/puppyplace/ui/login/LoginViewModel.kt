package com.project.puppyplace.ui.login

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.navigation.NavController
import com.google.firebase.auth.FirebaseAuth
import com.project.puppyplace.data.remote.dto.UserDto
import com.project.puppyplace.data.repository.LoginRepository
import com.project.puppyplace.di.AppModule.userLoged
import com.project.puppyplace.navigation.Destination
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.runBlocking
import retrofit2.HttpException
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val loginRepository: LoginRepository
): ViewModel(){
    var email by mutableStateOf("")
    var password by mutableStateOf("")
    var user by mutableStateOf(UserDto())
    var hidePassword by mutableStateOf(true)

    var goodEmail by mutableStateOf(false)
    var goodPassword by mutableStateOf(false)
    //Error messages
    var emailError by mutableStateOf("")
    var passwordError by mutableStateOf("")

    //main
    fun logIn(navController: NavController){
        validateEmailData(this.email)
        validatePasswordData(this.password)
        if(goodEmail && goodPassword){
            FirebaseAuth.getInstance().signInWithEmailAndPassword(email, password)
                .addOnCompleteListener{
                    if(it.isSuccessful){
                        userLoged = user
                        Log.d("LOGIN", "Successful")
                        navController.navigate(Destination.home.route)
                    }else{
                        Log.d("LOGIN", "Failed")
                    }
                }
        }
        else{
            Log.d("LOGIN", "Invalid data")
        }
    }



    //onChage events
    fun onEmailChange(email: String){
        this.email = email
        emailError = if(email.isEmpty()){
            "Email is required"
        }else{
            ""
        }
    }
    private fun validateEmailData(email: String): Boolean {
        if (email.isEmpty()) {
            emailError = "Email is required"
            return false
        } else if (!android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            emailError = "Invalid email."
            return false
        }

        runBlocking {
            try {
                user = loginRepository.getUserByEmail(email)
                Log.d("USER", "${user.email} - ${user.password}")
                goodEmail = true
            } catch (e: HttpException) {
                emailError = "Email not registered."
                Log.d("HTTP E", "${e.message} (${e.code()})")
                goodEmail = false
            }
        }
        return goodEmail
    }

    private fun validatePasswordData(password: String): Boolean {
        if (password.isEmpty()) {
            passwordError = "Password is required."
            return false
        }

        runBlocking {
            try {
                if (password == user.password) {
                    goodPassword = true
                    passwordError = ""
                } else {
                    goodPassword = false
                    passwordError = "Incorrect password."
                }
            } catch (e: Exception) {
                Log.d("PASS EXCEPTION", "${e.message}")
            }
        }
        return goodPassword
    }

    fun onPasswordChange(password: String){
        this.password = password
        passwordError = if(password.isEmpty()){
            "Password is required"
        }else{
            ""
        }
    }
    fun onShowPasswordClick(){
        hidePassword = !hidePassword
    }
}