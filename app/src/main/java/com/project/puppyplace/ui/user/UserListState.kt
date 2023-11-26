package com.project.puppyplace.ui.user

import com.project.puppyplace.data.remote.dto.UserDto

data class UserListState(
    val isLoading: Boolean = false,
    val userList: List<UserDto> = emptyList(),
    val error: String = ""
)