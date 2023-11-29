package com.project.puppyplace.ui.login

import com.project.puppyplace.data.remote.dto.DogDto

data class LoginListState(
    val isLoading: Boolean = false,
    val dogsList: List<DogDto> = emptyList(),
    val error: String = ""
)