@file:OptIn(ExperimentalMaterial3Api::class, ExperimentalMaterial3Api::class,
    ExperimentalMaterial3Api::class, ExperimentalMaterial3Api::class
)

package com.project.puppyplace.ui.login

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AlternateEmail
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Remove
import androidx.compose.material.icons.filled.RemoveRedEye
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.project.puppyplace.R
import com.project.puppyplace.navigation.Destination

@Composable
fun LoginScreen(
    viewModel: LoginViewModel = hiltViewModel(),
    navController: NavController
){
    if(viewModel.state.collectAsStateWithLifecycle().value.isLoading) {
        LoadingIndicator()
    }else{
        LoginScreenContent(viewModel, navController)
    }

}
@Composable
fun LoginScreenContent(viewModel: LoginViewModel, navController: NavController){
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ){
        Image(
            painter = painterResource(R.drawable.login_background),
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
            WelcomeMessageText()
            LoginCard(viewModel, navController)
        }
    }
}
@Composable
fun WelcomeMessageText(){
    Text(
        text = "Welcome!",
        style = MaterialTheme.typography.displayLarge
    )
}
@ExperimentalMaterial3Api
@Composable
fun LoginCard(
    viewModel: LoginViewModel,
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
            border = CardDefaults.outlinedCardBorder(enabled = true),
        ) {
            LoginTitle()
            EmailTextField(viewModel = viewModel)
            PasswordTextField(viewModel = viewModel, navController = navController)
            LoginButton(viewModel, navController)
            SignUpButton(navController)
        }
    }
}

@Composable
fun LoginTitle(){
    Text(
        text = "LogIn",
        style = MaterialTheme.typography.headlineLarge,
        modifier = Modifier.padding(16.dp)
    )
}
@ExperimentalMaterial3Api
@Composable
fun EmailTextField(viewModel: LoginViewModel){
    OutlinedTextField(
        keyboardOptions = KeyboardOptions.Default.copy(
            keyboardType = KeyboardType.Email,
            imeAction = ImeAction.Next),
        value = viewModel.email,
        onValueChange = { viewModel.onEmailChange(it)},
        label = { Text(text = "Email") },
        leadingIcon = {
            Icon(
                imageVector = Icons.Filled.AlternateEmail,
                contentDescription = "At icon"
            )
        },
        maxLines = 1,
        singleLine = true,
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
    )
    Text(
        text = viewModel.emailError,
        color = MaterialTheme.colorScheme.error,
        modifier = Modifier.padding(start = 16.dp)
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PasswordTextField(viewModel: LoginViewModel, navController: NavController){
    OutlinedTextField(
        keyboardOptions = KeyboardOptions.Default.copy(
            keyboardType = KeyboardType.Password,
            imeAction = ImeAction.Next),
        keyboardActions = KeyboardActions(
            onDone = { viewModel.logIn(navController) }
        ),
        value = viewModel.password,
        onValueChange = { viewModel.onPasswordChange(it) },
        label = { Text(text = "Password") },
        leadingIcon = {
            Icon(
                imageVector = Icons.Filled.Lock,
                contentDescription = "Password icon"
            )
        },
        trailingIcon = {
            IconButton(onClick = { viewModel.onShowPasswordClick() }) {
                Icon(
                    imageVector = if(!viewModel.hidePassword) Icons.Filled.RemoveRedEye
                    else Icons.Filled.Remove,
                    contentDescription = "Password icon"
                )
            }
        },
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        maxLines = 1,
        singleLine = true,
        visualTransformation = if(viewModel.hidePassword) PasswordVisualTransformation() else VisualTransformation.None
    )
    Text(
        text = viewModel.passwordError,
        color = MaterialTheme.colorScheme.error,
        modifier = Modifier.padding(start = 16.dp)
    )
}

@Composable
fun LoginButton(viewModel: LoginViewModel, navController: NavController){
    Box(
        modifier = Modifier.fillMaxWidth()
    ){
        Button(
            modifier = Modifier
                .padding(8.dp)
                .align(Alignment.Center),
            onClick = {
                viewModel.logIn(navController)
            }
        ) {
            Text(
                text = "LogIn",
                style = MaterialTheme.typography.titleLarge
            )
        }
    }
}

@Composable
fun SignUpButton(
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
                text = "Don't have an account? ",
                style = MaterialTheme.typography.bodyMedium,
            )
            Text(
                text = "Sign Up",
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.primary,
                modifier = Modifier.clickable { navController.navigate(Destination.signUp.route) }
            )
        }
    }
}

@Composable
fun LoadingIndicator(){
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        CircularProgressIndicator()
        Row(
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
        ){
            Text(text = "Checking data...")
        }
    }
}