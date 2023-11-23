@file:OptIn(ExperimentalMaterial3Api::class, ExperimentalMaterial3Api::class,
    ExperimentalMaterial3Api::class, ExperimentalMaterial3Api::class
)

package com.project.puppyplace.ui.signup

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AlternateEmail
import androidx.compose.material.icons.filled.ArtTrack
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.HomeWork
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material.icons.filled.PhoneAndroid
import androidx.compose.material.icons.filled.Transgender
import androidx.compose.material3.Button
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.project.puppyplace.R
import com.project.puppyplace.navigation.Destination

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SignUpScreen(
    navController: NavController
){
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ){
        Image(
            painter = painterResource(R.drawable.signup_background),
            contentDescription = "Login background image",
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.FillHeight
        )
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            horizontalAlignment =  Alignment.Start,
            verticalArrangement = Arrangement.Center
        ) {
            SignUpCard(navController)
        }

    }
}

@ExperimentalMaterial3Api
@Composable
fun SignUpCard(
    navController: NavController
){
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        horizontalAlignment = Alignment.Start
    ) {
        Card(
            colors = CardDefaults.cardColors(
                containerColor = MaterialTheme.colorScheme.surface,
                contentColor = MaterialTheme.colorScheme.onSurface
            ),
            border = CardDefaults.outlinedCardBorder(enabled = true)

        ){
            SignUpTitle()
            IdentificationNumberTextField()
            Row {
                Column(
                    modifier = Modifier.weight(1f)
                ){
                    NameTextField()
                }
                Column(
                    modifier = Modifier.weight(1f)
                ) {
                    SurnameTextField()
                }
            }
            EmailTextField()
            PasswordTextField()
            AddressTextField()
            Row {
                Column(
                    modifier = Modifier.weight(1f)
                ){
                    TelephoneTextField()
                }
                Column(
                    modifier = Modifier.weight(1f)
                ) {
                    CellphoneTextField()
                }
            }
            Row {
                Column(
                    modifier = Modifier.weight(1f)
                ) {
                    GenderTextField()
                }
                Column(
                    modifier = Modifier.weight(1f)
                ) {
                    HouseTypeTextField()
                }
            }
            SignUpButton()
            AlreadyAnAccountTextField(navController)
        }
    }
}
@Composable
fun SignUpTitle(){
    Text(
        text = "Sign Up",
        style = MaterialTheme.typography.headlineLarge,
        modifier = Modifier.padding(16.dp)
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NameTextField(){
    OutlinedTextField(
        value = "",
        onValueChange = { TODO() },
        label = { Text(text = "First Name") },
        leadingIcon = {
            Icon(
                imageVector = Icons.Filled.Person,
                contentDescription = "At icon"
            )
        },
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
    )
}
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SurnameTextField(){
    OutlinedTextField(
        value = "",
        onValueChange = { TODO() },
        label = { Text(text = "Surname") },
        leadingIcon = {
            Icon(
                imageVector = Icons.Filled.Person,
                contentDescription = "At icon"
            )
        },
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
    )
}
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun IdentificationNumberTextField(){
    OutlinedTextField(
        value = "",
        onValueChange = { TODO() },
        label = { Text(text = "Identification number") },
        leadingIcon = {
            Icon(
                imageVector = Icons.Filled.ArtTrack,
                contentDescription = "At icon"
            )
        },
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
    )
}

@ExperimentalMaterial3Api
@Composable
fun EmailTextField(){
    OutlinedTextField(
        value = "",
        onValueChange = { TODO() },
        label = { Text(text = "Email") },
        leadingIcon = {
            Icon(
                imageVector = Icons.Filled.AlternateEmail,
                contentDescription = "At icon"
            )
        },
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
    )
}
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PasswordTextField(){
    OutlinedTextField(
        value = "",
        onValueChange = { TODO() },
        label = { Text(text = "Password") },
        leadingIcon = {
            Icon(
                imageVector = Icons.Filled.Lock,
                contentDescription = "Password icon"
            )
        },
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        maxLines = 1
    )
}
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddressTextField(){
    OutlinedTextField(
        value = "",
        onValueChange = { TODO() },
        label = { Text(text = "Address") },
        leadingIcon = {
            Icon(
                imageVector = Icons.Filled.Home,
                contentDescription = "Address icon"
            )
        },
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        maxLines = 1
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TelephoneTextField(){
    OutlinedTextField(
        value = "",
        onValueChange = { TODO() },
        label = { Text(text = "Telephone") },
        leadingIcon = {
            Icon(
                imageVector = Icons.Filled.Phone,
                contentDescription = "Telephone icon"
            )
        },
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        maxLines = 1
    )
}
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CellphoneTextField(){
    OutlinedTextField(
        value = "",
        onValueChange = { TODO() },
        label = { Text(text = "Cellphone") },
        leadingIcon = {
            Icon(
                imageVector = Icons.Filled.PhoneAndroid,
                contentDescription = "Cellphone icon"
            )
        },
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        maxLines = 1
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GenderTextField(){
    OutlinedTextField(
        value = "",
        onValueChange = { TODO() },
        label = { Text(text = "Gender") },
        leadingIcon = {
            Icon(
                imageVector = Icons.Filled.Transgender,
                contentDescription = "Sex icon"
            )
        },
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        maxLines = 1
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HouseTypeTextField(){
    OutlinedTextField(
        value = "",
        onValueChange = { TODO() },
        label = { Text(text = "House type") },
        leadingIcon = {
            Icon(
                imageVector = Icons.Filled.HomeWork,
                contentDescription = "House icon"
            )
        },
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        maxLines = 1
    )
}
@Composable
fun SignUpButton(){
    Box(
        modifier = Modifier.fillMaxWidth()
    ){
        Button(
            modifier = Modifier
                .padding(8.dp)
                .align(Alignment.Center),
            onClick = { /*TODO*/ }
        ) {
            Text(
                text = "Sign Up",
                style = MaterialTheme.typography.titleLarge
            )
        }
    }
}

@Composable
fun AlreadyAnAccountTextField(
    navController: NavController
){
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
    ){
        Row(
            modifier = Modifier.align(Alignment.Center),
        ) {
            Text(
                text = "Do you have an account? ",
                style = MaterialTheme.typography.bodyMedium,
            )
            Text(
                text = "LogIn",
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.primary,
                modifier = Modifier.clickable { navController.navigate(Destination.login.route)}
            )
        }
    }
}