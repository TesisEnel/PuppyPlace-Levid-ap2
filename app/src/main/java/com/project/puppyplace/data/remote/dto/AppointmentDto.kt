package com.project.puppyplace.data.remote.dto

data class AppointmentDto(
    val id: Int = 0,
    val date: String = "",
    val userName: String = "",
    val userSurname: String = "",
    val identificationNumber: String = "",
    val dogId: Int = 0,
    val telephone: String = "",
    val cellphone: String = "",
    val email: String = "",
    val address: String = ""
)
