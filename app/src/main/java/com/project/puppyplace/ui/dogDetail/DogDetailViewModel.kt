package com.project.puppyplace.ui.dogDetail

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.project.puppyplace.data.remote.dto.DogDto
import com.project.puppyplace.di.AppModule.sharedDog
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class DogDetailViewModel @Inject constructor(): ViewModel(){
    var dog by mutableStateOf(DogDto())
    init{
        dog = sharedDog ?: DogDto()
    }
}