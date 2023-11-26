@file:OptIn(ExperimentalMaterial3Api::class)

package com.project.puppyplace.ui.adoption

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArtTrack
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Mail
import androidx.compose.material.icons.filled.Person2
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material.icons.filled.PhoneAndroid
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import coil.compose.AsyncImage

@Composable
fun AdoptionScreen(
    viewModel: AdoptionViewModel = hiltViewModel(),
    navController: NavHostController
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
    ){
        AsyncImage(
            model = viewModel.dog.image,
            contentDescription = viewModel.dog.name,
            contentScale = ContentScale.Fit,
            modifier = Modifier
                .fillMaxWidth()
                .align(alignment = Alignment.TopCenter)
        )

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .align(alignment = Alignment.BottomCenter)
        ) {
            Card(
                modifier = Modifier
                    .fillMaxWidth(),
                colors = CardDefaults.cardColors(
                    containerColor = MaterialTheme.colorScheme.surface,
                )
            ){
                AdoptionForm(viewModel = viewModel)
            }
        }
    }
}

@Composable
fun AdoptionForm(viewModel: AdoptionViewModel) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
        ) {
            Text(
                text = "${viewModel.dog.name}'s adoption form",
                style = MaterialTheme.typography.headlineLarge
            )
        }
        Row {
            Column(
                modifier = Modifier
                    .weight(1f)
            ) {
                UserNameField(viewModel = viewModel)
            }
            Spacer(modifier = Modifier.padding(8.dp))
            Column(
                modifier = Modifier
                    .weight(1f)
            ) {
                SurnameField(viewModel = viewModel)
            }
        }
        IdentificationNumberField(viewModel)
        AddressField(viewModel = viewModel)
        Row {
            Column(
                modifier = Modifier
                    .weight(1f)
            ) {
                TelephoneField(viewModel = viewModel)
            }
            Spacer(modifier = Modifier.padding(8.dp))
            Column(
                modifier = Modifier
                    .weight(1f)
            ) {
                CellphoneField(viewModel = viewModel)
            }
        }
        EmailField(viewModel = viewModel)

    }
}
@Composable
fun UserNameField(viewModel: AdoptionViewModel) {
    OutlinedTextField(
        value = viewModel.userName,
        onValueChange = {viewModel.onUserNameChange(it)},
        label = { Text(text = "Name") },
        isError = viewModel.userNameError.isNotEmpty(),
        trailingIcon = {
            Icon(
                imageVector = Icons.Filled.Person2,
                contentDescription = "Person name"
            )
        },
        maxLines = 1,
        modifier = Modifier.fillMaxWidth()
    )
    Text(text = viewModel.userNameError, color = MaterialTheme.colorScheme.error)
}
@Composable
fun SurnameField(viewModel: AdoptionViewModel) {
    OutlinedTextField(
        value = viewModel.userSurname,
        onValueChange = {viewModel.onUserSurnameChange(it)},
        label = { Text(text = "Surname") },
        isError = viewModel.userSurnameError.isNotEmpty(),
        trailingIcon = {
            Icon(
                imageVector = Icons.Filled.Person2,
                contentDescription = "Person name"
            )
        },
        maxLines = 1
    )
    Text(text = viewModel.userSurnameError, color = MaterialTheme.colorScheme.error)
}
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun IdentificationNumberField(viewModel: AdoptionViewModel){
    OutlinedTextField(
        keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number),
        value = viewModel.identificationNumber,
        onValueChange = { viewModel.onIdentificationNumberChange(it) },
        label = { Text(text = "Identification number") },
        isError = viewModel.userSurnameError.isNotEmpty(),
        leadingIcon = {
            Icon(
                imageVector = Icons.Filled.ArtTrack,
                contentDescription = "At icon"
            )
        },
        maxLines = 1,
        modifier = Modifier.fillMaxWidth()
    )
    Text(text = viewModel.identificationNumberError, color = MaterialTheme.colorScheme.error)
}
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddressField(viewModel: AdoptionViewModel){
    OutlinedTextField(
        keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Phone),
        value = viewModel.address,
        onValueChange = { viewModel.onAddressChange(it) },
        label = { Text(text = "Address") },
        isError = viewModel.addressError.isNotEmpty(),
        leadingIcon = {
            Icon(
                imageVector = Icons.Filled.Home,
                contentDescription = "Address icon"
            )
        },
        maxLines = 1,
        modifier = Modifier.fillMaxWidth()
    )
    Text(text = viewModel.addressError, color = MaterialTheme.colorScheme.error)
}
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TelephoneField(viewModel: AdoptionViewModel){
    OutlinedTextField(
        keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Phone),
        value = viewModel.telephone,
        onValueChange = { viewModel.onTelephoneChange(it) },
        label = { Text(text = "Telephone") },
        isError = viewModel.telephoneError.isNotEmpty(),
        leadingIcon = {
            Icon(
                imageVector = Icons.Filled.Phone,
                contentDescription = "Telephone icon"
            )
        },
        maxLines = 1
    )
    Text(text = viewModel.telephoneError, color = MaterialTheme.colorScheme.error)
}
@Composable
fun CellphoneField(viewModel: AdoptionViewModel) {
    OutlinedTextField(
        keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Phone),
        value = viewModel.cellphone,
        onValueChange = { viewModel.onCellphoneChange(it) },
        label = { Text(text = "Cellphone") },
        isError = viewModel.cellphoneError.isNotEmpty(),
        leadingIcon = {
            Icon(
                imageVector = Icons.Filled.PhoneAndroid,
                contentDescription = "Cellphone icon"
            )
        },
        maxLines = 1
    )
    Text(text = viewModel.cellphoneError, color = MaterialTheme.colorScheme.error)
}
@Composable
fun EmailField(viewModel: AdoptionViewModel) {
    OutlinedTextField(
        keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Email),
        value = viewModel.email,
        onValueChange = { viewModel.onEmailChange(it) },
        label = { Text(text = "Email") },
        isError = viewModel.emailError.isNotEmpty(),
        leadingIcon = {
            Icon(
                imageVector = Icons.Filled.Mail,
                contentDescription = "email icon"
            )
        },
        maxLines = 1,
        modifier = Modifier.fillMaxWidth()
    )
    Text(text = viewModel.emailError, color = MaterialTheme.colorScheme.error)
}