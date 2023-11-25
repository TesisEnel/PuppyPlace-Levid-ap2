package com.project.puppyplace.ui.home

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import com.google.firebase.auth.FirebaseAuth
import com.project.puppyplace.data.remote.dto.DogDto
import com.project.puppyplace.data.repository.HomeRepository
import com.project.puppyplace.di.AppModule.sharedDog
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
class HomeViewModel @Inject constructor(
    private val homeRepository: HomeRepository
): ViewModel(){
    private var _state = MutableStateFlow(HomeListState())
    val state: StateFlow<HomeListState> = _state.asStateFlow()

    var searchItem by mutableStateOf("")

    fun onLikedClicked(dog: DogDto, isLiked: Boolean){
        viewModelScope.launch {
            homeRepository.updateDog(
                DogDto(
                    id = dog.id,
                    name = dog.name,
                    breed = dog.breed,
                    size = dog.size,
                    weight = dog.weight,
                    gender = dog.gender,
                    birthDate = dog.birthDate,
                    hairColor = dog.hairColor,
                    isSterilized = dog.isSterilized,
                    behaviour = dog.behaviour,
                    activityLevel = dog.activityLevel,
                    origin = dog.origin,
                    image = dog.image,
                    age = dog.age,
                    isLiked = isLiked,
                    status = dog.status
                )
            )
        }
    }
    fun logOut(navController: NavController) {
        FirebaseAuth.getInstance().signOut()

        navController.popBackStack()
        navController.navigate(Destination.login.route)
    }
    fun onDogSelected(navController: NavController, dog: DogDto){
        sharedDog = dog
        navController.navigate(Destination.dogDetail.route)
    }
    fun onSearchItemChanged(searchItem: String){
        this.searchItem = searchItem
    }
    fun getDogsBySize(size: String){
        viewModelScope.launch {
            homeRepository.getDogsBySize(size).onEach { result ->
                when (result) {
                    is Resource.Loading -> {
                        _state.value = HomeListState(isLoading = true)
                    }

                    is Resource.Success -> {
                        _state.value = HomeListState(dogsList = result.data ?: emptyList())
                    }

                    is Resource.Error -> {
                        _state.value = HomeListState(error = result.message ?: "Unknown error")
                    }
                }
            }.launchIn(viewModelScope)
        }
    }
    fun getDogsByBreed(breed: String){
        viewModelScope.launch {
            if(breed.isBlank()) {
                getDogs()
                return@launch
            }
            homeRepository.getDogsByBreed(breed).onEach { result ->
                when (result) {
                    is Resource.Loading -> {
                        _state.value = HomeListState(isLoading = true)
                    }

                    is Resource.Success -> {
                        _state.value = HomeListState(dogsList = result.data ?: emptyList())
                    }

                    is Resource.Error -> {
                        _state.value = HomeListState(error = result.message ?: "Unknown error")
                    }
                }
            }.launchIn(viewModelScope)
        }
    }
    fun getDogsBySex(sex: String){
        viewModelScope.launch {
            homeRepository.getDogsBySex(sex).onEach { result ->
                when (result) {
                    is Resource.Loading -> {
                        _state.value = HomeListState(isLoading = true)
                    }

                    is Resource.Success -> {
                        _state.value = HomeListState(dogsList = result.data ?: emptyList())
                    }

                    is Resource.Error -> {
                        _state.value = HomeListState(error = result.message ?: "Unknown error")
                    }
                }
            }.launchIn(viewModelScope)
        }
    }
    fun getDogs(){
        viewModelScope.launch {
            homeRepository.getDogs().onEach { result ->
                when (result) {
                    is Resource.Loading -> {
                        _state.value = HomeListState(isLoading = true)
                    }

                    is Resource.Success -> {
                        _state.value = HomeListState(dogsList = result.data ?: emptyList())
                    }

                    is Resource.Error -> {
                        _state.value = HomeListState(error = result.message ?: "Unknown error")
                    }
                }
            }.launchIn(viewModelScope)
        }
    }
    init {
        getDogs()
    }
}