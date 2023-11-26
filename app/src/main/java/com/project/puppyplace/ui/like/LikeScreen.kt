package com.project.puppyplace.ui.like

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.SettingsInputComponent
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.project.puppyplace.R
import com.project.puppyplace.navigation.Destination
import com.project.puppyplace.ui.home.HomeViewModel


@Composable
fun LikeScreen(
   navController: NavController,
   viewModel: LikeViewModel = hiltViewModel()
){
    Column(
        modifier = Modifier.fillMaxSize()
    ){
        TopBar(navController = navController, viewModel)

        Image(
            painter = painterResource(R.drawable.like_background),
            contentDescription = "like background image",
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.FillHeight
        )

    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBar(navController: NavController, viewModel: LikeViewModel) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 6.dp, vertical = 6.dp)
            .background(Color.Blue)
    ) {
        CenterAlignedTopAppBar(
            title = {
                Text(
                    text = "Like It",
                    color = Color(0xFF4E2A00)
                )
            },
            navigationIcon = {
                IconButton(
                    onClick = {
                        viewModel.BackHome(navController)
                    }
                ) {
                    Box(
                        modifier = Modifier
                            .size(50.dp)
                            .background(Color(0xFF4E2A00))
                    ){
                        Icon(
                            modifier = Modifier.align(Alignment.Center),
                            imageVector = Icons.Filled.ArrowBack,
                            contentDescription = "Atras",
                            tint = Color.White
                        )
                    }
                }
            },
            actions = {
                Box(
                    modifier = Modifier
                        .size(40.dp)
                        .background(Color(0xFF4E2A00))
                ){
                    IconButton(
                        onClick = {

                        }
                    ) {
                        Icon(
                            imageVector = Icons.Filled.SettingsInputComponent,
                            contentDescription = "Setting",
                            tint = Color.White
                        )
                    }
                }

            }
        )
    }
}
