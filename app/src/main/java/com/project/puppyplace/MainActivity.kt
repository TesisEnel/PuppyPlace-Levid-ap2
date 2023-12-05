package com.project.puppyplace

import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import com.project.puppyplace.navigation.NavigationHost
import com.project.puppyplace.ui.NoConnection.NoConnectionScreen
import com.project.puppyplace.ui.theme.PuppyPlaceTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            PuppyPlaceTheme {
                // A surface container using the 'background' color from the theme
                val connectivityManager = LocalContext.current.getSystemService(CONNECTIVITY_SERVICE)
                        as ConnectivityManager
                val network = connectivityManager.activeNetwork
                val networkCapabilities = connectivityManager.getNetworkCapabilities(network)
                val isConnected = network != null && networkCapabilities != null &&
                        networkCapabilities.hasCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET)
                if(!isConnected){
                    NoConnectionScreen()
                }
                else{
                    Surface(
                        modifier = Modifier.fillMaxSize(),
                        color = MaterialTheme.colorScheme.background
                    ) {
                        NavigationHost()
                    }
                }
            }
        }
    }
}
