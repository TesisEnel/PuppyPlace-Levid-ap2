package com.project.puppyplace.data.remote.dto

import com.squareup.moshi.Json

data class UserDto(
    @Json(name = "userId")
    val id: Int,
    val name: String,
    val surname: String,
    val identificationNumber: String,
    val address: String,
    val email: String,
    val password: String,
    val telephone: String,
    val cellphone: String,
    @Json(name = "sex")
    val gender: String,
    val appointmentDate: String? = null,
    val houseType: String,
    val role: String = "User"
)