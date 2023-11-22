package com.project.puppyplace.ui.login

import com.project.puppyplace.data.remote.dto.UserDto

data class LoginListState(
    val isLoading: Boolean = false,
    val userList: List<UserDto> = emptyList(),
    val error: String = ""
)