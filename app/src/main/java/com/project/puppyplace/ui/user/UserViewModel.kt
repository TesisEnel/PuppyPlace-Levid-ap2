package com.project.puppyplace.ui.user

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import com.google.firebase.auth.FirebaseAuth
import com.project.puppyplace.data.remote.dto.AppointmentDto
import com.project.puppyplace.data.remote.dto.UserDto
import com.project.puppyplace.data.repository.AdoptionRepository
import com.project.puppyplace.data.repository.UserRepository
import com.project.puppyplace.navigation.Destination
import com.project.puppyplace.ui.adoption.AdoptionListState
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
class UserViewModel @Inject constructor(
    private val userRepository: UserRepository,
    private val adoptionRepository: AdoptionRepository
): ViewModel() {
    private var _state = MutableStateFlow(UserListState())
    val state: StateFlow<UserListState> = _state.asStateFlow()

    private var _stateAdoption = MutableStateFlow(AdoptionListState())
    val stateAdoption: StateFlow<AdoptionListState> = _stateAdoption.asStateFlow()

    var user by mutableStateOf(UserDto())
    var adoption by mutableStateOf(AppointmentDto())

    fun deleteAppointment(id: Int){
        viewModelScope.launch {
            userRepository.deleteAppointment(id)
        }
        getAppoiment()
    }
    fun getUsers(){
        viewModelScope.launch {
            userRepository.getUsers().onEach { result ->
                when (result) {
                    is Resource.Loading -> {
                        _state.value = UserListState(isLoading = true)
                    }

                    is Resource.Success -> {
                        _state.value = UserListState(userList = result.data ?: emptyList())
                        user = _state.value.userList[1]

                    }

                    is Resource.Error -> {
                        _state.value = UserListState(error = result.message ?: "Unknown error")
                    }
                }
            }.launchIn(viewModelScope)
        }
    }
    init {
        getUsers()
        getAppoiment()
    }
    fun BackHome(navController: NavController){
        navController.navigate(Destination.home.route)
    }

    fun logOut(navController: NavController) {
        FirebaseAuth.getInstance().signOut()

        navController.popBackStack()
        navController.navigate(Destination.login.route)
    }

    fun getAppoiment(){
        viewModelScope.launch {
            adoptionRepository.getAppointments().onEach { result ->
                when (result) {
                    is Resource.Loading -> {
                        _stateAdoption.value = AdoptionListState(isLoading = true)
                    }

                    is Resource.Success -> {
                        _stateAdoption.value = AdoptionListState(adoptionList = result.data ?: emptyList())
                    }

                    is Resource.Error -> {
                        _stateAdoption.value = AdoptionListState(error = result.message ?: "Unknown error")
                    }
                }
            }.launchIn(viewModelScope)
        }
    }
}