package com.project.puppyplace.ui.adoption

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.project.puppyplace.data.remote.dto.DogDto
import com.project.puppyplace.data.repository.AdoptionRepository
import com.project.puppyplace.di.AppModule.sharedDog
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class AdoptionViewModel @Inject constructor(
    private val adoptionRepository: AdoptionRepository
): ViewModel(){

    var dog by mutableStateOf(DogDto())
    var date by mutableStateOf("")
    var userName by mutableStateOf("")
    var userSurname by mutableStateOf("")
    var identificationNumber by mutableStateOf("")
    var telephone by mutableStateOf("")
    var cellphone by mutableStateOf("")
    var email by mutableStateOf("")
    var address by mutableStateOf("")

    //Error messages
    var dateError by mutableStateOf("")
    var userNameError by mutableStateOf("")
    var userSurnameError by mutableStateOf("")
    var identificationNumberError by mutableStateOf("")
    var telephoneError by mutableStateOf("")
    var cellphoneError by mutableStateOf("")
    var emailError by mutableStateOf("")
    var addressError by mutableStateOf("")

    fun onUserNameChange(userName: String){
        this.userName = userName
        userNameError = if(userName.isEmpty()){
            "Name required."
        }else{
            ""
        }
    }
    fun onUserSurnameChange(userSurname: String){
        this.userSurname = userSurname
        userSurnameError = if(userSurname.isEmpty()){
            "Surname required."
        }else{
            ""
        }
    }
    fun onIdentificationNumberChange(identificationNumber: String){
        this.identificationNumber = identificationNumber
        identificationNumberError = if(identificationNumber.isEmpty()){
            "Identification Number required."
        } else if(identificationNumber.length < 11){
            "Identification Number must be 11 digits."
        } else{
            ""
        }
    }
    fun onTelephoneChange(telephone: String){
        this.telephone = telephone
        if(telephone.isEmpty()){
            telephoneError = "Telephone required."
        }
        else if(telephone.length < 10) {
            telephoneError = "Telephone must be 10 digits."
        }
        else{
            telephoneError = ""
        }
    }
    fun onCellphoneChange(cellphone: String){
        this.cellphone = cellphone
        cellphoneError = if(cellphone.isEmpty()){
            "Cellphone required."
        }else{
            ""
        }
    }
    fun onEmailChange(email: String){
        this.email = email
        emailError =
            if(email.isEmpty()){
            "Email required."
            } else if(!android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()){
                "Invalid email."
            } else{
                ""
            }
    }
    fun onDateChange(date: String){
        this.date = date
        dateError = if(date.isEmpty()){
            "Date required."
        }else{
            ""
        }
    }
    fun onAddressChange(address: String){
        this.address = address
        addressError = if(address.isEmpty()){
            "Address required."
        }else{
            ""
        }
    }


    init{
        dog = sharedDog!!
    }
}