package com.project.puppyplace.data.remote.dto

import com.squareup.moshi.Json

data class DogDto(
    @Json(name = "dogId")
    val id: Int = 0,
    val name: String = "",
    @Json(name = "race")
    val breed: String = "",
    val size: String = "",
    val weight: Float = 0.0f,
    val gender: String = "",
    val birthDate: String = "",
    val hairColor: String = "",
    @Json(name = "sterilization")
    val isSterilized: Boolean = false,
    val behavior: String? = "",
    val activityLevel: String = "",
    val origin: String = "",
    val image: String = "",
    val age: Int = 0,
)