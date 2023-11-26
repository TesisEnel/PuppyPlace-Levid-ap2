package com.project.puppyplace.ui.like

import com.project.puppyplace.data.remote.dto.DogDto

data class LikeListState(
    val isLoading: Boolean = false,
    val favoriteDogs: List<DogDto> = emptyList(),
    val error: String = ""
)