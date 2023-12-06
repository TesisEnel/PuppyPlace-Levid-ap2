package com.project.puppyplace.ui.home

import android.content.Context
import android.media.MediaPlayer
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import com.project.puppyplace.R
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
import kotlin.random.Random

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val homeRepository: HomeRepository
): ViewModel(){
    private var _state = MutableStateFlow(HomeListState())
    val state: StateFlow<HomeListState> = _state.asStateFlow()

    private val _favoriteDogsList = MutableStateFlow<List<DogDto>>(emptyList())
    val favoriteDogsList: StateFlow<List<DogDto>> = _favoriteDogsList.asStateFlow()

    var searchItem by mutableStateOf("")
    var isLiked by mutableStateOf(false)


    fun isMale(dog:DogDto): Boolean{
        return dog.gender == "Male"
    }
    fun onLikedClicked(dog: DogDto) {
        viewModelScope.launch {
            val deferred = async{
                homeRepository.updateUser(userLoged!!, dog.id)
            }
            deferred.await()
            getFavoritesDogs()
        }
            isLiked = !dogIsLiked(dog)
    }
    fun onPetsPressed(context: Context){
        val effectsList = listOf(
            R.raw.dog_bark,
            R.raw.dog_bark2,
            R.raw.dog_bark3
        )
        val randomIndex = Random.nextInt(effectsList.size)
        val effectResource = effectsList[randomIndex]
        val mp: MediaPlayer = MediaPlayer.create(
            context,
            effectResource
        )
        mp.start()
    }
    fun onDogSelected(navController: NavController, dog: DogDto){
        getDogs()
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
    private fun getFavoritesDogs() {
        viewModelScope.launch {
            homeRepository.getFavoritesByUserId(userLoged!!.id).onEach {
                result ->
                when (result) {
                    is Resource.Loading -> {
                        _favoriteDogsList.value = emptyList()
                    }

                    is Resource.Success -> {
                        _favoriteDogsList.value = result.data ?: emptyList()
                    }

                    is Resource.Error -> {
                        _favoriteDogsList.value = emptyList()
                    }
                }
            }.launchIn(viewModelScope)
        }
    }
    fun dogIsLiked(dog: DogDto): Boolean = favoriteDogsList.value.contains(dog)
    private fun getData(){
        viewModelScope.launch {
            val deferred = async {
                getFavoritesDogs()
            }
            deferred.await()
            getDogs()
        }
    }
    init {
        getData()
    }

    // BOTTOM BAR ACTIONS
    fun onBottomBarLikePressed(navController: NavController){
        navController.navigate(Destination.like.route)
    }
    fun onBottomBarAppointmentPressed(navController: NavController){
        navController.navigate(Destination.appointment.route)
    }

    // TOP BAR ACTIONS
    fun onTopBarSettingsPressed(navController: NavController){
        navController.navigate(Destination.settings.route)
    }
}