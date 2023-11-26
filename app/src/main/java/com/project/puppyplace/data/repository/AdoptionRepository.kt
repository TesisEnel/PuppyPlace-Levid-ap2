package com.project.puppyplace.data.repository

import com.project.puppyplace.data.remote.PuppyPlaceAPI
import com.project.puppyplace.data.remote.dto.AppointmentDto
import com.project.puppyplace.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import okio.IOException
import retrofit2.HttpException
import javax.inject.Inject

class AdoptionRepository @Inject constructor(
    private val puppyPlaceApi: PuppyPlaceAPI
){
    fun getAppointments(): Flow<Resource<List<AppointmentDto>>> = flow {
        try {
            emit(Resource.Loading())

            val appointments = puppyPlaceApi.getAppointments()

            emit(Resource.Success(appointments))
        } catch (e: HttpException) {
            emit(Resource.Error(e.message ?: "Error HTTP GENERAL"))
        } catch (e: IOException) {
            emit(Resource.Error(e.message ?: "verificar tu conexion a internet"))
        }
    }

    suspend fun createAppointment(appointmentDto: AppointmentDto) =
        puppyPlaceApi.createAppointment(appointmentDto)
}