package com.project.puppyplace.ui.home

import com.project.puppyplace.data.remote.dto.DogDto

data class HomeListState(
    val isLoading: Boolean = false,
    val dogsList: List<DogDto> = emptyList(),
    val error: String = ""
)