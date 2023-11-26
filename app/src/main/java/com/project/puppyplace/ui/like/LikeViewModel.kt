package com.project.puppyplace.ui.like

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
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
class LikeViewModel @Inject constructor(
    private val homeRepository: HomeRepository
): ViewModel() {

    private var _state = MutableStateFlow(LikeListState())
    val state: StateFlow<LikeListState> = _state.asStateFlow()

    init {
        getFavoriteDogs()
    }
    private fun getFavoriteDogs(){
        viewModelScope.launch {
            homeRepository.getFavorites().onEach { result ->
                when (result) {
                    is Resource.Loading -> {
                        _state.value = LikeListState(isLoading = true)
                    }

                    is Resource.Success -> {
                        _state.value = LikeListState(favoriteDogs = result.data ?: emptyList())
                    }

                    is Resource.Error -> {
                        _state.value = LikeListState(error = result.message ?: "Unknown error")
                    }
                }
            }.launchIn(viewModelScope)
        }
    }
    fun onDogSelected(navController: NavController, dog: DogDto){
        getFavoriteDogs()
        sharedDog = dog
        navController.navigate(Destination.dogDetail.route)
    }
    fun isMale(dog:DogDto): Boolean{
        return dog.gender == "Male"
    }
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
                    status = dog.status,
                    description = dog.description
                )
            )
        }
    }
    fun onHomeSelected(navController: NavController){
        navController.navigate(Destination.home.route)
    }
    fun onBackPressed(navController: NavController){
        navController.popBackStack()
    }
}