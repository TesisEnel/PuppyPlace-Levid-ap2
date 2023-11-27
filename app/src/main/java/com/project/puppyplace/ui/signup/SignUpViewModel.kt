package com.project.puppyplace.ui.signup

import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import com.google.firebase.auth.FirebaseAuth
import com.project.puppyplace.data.remote.dto.UserDto
import com.project.puppyplace.data.repository.SignUpRepository
import com.project.puppyplace.navigation.Destination
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SignUpViewModel @Inject constructor(
    private val signUpRepository: SignUpRepository
): ViewModel() {
    var name by mutableStateOf("")
    var surname by mutableStateOf("")
    var identificationNumber by mutableStateOf("")
    var address by mutableStateOf("")
    var email by mutableStateOf("")
    var password by mutableStateOf("")
    var telephone by mutableStateOf("")
    var cellphone by mutableStateOf("")

    //Error messages
    var nameError by mutableStateOf("")
    var surnameError by mutableStateOf("")
    var identificationNumberError by mutableStateOf("")
    var addressError by mutableStateOf("")
    var emailError by mutableStateOf("")
    var passwordError by mutableStateOf("")
    var telephoneError by mutableStateOf("")
    var cellphoneError by mutableStateOf("")

    var hidePassword by mutableStateOf(true)
    //Snackbarh
    val snackbarHostState by mutableStateOf(SnackbarHostState())

    var success by mutableStateOf(false)
    fun showSnackBar(){
        viewModelScope.launch {
            snackbarHostState.showSnackbar("Test message")
        }
    }

    //SignUp
    fun onSignUpClick(navController: NavController){
        if(isValid()){
            FirebaseAuth.getInstance()
                .createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener{
                    if(it.isSuccessful){
                        showSnackBar()
                        viewModelScope.launch{
                            signUpRepository.createUser(
                                UserDto(
                                    name = name,
                                    surname = surname,
                                    identificationNumber = identificationNumber,
                                    address = address,
                                    email = email,
                                    password = password,
                                    telephone = telephone,
                                    cellphone = cellphone
                                )
                            )
                        }
                        navController.navigate(Destination.login.route)
                    }
                }

        }
    }


    //Validation
    fun isValid(): Boolean{
        onNameChange(name)
        onSurnameChange(surname)
        onIdentificationNumberChange(identificationNumber)
        onTelephoneChange(telephone)
        onCellphoneChange(cellphone)
        onEmailChange(email)
        onPasswordChange(password)
        onAddressChange(address)
        return  nameError.isEmpty() &&
                surnameError.isEmpty() &&
                identificationNumberError.isEmpty() &&
                telephoneError.isEmpty() &&
                cellphoneError.isEmpty() &&
                emailError.isEmpty() &&
                passwordError.isEmpty() &&
                addressError.isEmpty()
    }
    fun onNameChange(name: String){
        this.name = name
        nameError = if(name.isEmpty()){
            "Name required."
        }else{
            ""
        }
    }
    fun onSurnameChange(userSurname: String){
        this.surname = userSurname
        surnameError = if(userSurname.isEmpty()){
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
            "Identification Number must be ${identificationNumber.length}/11 digits."
        } else if(identificationNumber.length > 11){
            "Identification Number must be ${identificationNumber.length}/11 digits."
        }
        else{
            ""
        }
    }
    fun onTelephoneChange(telephone: String){
        this.telephone = telephone
        telephoneError = if(telephone.isEmpty()){
            "Telephone required."
        } else if(telephone.length < 10) {
            "Telephone must be ${telephone.length}/10 digits."
        } else if(telephone.length > 10){
            "Telephone must be ${telephone.length}/10 digits."
        }
        else{
            ""
        }
    }
    fun onCellphoneChange(cellphone: String){
        this.cellphone = cellphone
        cellphoneError = if(cellphone.isEmpty()){
            "Cellphone required."
        } else if (cellphone.length < 10){
            "Cellphone must be ${cellphone.length}/10 digits."
        } else if(cellphone.length > 10){
            "Cellphone must be ${cellphone.length}/10 digits."
        } else{
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
    fun onPasswordChange(password: String){
        this.password = password
        passwordError =
            if(password.isEmpty()){
                "Password required."
            } else if(password.length < 6){
                "Password must be at least 6 characters."
            } else{
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

    fun onHidePasswordPressed(){
        hidePassword = !hidePassword
    }
}