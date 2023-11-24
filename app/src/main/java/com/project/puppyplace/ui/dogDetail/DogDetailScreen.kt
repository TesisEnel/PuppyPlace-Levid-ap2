@file:OptIn(ExperimentalMaterial3Api::class)

package com.project.puppyplace.ui.dogDetail

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Beenhere
import androidx.compose.material.icons.filled.Bloodtype
import androidx.compose.material.icons.filled.Cake
import androidx.compose.material.icons.filled.DownhillSkiing
import androidx.compose.material.icons.filled.Healing
import androidx.compose.material.icons.filled.HeartBroken
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Scanner
import androidx.compose.material.icons.filled.SignalCellular0Bar
import androidx.compose.material.icons.filled.Transgender
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.project.puppyplace.data.remote.dto.DogDto

@Composable
fun DogDetailScreen(
    viewModel: DogDetailViewModel = hiltViewModel(),
    navController: NavController
) {
    val dog: DogDto = viewModel.dog
    Scaffold(
        floatingActionButton = {
            FABAdoptMe()
        }
    ) {paddingValues ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ){
            AsyncImage(
                model = dog.image,
                contentDescription = dog.name,
                contentScale = ContentScale.FillHeight,
            )
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .align(alignment = Alignment.BottomCenter)
                    .border(1.dp, color = MaterialTheme.colorScheme.onSurface),
            ) {
                DisplayDogInfo()
            }
        }
    }
}

@Composable
fun DisplayDogInfo(){
    Card(
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surface
        ),
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Row {
                Column(
                    modifier = Modifier.weight(1f)
                ) {
                    Text(
                        text = "Name",
                        style = MaterialTheme.typography.headlineLarge,
                    )
                }
                Column {
                    Icon(imageVector = Icons.Filled.HeartBroken, contentDescription = "")
                }
            }
            Row {
                Column(
                    modifier = Modifier.weight(1f)
                ) {
                    Text(text = "Breed")
                }
                Column {
                    Icon(imageVector = Icons.Filled.Scanner, contentDescription = "")
                }
            }
            Row {
                Column(
                    modifier = Modifier.weight(1f)
                ) {
                    Row(
                        modifier = Modifier.padding(8.dp)
                    ) {
                        Icon(
                            imageVector = Icons.Default.Cake,
                            contentDescription = "",
                            tint = MaterialTheme.colorScheme.primary
                        )
                        Text(text = "Age")
                    }
                }
                Column(
                    modifier = Modifier.weight(1f)
                ) {
                    Row(
                        modifier = Modifier.padding(8.dp)
                    ) {
                        Icon(
                            imageVector = Icons.Default.Transgender,
                            contentDescription = "",
                            tint = MaterialTheme.colorScheme.primary
                        )
                        Text(text = "Gender")
                    }
                }
            }
            Row {
                Column(
                    modifier = Modifier.weight(1f)
                ) {
                    Row(
                        modifier = Modifier.padding(8.dp)
                    ) {
                        Icon(
                            imageVector = Icons.Default.SignalCellular0Bar,
                            contentDescription = "",
                            tint = MaterialTheme.colorScheme.primary
                        )
                        Text(text = "Height")
                    }
                }
                Column(
                    modifier = Modifier.weight(1f)
                ) {
                    Row(
                        modifier = Modifier.padding(8.dp)
                    ){
                        Icon(
                            imageVector = Icons.Default.Beenhere,
                            contentDescription = "",
                            tint = MaterialTheme.colorScheme.primary
                        )
                        Text(text = "Weight")
                    }
                }
            }
            Row {
                Column(
                    modifier = Modifier.weight(1f)
                ) {
                    Row(
                        modifier = Modifier.padding(8.dp)
                    ) {
                        Icon(
                            imageVector = Icons.Default.Bloodtype,
                            contentDescription = "",
                            tint = MaterialTheme.colorScheme.primary
                        )
                        Text(text = "Color")
                    }
                }
                Column(
                    modifier = Modifier.weight(1f)
                ) {
                    Row(
                        modifier = Modifier.padding(8.dp)
                    ){
                        Icon(
                            imageVector = Icons.Default.Healing,
                            contentDescription = "",
                            tint = MaterialTheme.colorScheme.primary
                        )
                        Text(text = "Activity")
                    }
                }
            }
            Row {
                Column(
                    modifier = Modifier.weight(1f)
                ) {
                    Row(
                        modifier = Modifier.padding(8.dp)
                    ) {
                        Icon(
                            imageVector = Icons.Default.Home,
                            contentDescription = "",
                            tint = MaterialTheme.colorScheme.primary
                        )
                        Text(text = "Origin")
                    }
                }
                Column(
                    modifier = Modifier.weight(1f)
                ) {
                    Row(
                        modifier = Modifier.padding(8.dp)
                    ){
                        Icon(
                            imageVector = Icons.Default.DownhillSkiing,
                            contentDescription = "",
                            tint = MaterialTheme.colorScheme.primary
                        )
                        Text(text = "Personality")
                    }
                }
            }
            Row {
                Text(
                    text = "About",
                    style = MaterialTheme.typography.titleLarge,
                )
            }
            Row {
                Text(text = "Description")
            }
        }
    }
}

@Composable
fun FABAdoptMe(){
    Box(
        modifier = Modifier.fillMaxWidth()
    ){
        ExtendedFloatingActionButton(
            onClick = { /*TODO*/ },
            containerColor = MaterialTheme.colorScheme.primary,
            modifier = Modifier.align(Alignment.BottomCenter)
        ) {
            Text(
                text = "Adopt me!",
                style = MaterialTheme.typography.titleLarge,
            )
        }
    }
}
