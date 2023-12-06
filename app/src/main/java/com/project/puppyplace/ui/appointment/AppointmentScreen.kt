@file:OptIn(ExperimentalMaterial3Api::class, ExperimentalMaterial3Api::class)

package com.project.puppyplace.ui.appointment

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.CalendarMonth
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Logout
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material.icons.filled.PhoneAndroid
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.project.puppyplace.R
import com.project.puppyplace.data.remote.dto.AppointmentDto
import com.project.puppyplace.util.appBottomBar.AppBottomBar
import java.text.SimpleDateFormat

@Composable
fun AppointmentScreen(
    viewModel: AppointmentViewModel = hiltViewModel(),
    navController: NavController
){
    Scaffold(
        topBar = {
            TopBar(navController = navController, viewModel = viewModel)
        },
        bottomBar = {
            AppBottomBar(navController = navController)
        }
    ) {paddingValues ->
        val appointments by viewModel.stateAdoption.collectAsStateWithLifecycle()
        Box(
            modifier = Modifier.fillMaxSize().padding(paddingValues)
        ) {
            Image(
                painter = painterResource(R.drawable.background_user),
                contentDescription = "User background image",
                modifier = Modifier.fillMaxSize(),
                contentScale = ContentScale.FillHeight
            )
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .align(Alignment.Center)
                    .padding(16.dp)
            ) {
                NameUserText(viewModel = viewModel)
                InfoUser(viewModel = viewModel)
                AppointmentsList(appointments.adoptionList, viewModel, navController)
            }
        }
    }
}

@Composable
fun NameUserText(viewModel: AppointmentViewModel){
    Row{
        Column {
            Text(
                text ="Hi! " + viewModel.user.name ,
                style = MaterialTheme.typography.displayMedium
            )
            Spacer(modifier = Modifier.width(12.dp))
            Text(text =viewModel.user.surname,
                style = MaterialTheme.typography.displayMedium
            )
        }
        Column(modifier = Modifier
            .weight(0.1f)
        ) {
            Image(
                painter = painterResource(R.drawable.perro_perfil),
                contentDescription = "Dog image",
                contentScale = ContentScale.FillHeight
            )
        }
    }
}

@Composable
fun InfoUser(viewModel: AppointmentViewModel){
    Card(
        colors = CardDefaults.cardColors(
            containerColor= MaterialTheme.colorScheme.secondary
        ),
        border = CardDefaults.outlinedCardBorder(enabled = false),
        modifier = Modifier
            .fillMaxWidth()
    ){
        Row {
            Column(
                modifier = Modifier
                    .weight(1f)
                    .padding(8.dp)
            ){
                Row {
                    Icon(imageVector = Icons.Filled.Phone, contentDescription = "Telephone")
                    Text(text = viewModel.user.telephone)
                }
            }
            Column(
                modifier = Modifier
                    .weight(1f)
                    .padding(8.dp)

            ){
                Row {
                    Icon(imageVector = Icons.Filled.PhoneAndroid, contentDescription = "Cellphone")
                    Text(text = viewModel.user.cellphone,)
                }
            }
        }
        Row {
            Column(
                modifier = Modifier
                    .weight(1f)
                    .padding(8.dp)

            ){
                Row {
                    Icon(imageVector = Icons.Filled.Email, contentDescription = "Email")
                    Text(text = viewModel.user.email,)
                }
            }
            Column(
                modifier = Modifier
                    .weight(1f)
                    .padding(8.dp)

            ){
                Row {
                    Icon(imageVector = Icons.Filled.LocationOn, contentDescription = "Address")
                    Text(text = viewModel.user.address,)
                }
            }
        }
    }
    Spacer(modifier = Modifier.height(8.dp))
}

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBar(navController: NavController, viewModel: AppointmentViewModel= hiltViewModel()){
    CenterAlignedTopAppBar(
        title = {
            Text(
                text = ""
            )
        },
        navigationIcon = {
            IconButton(
                onClick = {
                    viewModel.BackHome(navController)
                }
            ) {
                Icon(
                    modifier = Modifier
                        .size(40.dp),
                    imageVector = Icons.Filled.ArrowBack,
                    contentDescription = "Atras",
                    tint = Color(0xFF4E2A00)
                )

            }
        },
        actions = {
            IconButton(
                onClick = {
                    viewModel.logOut(navController)
                }
            ) {
                Icon(
                    modifier = Modifier
                        .size(40.dp),
                    imageVector = Icons.Filled.Logout,
                    contentDescription = "Logout",
                    tint = Color(0xFF4E2A00)
                )
            }
        },
        colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
            containerColor = Color.Transparent
        ),
        modifier = Modifier.fillMaxWidth()
    )
}

