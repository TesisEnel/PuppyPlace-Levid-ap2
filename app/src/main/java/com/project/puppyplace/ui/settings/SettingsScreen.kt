@file:OptIn(ExperimentalMaterial3Api::class)

package com.project.puppyplace.ui.settings

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ExitToApp
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.project.puppyplace.util.appBottomBar.AppBottomBar
import com.project.puppyplace.util.appTopBar.AppTopBar

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SettingsScreen(
    viewModel : SettingsViewModel = hiltViewModel(),
    navController: NavController
) {
    Scaffold(
        topBar = {
                 AppTopBar(
                     title = "Settings",
                     navController = navController,
                     actionsIcon = Icons.Filled.ExitToApp,
                     onActionsPressed = { viewModel.onTopBarActionsPressed(navController) }
                 )
        },
        bottomBar = {
            AppBottomBar(navController = navController)
        }
    ) {paddingValues ->
        SettingsScreenContent(paddingValues = paddingValues)
    }
}
@Composable
fun SettingsScreenContent(paddingValues: PaddingValues){
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(paddingValues),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        InfoMessage(paddingValues = paddingValues)
    }
}

@Composable
fun InfoMessage(paddingValues: PaddingValues){
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(paddingValues)
    ){
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(text = "We are so sorry :(", style = MaterialTheme.typography.headlineLarge)
            Text(text = "We had no time to make this screen :(")
        }
    }
}