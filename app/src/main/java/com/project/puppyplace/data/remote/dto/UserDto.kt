package com.project.puppyplace.data.remote.dto

import com.squareup.moshi.Json

data class UserDto(
    @Json(name = "userId")
    val id: Int = 0,
    val name: String = "",
    val surname: String = "",
    val identificationNumber: String = "",
    val address: String = "",
    val email: String = "",
    val password: String = "",
    val telephone: String = "",
    val cellphone: String = "",
    @Json(name = "sex")
    val gender: String = "",
    val appointmentDate: String? = "0001-01-01",
    val houseType: String = "",
    val role: String = "User",
    var favoriteDogs: MutableList<DogDto> = mutableListOf()
)