package com.project.puppyplace.data.remote.dto

import com.squareup.moshi.Json

data class DogDto(
    @Json(name = "dogId")
    val id: Int,
    val name: String,
    @Json(name = "race")
    val breed: String,
    val size: String,
    val weight: Float,
    val gender: String,
    val birthDate: String,
    val hairColor: String,
    @Json(name = "sterilization")
    val isSterilized: Boolean,
    val behavior: String?,
    val activityLevel: String,
    val origin: String,
    val image: String
)