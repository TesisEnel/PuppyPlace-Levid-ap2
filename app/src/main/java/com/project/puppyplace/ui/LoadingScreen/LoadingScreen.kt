package com.project.puppyplace.ui.LoadingScreen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.project.puppyplace.R
import com.project.puppyplace.navigation.Destination


@OptIn(ExperimentalComposeUiApi::class, ExperimentalMaterial3Api::class)
@Composable
fun LoadingScreen(
    navController: NavController
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFFCB72C)) // Orange color
    ) {
        Text(
            text = "Make a New Friend!",
            style = MaterialTheme.typography.displayLarge,
            modifier = Modifier.padding(start = 10.dp, top = 30.dp, end = 20.dp),
            color = Color.White

        )

        Box(
            //modifier = Modifier.offset(y = (-40).dp)
        ) {
            Image(
                painter = painterResource(id = R.drawable.logo),
                contentDescription = "Puppy image",
                modifier = Modifier.size(600.dp)
            )

            Text(
                text = "Puppy Place",
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.displaySmall.copy(fontWeight = FontWeight.Bold),
                modifier = Modifier
                    .padding(top = 405.dp)
                    .fillMaxWidth(),
                color = Color.White
            )
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal=16.dp),
            horizontalArrangement = Arrangement.End
        ) {
            Button(
                onClick = { navController.navigate(Destination.login.route) },
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color.White,
                    contentColor = Color.Black
                ),
                shape = RoundedCornerShape(10.dp),
            ) {
                Text(text = "Let's Go!")
            }
        }

    }
}