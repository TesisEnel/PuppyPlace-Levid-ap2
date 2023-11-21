package com.project.puppyplace.ui.PuppyPlace

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier // Missing import statement
import androidx.compose.ui.draw.clipToBounds
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import com.project.puppyplace.R
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp


@OptIn(ExperimentalComposeUiApi::class, ExperimentalMaterial3Api::class)
@Composable
fun LoadingScreen() {
    Surface(
        modifier = Modifier
            .fillMaxSize()
            .clipToBounds()
            .background(Color(0xFFFFA500)) // Orange color
    ) {
        Text(
            text = "Make a New Friend!",
            style = MaterialTheme.typography.displayLarge,
            modifier = Modifier
                .align(Alignment.TopCenter) // Align text to top center
        )

        Image(
            painter = painterResource(id = R.drawable.logo),
            contentDescription = "Puppy image",
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight()
                .align(Alignment.Center) // Align image to center
        )

        Text(
            text = "Puppy Place",
            style = MaterialTheme.typography.displaySmall.copy(fontWeight = FontWeight.Bold),
            modifier = Modifier
                .align(Alignment.BottomCenter) // Align text to bottom center
                .padding(bottom = 220.dp),
            color = Color.White
        )
    }
}