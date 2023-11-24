package com.project.puppyplace.ui.login

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import com.google.firebase.auth.FirebaseAuth
import com.project.puppyplace.data.remote.dto.UserDto
import com.project.puppyplace.data.repository.LoginRepository
import com.project.puppyplace.navigation.Destination
import com.project.puppyplace.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val loginRepository: LoginRepository
): ViewModel(){
    private var _state = MutableStateFlow(LoginListState())
    val state: StateFlow<LoginListState> = _state.asStateFlow()

    var email by mutableStateOf("")
    var password by mutableStateOf("")
    //errors
    var emailMessage by mutableStateOf("")
    var passwordMessage by mutableStateOf("")
    //
    private var userList: List<UserDto> by mutableStateOf(emptyList())

    var access by mutableStateOf(false)

    init{
        loadData()
    }
    fun logIn(navController: NavController): Boolean{
        if(validate()){
            FirebaseAuth.getInstance()
                .signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(){
                    access = it.isSuccessful
            }
            navController.popBackStack()
            navController.navigate(Destination.home.route)
        }
        return access
    }

    fun validate(): Boolean{
        return validEmail() && validPassword()
    }
    fun validEmail(): Boolean{
        if(email.isEmpty()){
            emailMessage = "Please, enter your email."
            return false
        }
        else if(email == userList[0].email ||  email == userList[1].email){
            emailMessage = ""
            return true
        }
        else
        {
            emailMessage = "Email is not registered."
            return false
        }
    }

    private fun validPassword(): Boolean {
        if(password.isEmpty()){
            passwordMessage = "Please, enter your password."
            return false
        }
        else if(
            (password == userList[0].password && email == userList[0].email)
            || (password == userList[1].password && email == userList[1].email)
            ){
            passwordMessage = ""
            return true
        }
        else
        {
            passwordMessage = "Password is incorrect."
            return false
        }
    }
    private fun loadData(){
        viewModelScope.launch {
            loginRepository.getUsers().onEach { result ->
                when (result) {
                    is Resource.Loading -> {
                        _state.value = LoginListState(isLoading = true)
                    }

                    is Resource.Success -> {
                        _state.value = LoginListState(userList = result.data ?: emptyList())
                        userList = _state.value.userList
                    }

                    is Resource.Error -> {
                        _state.value = LoginListState(error = result.message ?: "Unknown error")
                    }
                }
            }.launchIn(viewModelScope)
        }
    }
}