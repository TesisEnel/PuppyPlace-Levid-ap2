package com.project.puppyplace.ui.signup

import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import com.google.firebase.auth.FirebaseAuth
import com.project.puppyplace.data.remote.dto.UserDto
import com.project.puppyplace.data.repository.SignUpRepository
import com.project.puppyplace.navigation.Destination
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SignUpViewModel @Inject constructor(
    private val signUpRepository: SignUpRepository
): ViewModel() {
    var name by mutableStateOf("")
    var surname by mutableStateOf("")
    var email by mutableStateOf("")
    var password by mutableStateOf("")

    //Error messages
    var nameError by mutableStateOf("")
    var surnameError by mutableStateOf("")
    var emailError by mutableStateOf("")
    var passwordError by mutableStateOf("")

    var hidePassword by mutableStateOf(true)
    //Snackbarh
    val snackbarHostState by mutableStateOf(SnackbarHostState())

    var isLoading by mutableStateOf(false)
    var loadingMessage by mutableStateOf("")
    fun showSnackBar(){
        viewModelScope.launch {
            snackbarHostState.showSnackbar("Test message")
        }
    }

    //SignUp
    fun onSignUpClick(navController: NavController){
        if(isValid()){
            isLoading = true
            loadingMessage = "Checking data..."
            FirebaseAuth.getInstance()
            .createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener{
                isLoading = false
                if(it.isSuccessful){
                    isLoading = true
                    loadingMessage = "Redirecting..."
                    showSnackBar()
                    viewModelScope.launch{
                        signUpRepository.createUser(
                            UserDto(
                                name = name,
                                surname = surname,
                                email = email,
                                password = password,
                            )
                        )
                    }
                    navController.navigate(Destination.login.route)
                    isLoading = false
                }
                else if(it.exception!!.message!!.contains("The email address is already in use by another account.")){
                    emailError = "Email is already registered."
                }
            }
        }
    }


    //Validation
    private fun isValid(): Boolean{
        onNameChange(name)
        onSurnameChange(surname)
        onEmailChange(email)
        onPasswordChange(password)
        return  nameError.isEmpty() &&
                surnameError.isEmpty() &&
                emailError.isEmpty() &&
                passwordError.isEmpty()
    }
    fun onNameChange(name: String){
        this.name = name
        nameError = if(name.isEmpty()){
            "Name required."
        }else{
            ""
        }
    }
    fun onSurnameChange(userSurname: String){
        this.surname = userSurname
        surnameError = if(userSurname.isEmpty()){
            "Surname required."
        }else{
            ""
        }
    }
    fun onEmailChange(email: String){
        this.email = email
        emailError =
            if(email.isEmpty()){
                "Email required."
            } else if(!android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()){
                "Invalid email."
            } else{
                ""
            }
    }
    fun onPasswordChange(password: String){
        this.password = password
        passwordError =
            if(password.isEmpty()){
                "Password required."
            } else if(password.length < 6){
                "Password must be at least 6 characters."
            } else{
                ""
            }
    }
    fun onHidePasswordPressed(){
        hidePassword = !hidePassword
    }
}