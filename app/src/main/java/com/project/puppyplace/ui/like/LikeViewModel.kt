package com.project.puppyplace.ui.like

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import com.project.puppyplace.data.remote.dto.DogDto
import com.project.puppyplace.data.repository.HomeRepository
import com.project.puppyplace.di.AppModule.sharedDog
import com.project.puppyplace.di.AppModule.userLoged
import com.project.puppyplace.navigation.Destination
import com.project.puppyplace.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.async
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

    var isLiked by mutableStateOf(true)
    init {
        getFavoriteDogs()
    }
    private fun getFavoriteDogs(){
        viewModelScope.launch {
            homeRepository.getFavoritesByUserId(userLoged!!.id).onEach { result ->
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
    fun onLikedClicked(dog: DogDto) {
        viewModelScope.launch {
            val deferred = async{
                homeRepository.updateUser(userLoged!!, dog.id)
            }
            deferred.await()
            getFavoriteDogs()
        }
            isLiked = !dogIsLiked(dog)
    }
    fun dogIsLiked(dog: DogDto): Boolean = _state.value.favoriteDogs.contains(dog)
}