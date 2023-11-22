package com.project.puppyplace.data.remote.dto

import com.squareup.moshi.Json

data class UserDto(
    @Json(name = "UserId")
    val id: Int,
    @Json(name = "Name")
    val name: String,
    @Json(name = "Surname")
    val surname: String,
    @Json(name = "IdentificationNumber")
    val identificationNumber: String,
    @Json(name = "Address")
    val address: String,
    @Json(name = "Email")
    val email: String,
    @Json(name = "Password")
    val password: String,
    @Json(name = "Telephone")
    val telephone: String,
    @Json(name = "Cellphone")
    val cellphone: String,
    @Json(name = "Sex")
    val gender: String,
    @Json(name = "AppointmentDate")
    val appointmentDate: String? = null,
    @Json(name = "HouseType")
    val houseType: String,
    @Json(name = "Role")
    val role: String = "User"
)