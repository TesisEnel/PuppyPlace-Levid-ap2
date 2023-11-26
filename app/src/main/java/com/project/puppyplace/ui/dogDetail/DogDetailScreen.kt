@file:OptIn(ExperimentalMaterial3Api::class)

package com.project.puppyplace.ui.dogDetail

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Adjust
import androidx.compose.material.icons.filled.Anchor
import androidx.compose.material.icons.filled.Cake
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Female
import androidx.compose.material.icons.filled.HeartBroken
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.InvertColors
import androidx.compose.material.icons.filled.Male
import androidx.compose.material.icons.filled.Pets
import androidx.compose.material.icons.filled.SignalCellular0Bar
import androidx.compose.material.icons.outlined.MonitorHeart
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.project.puppyplace.data.remote.dto.DogDto

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DogDetailScreen(
    viewModel: DogDetailViewModel = hiltViewModel(),
    navController: NavController
) {
    val dog by remember { mutableStateOf(viewModel.dog) }
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
                DisplayDogInfo(dog, viewModel)
            }
        }
    }
}

@Composable
fun DisplayDogInfo(dog: DogDto, viewModel: DogDetailViewModel){
    var isLiked by remember { mutableStateOf(false) }
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(340.dp),
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
                        text = dog.name,
                        style = MaterialTheme.typography.headlineLarge,
                    )
                }
                Column {
                    IconButton(onClick = {
                        isLiked = !isLiked
                        viewModel.onLikedClicked(dog, isLiked)
                    }) {
                        Icon(
                            imageVector =
                                if(dog.isLiked) Icons.Filled.Favorite
                                else Icons.Filled.HeartBroken,
                            contentDescription = "",
                            tint =
                                if(dog.isLiked) MaterialTheme.colorScheme.error
                                else MaterialTheme.colorScheme.onTertiary
                        )
                    }
                }
            }
            Row {
                Column(
                    modifier = Modifier.weight(1f)
                ) {
                    Text(text = dog.breed)
                }
                Column {
                    if(viewModel.isSterilized(dog)){
                        Icon(
                            imageVector = Icons.Filled.Adjust,
                            contentDescription = "Sterilized icon"
                        )
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
                            imageVector = Icons.Default.Cake,
                            contentDescription = "BirthDate icon ${dog.birthDate}",
                            tint = MaterialTheme.colorScheme.primary
                        )
                        Text(text = dog.birthDate)
                    }
                }
                Column(
                    modifier = Modifier.weight(1f)
                ) {
                    Row(
                        modifier = Modifier.padding(8.dp)
                    ) {
                        Icon(
                            imageVector =
                                if(viewModel.isMale(dog)) Icons.Filled.Male
                                else Icons.Filled.Female,
                            contentDescription = "Dog gender ${dog.gender}",
                            tint = MaterialTheme.colorScheme.primary
                        )
                        Text(text = dog.gender)
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
                            imageVector = Icons.Filled.SignalCellular0Bar,
                            contentDescription = "",
                            tint = MaterialTheme.colorScheme.primary
                        )
                        Text(text = dog.size)
                    }
                }
                Column(
                    modifier = Modifier.weight(1f)
                ) {
                    Row(
                        modifier = Modifier.padding(8.dp)
                    ){
                        Icon(
                            imageVector = Icons.Filled.Anchor,
                            contentDescription = "",
                            tint = MaterialTheme.colorScheme.primary
                        )
                        Text(text = "${dog.weight} kg")
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
                            imageVector = Icons.Default.InvertColors,
                            contentDescription = "",
                            tint = MaterialTheme.colorScheme.primary
                        )
                        Text(text = dog.hairColor)
                    }
                }
                Column(
                    modifier = Modifier.weight(1f)
                ) {
                    Row(
                        modifier = Modifier.padding(8.dp)
                    ){
                        Icon(
                            imageVector = Icons.Outlined.MonitorHeart,
                            contentDescription = "",
                            tint = MaterialTheme.colorScheme.primary
                        )
                        Text(text = dog.activityLevel)
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
                        Text(text = dog.origin)
                    }
                }
                Column(
                    modifier = Modifier.weight(1f)
                ) {
                    Row(
                        modifier = Modifier.padding(8.dp)
                    ){
                        Icon(
                            imageVector = Icons.Default.Pets,
                            contentDescription = "",
                            tint = MaterialTheme.colorScheme.primary
                        )
                        Text(text = dog.behaviour)
                    }
                }
            }
            Row {
                Text(
                    text = "About",
                    style = MaterialTheme.typography.titleLarge,
                )
            }
            LazyColumn(
                modifier = Modifier
                    .width(280.dp)
            ) {
                item{
                    Text(
                        text = dog.description,
                        style = MaterialTheme.typography.bodyMedium,
                        overflow = TextOverflow.Clip
                    )
                }
            }
        }
    }
}

@Composable
fun FABAdoptMe(){
    Box(
        modifier = Modifier.fillMaxWidth()
    ){
        FloatingActionButton(
            onClick = { /*TODO*/ },
            containerColor = MaterialTheme.colorScheme.primary,
            modifier = Modifier
                .padding(horizontal = 8.dp, vertical = 32.dp)
                .align(alignment = Alignment.BottomEnd)
        ) {
            Icon(
                imageVector = Icons.Default.Pets,
                contentDescription = "",
                tint = MaterialTheme.colorScheme.onPrimary
            )
        }
    }
}
