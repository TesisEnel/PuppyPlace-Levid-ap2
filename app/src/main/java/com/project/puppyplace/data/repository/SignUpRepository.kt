package com.project.puppyplace.data.repository

import com.project.puppyplace.data.remote.PuppyPlaceAPI
import com.project.puppyplace.data.remote.dto.UserDto
import javax.inject.Inject

class SignUpRepository @Inject constructor(
    private val puppyPlaceAPI: PuppyPlaceAPI
){
    suspend fun createUser(user: UserDto) = puppyPlaceAPI.createUser(user)
}