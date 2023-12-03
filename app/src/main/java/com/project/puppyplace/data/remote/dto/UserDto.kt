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
    @Json(name = "userFavoriteDogs")
    var favoriteDogs: MutableList<FavoriteDogsDto> = mutableListOf()
)