package com.project.puppyplace.data.repository

import com.project.puppyplace.data.remote.PuppyPlaceAPI
import com.project.puppyplace.data.remote.dto.DogDto
import com.project.puppyplace.data.remote.dto.UserDto
import com.project.puppyplace.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import okio.IOException
import retrofit2.HttpException
import javax.inject.Inject

class HomeRepository @Inject constructor(
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
    fun getDogsByBreed(breed: String): Flow<Resource<List<DogDto>>> = flow {
        try {
            emit(Resource.Loading())

            val dogs = puppyPlaceApi.getDogsByBreed(breed)

            emit(Resource.Success(dogs))
        } catch (e: HttpException) {
            emit(Resource.Error(e.message ?: "Error HTTP GENERAL"))
        } catch (e: IOException) {
            emit(Resource.Error(e.message ?: "verificar tu conexion a internet"))
        }
    }
    fun getDogsBySize(size: String): Flow<Resource<List<DogDto>>> = flow {
        try {
            emit(Resource.Loading())

            val dogs = puppyPlaceApi.getDogsBySize(size)

            emit(Resource.Success(dogs))
        } catch (e: HttpException) {
            emit(Resource.Error(e.message ?: "Error HTTP GENERAL"))
        } catch (e: IOException) {
            emit(Resource.Error(e.message ?: "verificar tu conexion a internet"))
        }
    }
    fun getDogsBySex(sex: String): Flow<Resource<List<DogDto>>> = flow {
        try {
            emit(Resource.Loading())

            val dogs = puppyPlaceApi.getDogsBySex(sex)

            emit(Resource.Success(dogs))
        } catch (e: HttpException) {
            emit(Resource.Error(e.message ?: "Error HTTP GENERAL"))
        } catch (e: IOException) {
            emit(Resource.Error(e.message ?: "verificar tu conexion a internet"))
        }
    }
    fun getFavoritesByUserId(userId: Int): Flow<Resource<List<DogDto>>> = flow {
        try {
            emit(Resource.Loading())

            val dogs = puppyPlaceApi.getFavoritesByUserId(userId)

            emit(Resource.Success(dogs))
        } catch (e: HttpException) {
            emit(Resource.Error(e.message ?: "Error HTTP GENERAL"))
        } catch (e: IOException) {
            emit(Resource.Error(e.message ?: "verificar tu conexion a internet"))
        }
    }
    suspend fun updateDog(dog: DogDto) = puppyPlaceApi.updateDog(dog.id, dog)

    suspend fun getDogById(id: Int) = puppyPlaceApi.getDogById(id)

    suspend fun updateUser(user: UserDto, dogId: Int) = puppyPlaceApi.updateUser(user.id, user, dogId )
    suspend fun getUserById(id: Int) = puppyPlaceApi.getUsersById(id)
}