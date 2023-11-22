@file:OptIn(ExperimentalMaterial3Api::class)

package com.project.puppyplace.ui.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.project.puppyplace.data.remote.dto.DogDto

@Composable
fun HomeScreen(
    viewModel: HomeViewModel = hiltViewModel(),
    navController: NavController
) {
    val state = viewModel.state.collectAsStateWithLifecycle()
    HomeScreenContent(state.value.dogsList)

}
@Composable
fun HomeScreenContent(dogsList: List<DogDto>){
    Column {
        HomeTopBar()
        SearchTextField()
        DogsList(dogsList = dogsList)
    }
}
@Composable
fun SearchTextField(){
    TextField(
        value = "",
        onValueChange = { /*TODO*/ },
        placeholder = { Text(text = "Search")},
        trailingIcon = {
            Icon(
                imageVector = Icons.Filled.Search,
                contentDescription = "Search"
            )
        },
        modifier = Modifier
            .padding(horizontal = 16.dp)
            .fillMaxWidth(),
        colors = TextFieldDefaults.textFieldColors(
            containerColor = MaterialTheme.colorScheme.tertiary,
            textColor = MaterialTheme.colorScheme.onTertiary,
        )
    )

}
@Composable
fun HomeTopBar(){
    CenterAlignedTopAppBar(
        title = {
            Text(text = "Discover")
        },
        navigationIcon = {
            IconButton(
                onClick = { /*TODO*/ }
            ) {
                Icon(
                    imageVector = Icons.Filled.Menu,
                    contentDescription = "Logout"
                )
            }
        },
        actions = {
            IconButton(
                onClick = { /*TODO*/ }
            ) {
                Icon(
                    imageVector = Icons.Filled.AccountCircle,
                    contentDescription = "Logout"
                )
            }
        }
    )
}
@Composable
fun DogsList(dogsList: List<DogDto>){
    LazyColumn(
        modifier = Modifier.padding(16.dp)
    ) {
        items(dogsList) { dog ->
            DogItem(dog = dog)
        }
    }
}

@Composable
fun DogItem(dog: DogDto){
    Box(
        modifier = Modifier
            .size(200.dp, 300.dp)
    ){
        AsyncImage(
            model = dog.image,
            contentDescription = dog.name,
            modifier = Modifier.fillMaxHeight()
        )
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Bottom,
            horizontalAlignment = Alignment.Start
        ) {
            Text(text = dog.name!!)
            Text(text = dog.breed!!)
            Text(text = dog.birthDate!!)
        }
    }
}
