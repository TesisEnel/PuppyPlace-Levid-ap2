package com.project.puppyplace.ui.dogDetail

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.lifecycle.ViewModel
import com.project.puppyplace.data.remote.dto.DogDto
import com.project.puppyplace.data.repository.HomeRepository
import com.project.puppyplace.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@HiltViewModel
class DogDetailViewModel @Inject constructor(
    private val homeRepository: HomeRepository
): ViewModel(){
    val dogId by mutableIntStateOf(0)
    var dogData: Flow<Resource<DogDto>>

    init{
        dogData = homeRepository.getDogById(dogId)
    }

}