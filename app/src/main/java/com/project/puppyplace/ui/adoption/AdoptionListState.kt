package com.project.puppyplace.ui.adoption

import com.project.puppyplace.data.remote.dto.AppointmentDto

data class AdoptionListState (
    val isLoading: Boolean = false,
    val adoptionList: List<AppointmentDto> = emptyList(),
    val error: String = ""
)