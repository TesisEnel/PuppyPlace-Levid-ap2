@file:OptIn(ExperimentalMaterial3Api::class, ExperimentalMaterial3Api::class)

package com.project.puppyplace.ui.home

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.HeartBroken
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Transgender
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
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.project.puppyplace.data.remote.dto.DogDto
import com.project.puppyplace.navigation.Destination

@Composable
fun HomeScreen(
    viewModel: HomeViewModel = hiltViewModel(),
    navController: NavController
) {
    val state = viewModel.state.collectAsStateWithLifecycle()
    HomeScreenContent(state.value.dogsList, navController)

}
@Composable
fun HomeScreenContent(dogsList: List<DogDto>, navController: NavController){
    Column {
        HomeTopBar()
        SearchTextField()
        DogsList(dogsList = dogsList, navController = navController)
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
fun DogsList(dogsList: List<DogDto>, navController: NavController){
    LazyVerticalGrid(
        GridCells.Fixed(2),
        modifier = Modifier.fillMaxHeight()
    ) {
        items(dogsList) { dog ->
            DogItem(dog = dog, navController)
        }
    }
}

@Composable
fun DogItem(dog: DogDto, navController: NavController){
    Column(
        modifier = Modifier
            .size(200.dp)
            .padding(8.dp)
            .clip(MaterialTheme.shapes.medium)
    ) {
        Box(
            modifier = Modifier.clickable {
                navController.navigate(Destination.login.route)
            }
        ){
            AsyncImage(
                model = dog.image,
                contentDescription = dog.name,
                modifier = Modifier.fillMaxHeight(),
                contentScale = ContentScale.FillBounds
            )
            Column(
                modifier = Modifier.align(Alignment.BottomStart)
            ) {
                Column {
                    Text(
                        text = dog.name,
                        modifier = Modifier
                            .padding(horizontal = 8.dp),
                        color = MaterialTheme.colorScheme.onSecondary
                    )

                }
                Row {
                    Column {
                        Text(
                            text = "40 years",
                            modifier = Modifier
                                .padding(horizontal = 8.dp, vertical = 4.dp),
                            color = MaterialTheme.colorScheme.onSecondary
                        )
                    }
                    Column(
                        modifier = Modifier.weight(2f)
                    ) {
                        Icon(
                            imageVector = Icons.Filled.Transgender,
                            contentDescription = "Gender",
                            tint = MaterialTheme.colorScheme.onSecondary
                        )
                    }
                    Column(
                        modifier = Modifier.weight(1f)
                    ) {
                        IconButton(onClick = {

                        }) {
                            Icon(
                                imageVector = Icons.Filled.HeartBroken,
                                contentDescription = "LikeIcon",
                                tint = MaterialTheme.colorScheme.onSecondary
                            )
                        }
                    }
                }
            }

        }
    }
}
