@file:OptIn(ExperimentalMaterial3Api::class)

package com.project.puppyplace.util.appTopBar

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController

@Composable
fun AppTopBar(
    viewModel: AppTopBarViewModel = hiltViewModel(),
    onNavigationPressed: (() -> Unit)? = null,
    navIcon: ImageVector? = null,
    title: String,
    onActionsPressed: (() -> Unit)? = null,
    actionsIcon: ImageVector? = Icons.Filled.Settings,
    navController: NavController
){
    CenterAlignedTopAppBar(
        title = { Text(text = title) },
        navigationIcon = {
            IconButton(
                onClick = {
                    if(onNavigationPressed == null){
                        viewModel.onTopBarNavigationPressed(navController)
                    }else{
                        onNavigationPressed()
                    }
                }
            ) {
                Icon(
                    imageVector = navIcon ?: Icons.Filled.ArrowBack,
                    contentDescription = "Nav"
                )
            }
        },
        actions = {
            IconButton(
                onClick = {
                    if(onActionsPressed == null){
                        viewModel.onTopBarActionsPressed(navController)
                    }else{
                        onActionsPressed()
                    }
                }
            ) {
                Icon(
                    imageVector = actionsIcon ?: Icons.Filled.Settings,
                    contentDescription = "Actions"
                )
            }
        }
    )
}