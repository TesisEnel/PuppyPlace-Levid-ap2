package com.project.puppyplace.ui.NoConnection

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import com.project.puppyplace.R

@Composable
fun NoConnectionScreen() {
    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        BackgroundImage()
        InformationText()
    }

}

@Composable
fun BackgroundImage(){
    Image(
        painter = painterResource(id = R.drawable.nointernet_screen), // Esta es la imagen
        contentDescription = "Background",
        contentScale = ContentScale.FillBounds
    )
}
@Composable
fun InformationText(){
    Text(
        text = "No internet connection",
        style = MaterialTheme.typography.headlineLarge,
    )
}