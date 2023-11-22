package com.project.puppyplace.data.remote

import com.project.puppyplace.data.remote.dto.DogDto
import com.project.puppyplace.data.remote.dto.UserDto
import retrofit2.http.GET

interface PuppyPlaceAPI {
    @GET("Dog")
    suspend fun getDogs(): List<DogDto>
    @GET("User")
    suspend fun getUsers(): List<UserDto>
}