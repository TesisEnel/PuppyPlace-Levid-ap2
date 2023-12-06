package com.project.puppyplace.ui.dogDetail

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import com.project.puppyplace.data.remote.dto.DogDto
import com.project.puppyplace.data.repository.HomeRepository
import com.project.puppyplace.di.AppModule.sharedDog
import com.project.puppyplace.navigation.Destination
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DogDetailViewModel @Inject constructor(
    private val homeRepository: HomeRepository
): ViewModel(){
    var dog by mutableStateOf(DogDto())
    init{
        dog = sharedDog ?: DogDto()
    }

    fun isMale(dog:DogDto): Boolean{
        return dog.gender == "Male"
    }

    fun isSterilized(dog:DogDto): Boolean{
        return dog.isSterilized
    }

    fun onAdoptionButtonPressed(navController: NavController){
        navController.navigate(Destination.adoption.route)
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
}