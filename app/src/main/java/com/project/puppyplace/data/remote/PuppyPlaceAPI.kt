package com.project.puppyplace.data.remote

import com.project.puppyplace.data.remote.dto.DogDto
import com.project.puppyplace.data.remote.dto.UserDto
import retrofit2.http.GET

interface PuppyPlaceAPI {
    @GET("api/Dogs")
    suspend fun getDogs(): List<DogDto>
    @GET("api/Dogs/{id}")
    suspend fun getDogById(id: Int): DogDto

    //User
    @GET("api/Users")
    suspend fun getUsers(): List<UserDto>
}