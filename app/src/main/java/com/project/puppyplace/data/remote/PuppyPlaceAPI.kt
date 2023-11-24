package com.project.puppyplace.data.remote

import com.project.puppyplace.data.remote.dto.DogDto
import com.project.puppyplace.data.remote.dto.UserDto
import retrofit2.http.GET

interface PuppyPlaceAPI {
    @GET("api/Dogs")
    suspend fun getDogs(): List<DogDto>
    @GET("api/Dogs/{id}")
    suspend fun getDogById(id: Int): DogDto
    @GET("api/Dogs/breed/{breed}")
    suspend fun getDogsByBreed(breed: String): List<DogDto>
    @GET("api/Dogs/size/{size}")
    suspend fun getDogsBySize(size: String): List<DogDto>
    @GET("api/Dogs/sex/{sex}")
    suspend fun getDogsBySex(sex: String): List<DogDto>

    //User
    @GET("api/Users")
    suspend fun getUsers(): List<UserDto>
}