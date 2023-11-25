@file:OptIn(ExperimentalMaterial3Api::class, ExperimentalMaterial3Api::class,
    ExperimentalMaterial3Api::class
)

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
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Favorite
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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
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
    HomeScreenContent(state.value.dogsList, navController, viewModel)

}
@Composable
fun HomeScreenContent(
    dogsList: List<DogDto>,
    navController: NavController,
    viewModel: HomeViewModel
){
    Column {
        HomeTopBar(viewModel, navController)
        SearchTextField(viewModel)
        ChipGroup(viewModel)
        DogsList(dogsList = dogsList, navController = navController, viewModel = viewModel)
    }
}
@Composable
fun HomeTopBar(viewModel: HomeViewModel, navController: NavController){
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
                onClick = {
                    viewModel.logOut(navController)
                }
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
fun SearchTextField(viewModel: HomeViewModel){
    TextField(
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Text,
            imeAction = ImeAction.Search
        ),
        keyboardActions = KeyboardActions(
            onSearch = {
                viewModel.getDogsByBreed(viewModel.searchItem)
            }
        ),
        maxLines = 1,
        value = viewModel.searchItem,
        onValueChange = { viewModel.onSearchItemChanged(it) },
        label = { Text(text = "Search") },
        placeholder = {
            Text(
                text = "Golden",
                style = MaterialTheme.typography.labelSmall,
                color = MaterialTheme.colorScheme.outline
            )
        },
        trailingIcon = {
            IconButton(onClick = { viewModel.getDogsByBreed(viewModel.searchItem) }) {
                Icon(
                    imageVector = Icons.Filled.Search,
                    contentDescription = "Search"
                )
            }
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
fun ChipGroup(viewModel: HomeViewModel){
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row(
            modifier = Modifier.horizontalScroll(rememberScrollState())
        ) {
            Column(
                modifier = Modifier.padding(end = 8.dp)
            ){
                AssistChip(
                    onClick = { viewModel.getDogs() },
                    label = { Text(text = "All") },
                    colors = AssistChipDefaults.assistChipColors(
                        containerColor = MaterialTheme.colorScheme.primary,
                        labelColor = MaterialTheme.colorScheme.onTertiary
                    )
                )
            }
            Column(
                modifier = Modifier.padding(end = 8.dp)
            ){
                AssistChip(
                    onClick = { viewModel.getDogsBySex("female") },
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
                    onClick = { viewModel.getDogsBySex("male") },
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
                    onClick = { viewModel.getDogsBySize("small") },
                    label = { Text(text = "Small") },
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
                    onClick = { viewModel.getDogsBySize("medium") },
                    label = { Text(text = "Medium") },
                    colors = AssistChipDefaults.assistChipColors(
                        containerColor = MaterialTheme.colorScheme.primary,
                        labelColor = MaterialTheme.colorScheme.onTertiary
                    )
                )
            }
            Column(
                modifier = Modifier.padding(end = 8.dp)
            ){
                AssistChip(
                    onClick = { viewModel.getDogsBySize("big") },
                    label = { Text(text = "Big") },
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
    var isLiked by remember { mutableStateOf(dog.isLiked) }
    Column(
        modifier = Modifier
            .size(200.dp)
            .padding(8.dp)
            .clip(MaterialTheme.shapes.medium)
    ) {
        Box(
            modifier = Modifier.clickable {
                viewModel.onDogSelected(dog = dog, navController = navController)
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
                            text = "${dog.age} years",
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
                            isLiked = !isLiked
                            viewModel.onLikedClicked(dog, isLiked)
                        }) {
                            if(isLiked){
                                Icon(
                                    imageVector = Icons.Filled.Favorite,
                                    contentDescription = "Liked",
                                    tint = MaterialTheme.colorScheme.error
                                )
                            }
                            else{
                                Icon(
                                    imageVector = Icons.Filled.HeartBroken,
                                    contentDescription = "Not Liked",
                                    tint = MaterialTheme.colorScheme.onSecondary
                                )
                            }
                        }
                    }
                }
            }

        }
    }
}