@Composable
fun AppointmentsList(appoiments: List<AppointmentDto>, viewModel: AppointmentViewModel, navController: NavController){
    Text(text = "Appointments: ",
        style = MaterialTheme.typography.titleLarge
    )
    LazyColumn{
        items(appoiments){
            appoiment -> AppoimentItem(viewModel, appoiment, navController = navController)
        }
    }

}

@SuppressLint("SimpleDateFormat")
@Composable
fun AppoimentItem(viewModel:AppointmentViewModel, appoiment: AppointmentDto, navController: NavController){
    val sdf = SimpleDateFormat("yyyy-MM-dd")
    val date = sdf.parse(appoiment.date)
    val formattedDate = sdf.format(date!!)


    Card(
        colors = CardDefaults.cardColors(
            containerColor= MaterialTheme.colorScheme.tertiary
        ),
        border = CardDefaults.outlinedCardBorder(enabled = false),
        modifier = Modifier
            .fillMaxWidth()
    ){

        Column(
            modifier = Modifier
                .padding(8.dp)

        ) {
            Row {
                Icon(imageVector = Icons.Filled.CalendarMonth, contentDescription = "update")
                Text(text = formattedDate)
            }
        }
        Column(
            modifier = Modifier
                .padding(8.dp)

        ) {
            Row {
                Icon(imageVector = Icons.Filled.LocationOn, contentDescription = "location")
                Text(text = appoiment.address)
            }
        }

        Row {
            Column(
                modifier = Modifier
                    .padding(8.dp)
            ) {
                Row {
                    Icon(imageVector = Icons.Filled.Person, contentDescription = "person")
                    Text(text = appoiment.userName)
                    Text(text = appoiment.userSurname)
                }
            }
            Column(
                modifier = Modifier
                    .padding(8.dp)
            ) {
                Row {
                    Icon(imageVector = Icons.Filled.Email, contentDescription = "email")
                    Text(text = appoiment.email)
                }
            }
        }
        Row {
            Column(
                modifier = Modifier
                    .padding(8.dp)
            ) {
                Row {
                    Icon(imageVector = Icons.Filled.Phone, contentDescription = "telephone")
                    Text(text = appoiment.telephone)
                }
            }
            Column(
                modifier = Modifier
                    .padding(8.dp)
            ) {
                Row {
                    Icon(imageVector = Icons.Filled.PhoneAndroid, contentDescription = "cellphone")
                    Text(text = appoiment.cellphone)
                }
            }
        }
        //Botones
        Divider(thickness = 1.dp)
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center
        ) {
            Column(
                modifier = Modifier.weight(1f),
                horizontalAlignment = Alignment.CenterHorizontally
            ){
                TextButton(
                    onClick = { viewModel.deleteAppointment(appoiment.id) }
                ) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.Center
                    ) {
                        Column(
                            modifier = Modifier.weight(1f),
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Icon(
                                imageVector = Icons.Filled.Delete,
                                contentDescription = "Delete",
                                tint = MaterialTheme.colorScheme.error
                            )
                            Text(text = "Delete", color = MaterialTheme.colorScheme.error)
                        }
                    }
                }
            }
            Column(
                modifier = Modifier.weight(1f),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                TextButton(
                    onClick = {
                        viewModel.onModifyPressed(
                        navController = navController,
                        appointmentDto = appoiment
                        )
                    }
                ) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.Center
                    ) {
                        Column(
                            modifier = Modifier.weight(1f),
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Icon(
                                imageVector = Icons.Filled.Edit,
                                contentDescription = "Modify"
                            )
                            Text(text = "Edit")
                        }
                    }
                }
            }
        }
    }
    Spacer(modifier = Modifier.height(8.dp))
}