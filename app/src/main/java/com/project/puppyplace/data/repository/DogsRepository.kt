package com.project.puppyplace.data.repository

import com.project.puppyplace.data.remote.PuppyPlaceAPI
import com.project.puppyplace.data.remote.dto.DogDto
import com.project.puppyplace.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import okio.IOException
import retrofit2.HttpException
import javax.inject.Inject

class DogsRepository @Inject constructor(
    private val puppyPlaceApi: PuppyPlaceAPI
) {
    fun getDogs(): Flow<Resource<List<DogDto>>> = flow {
        try {
            emit(Resource.Loading())

            val dogs = puppyPlaceApi.getDogs()

            emit(Resource.Success(dogs))
        } catch (e: HttpException) {
            emit(Resource.Error(e.message ?: "Error HTTP GENERAL"))
        } catch (e: IOException) {
            emit(Resource.Error(e.message ?: "verificar tu conexion a internet"))
        }
    }
}