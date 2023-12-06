package com.project.puppyplace.ui.appointment

import com.project.puppyplace.data.remote.dto.UserDto

data class AppointmentListState(
    val isLoading: Boolean = false,
    val userList: List<UserDto> = emptyList(),
    val error: String = ""
)