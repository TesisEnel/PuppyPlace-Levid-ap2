@file:OptIn(ExperimentalMaterial3Api::class, ExperimentalMaterial3Api::class)

package com.project.puppyplace.ui.home

import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
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
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.HeartBroken
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Transgender
import androidx.compose.material3.AssistChip
import androidx.compose.material3.AssistChipDefaults
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
    HomeScreenContent(state.value.dogsList, navController, viewModel)

}
@Composable
fun HomeScreenContent(
    dogsList: List<DogDto>,
    navController: NavController,
    viewModel: HomeViewModel
){
    Column {
        HomeTopBar()
        SearchTextField()
        ChipGroup()
        DogsList(dogsList = dogsList, navController = navController, viewModel = viewModel)
    }
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
                    contentDescription = "Menu"
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
fun ChipGroup(){
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row(
            modifier = Modifier.horizontalScroll(rememberScrollState())
        ) {
            Column {
                Text(
                    text = "More",
                    modifier = Modifier
                        .padding(8.dp),
                    color = MaterialTheme.colorScheme.onTertiary,
                    style = MaterialTheme.typography.titleLarge
                )
            }
            Column(
                modifier = Modifier.padding(end = 8.dp)
            ){
                AssistChip(
                    onClick = { /*TODO*/ },
                    label = { Text(text = "Female") },
                    colors = AssistChipDefaults.assistChipColors(
                        containerColor = MaterialTheme.colorScheme.primary,
                        labelColor = MaterialTheme.colorScheme.onTertiary
                    )
                )
            }
            Column(
                modifier = Modifier.padding(end = 8.dp)
            ) {
                AssistChip(
                    onClick = { /*TODO*/ },
                    label = { Text(text = "Male") },
                    colors = AssistChipDefaults.assistChipColors(
                        containerColor = MaterialTheme.colorScheme.onTertiary,
                        labelColor = MaterialTheme.colorScheme.surface
                    )
                )
            }
            Column(
                modifier = Modifier.padding(end = 8.dp)
            ) {
                AssistChip(
                    onClick = { /*TODO*/ },
                    label = { Text(text = "Corgi") },
                    colors = AssistChipDefaults.assistChipColors(
                        containerColor = MaterialTheme.colorScheme.secondary,
                        labelColor = MaterialTheme.colorScheme.onTertiary
                    )
                )
            }
            Column(
                modifier = Modifier.padding(end = 8.dp)
            ){
                AssistChip(
                    onClick = { /*TODO*/ },
                    label = { Text(text = "Golden") },
                    colors = AssistChipDefaults.assistChipColors(
                        containerColor = MaterialTheme.colorScheme.primary,
                        labelColor = MaterialTheme.colorScheme.onTertiary
                    )
                )
            }
        }
    }
}
@Composable
fun DogsList(
    dogsList: List<DogDto>,
    navController: NavController,
    viewModel: HomeViewModel
){
    LazyVerticalGrid(
        GridCells.Fixed(2),
        modifier = Modifier.fillMaxHeight()
    ) {
        items(dogsList) { dog ->
            DogItem(dog = dog, navController, viewModel)
        }
    }
}

@Composable
fun DogItem(
    dog: DogDto,
    navController: NavController,
    viewModel: HomeViewModel
){
    Column(
        modifier = Modifier
            .size(200.dp)
            .padding(8.dp)
            .clip(MaterialTheme.shapes.medium)
    ) {
        Box(
            modifier = Modifier.clickable {
                viewModel.onDogSelected(dog)
                navController.navigate(Destination.dogDetail.route)
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
