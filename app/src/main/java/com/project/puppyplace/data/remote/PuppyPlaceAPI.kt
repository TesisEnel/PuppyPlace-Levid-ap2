package com.project.puppyplace.data.remote

import com.project.puppyplace.data.remote.dto.DogDto
import retrofit2.http.GET

interface PuppyPlaceAPI {
    @GET("Dog")
    suspend fun getDogs(): List<DogDto>
}