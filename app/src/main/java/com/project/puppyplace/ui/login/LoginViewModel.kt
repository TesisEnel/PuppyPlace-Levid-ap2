package com.project.puppyplace.ui.login

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import com.google.firebase.auth.FirebaseAuth
import com.project.puppyplace.data.remote.dto.UserDto
import com.project.puppyplace.data.repository.LoginRepository
import com.project.puppyplace.di.AppModule.userLoged
import com.project.puppyplace.navigation.Destination
import com.project.puppyplace.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await
import retrofit2.HttpException
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val loginRepository: LoginRepository
): ViewModel(){

    private var _state = MutableStateFlow(LoginListState())
    val state: StateFlow<LoginListState> = _state.asStateFlow()

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
    fun logIn(navController: NavController) {
        _state.value = LoginListState(isLoading = true)
        viewModelScope.launch {

            val isEmailValid = validateEmailData(this@LoginViewModel.email)
            val isPasswordValid = validatePasswordData(this@LoginViewModel.password)

            if (isEmailValid && isPasswordValid) {
                try {
                    val authResult = FirebaseAuth.getInstance().signInWithEmailAndPassword(email, password).await()

                    if (authResult.user != null) {
                        userLoged = user
                        loadUserFavorites(userLoged!!.id)
                        Log.d("LOGIN", "Successful")
                        _state.value = LoginListState(isLoading = false) // Antes de la navegación

                        if (navController.currentDestination?.route == Destination.login.route) {
                            navController.navigate(Destination.home.route) {
                                popUpTo(navController.graph.startDestinationRoute!!) {
                                    inclusive = true
                                }
                            }
                        }
                    } else {
                        Log.d("LOGIN", "Failed")
                        _state.value = LoginListState(isLoading = false) // En caso de fallo también
                    }
                } catch (e: Exception) {
                    Log.d("LOGIN", "Exception: ${e.message}")
                    _state.value = LoginListState(isLoading = false) // Manejo de excepciones
                }
            } else {
                Log.d("LOGIN", "Invalid data")
                _state.value = LoginListState(isLoading = false) // Datos no válidos
            }
        }
    }



    fun loadUserFavorites(userId: Int){
        viewModelScope.launch {
            loginRepository.getUsersFavoritesDogs(userId).collect { resource ->
                when (resource) {
                    is Resource.Loading -> {
                        _state.value = _state.value.copy(isLoading = true)
                    }
                    is Resource.Success -> {
                        _state.value = _state.value.copy(
                            isLoading = false,
                            dogsList = resource.data ?: emptyList()
                        )
                    }
                    is Resource.Error -> {
                        _state.value = _state.value.copy(
                            isLoading = false,
                            error = resource.message ?: "Unknown error"
                        )
                    }
                }
            }
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
    private suspend fun validateEmailData(email: String): Boolean {
        if (email.isEmpty()) {
            emailError = "Email is required"
            return false
        } else if (!android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            emailError = "Invalid email."
            return false
        }

        _state.value = LoginListState(isLoading = true)
        try {
            user = loginRepository.getUserByEmail(email)
            Log.d("USER", "${user.email} - ${user.password}")
            goodEmail = true
        } catch (e: HttpException) {
            emailError = "Email not registered."
            Log.d("HTTP E", "${e.message} (${e.code()})")
            goodEmail = false
        } finally {
            _state.value = LoginListState(isLoading = false)
        }
        return goodEmail
    }

    private fun validatePasswordData(password: String): Boolean {
        if (password.isEmpty()) {
            passwordError = "Password is required."
            return false
        }

        _state.value = LoginListState(isLoading = true)
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
        } finally {
            _state.value = LoginListState(isLoading = false)
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