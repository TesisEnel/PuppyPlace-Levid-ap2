package com.project.puppyplace.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.project.puppyplace.data.remote.dto.DogDto
import com.project.puppyplace.data.repository.HomeRepository
import com.project.puppyplace.di.AppModule.sharedDog
import com.project.puppyplace.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val homeRepository: HomeRepository
): ViewModel(){
    private var _state = MutableStateFlow(HomeListState())
    val state: StateFlow<HomeListState> = _state.asStateFlow()

    fun onDogSelected(dog: DogDto){
        sharedDog = dog
    }

    init {
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