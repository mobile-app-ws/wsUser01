package com.dashkovskiy.world_skills_medic_app.ui.launch

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.dashkovskiy.world_skills_medic_app.R
import kotlinx.coroutines.delay

@Composable
fun LaunchScreen(
    navigateNext : () -> Unit = {}
) {
    LaunchedEffect(Unit){
        delay(1000L)
        navigateNext()
    }
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Image(
            modifier = Modifier.fillMaxSize(),
            painter = painterResource(R.drawable.onboard_background),
            contentDescription = null,
            contentScale = ContentScale.FillBounds
        )
        Icon(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 56.dp),
            painter = painterResource(R.drawable.logo),
            tint = Color.White,
            contentDescription = null
        )
    }
}

@Preview
@Composable
fun LaunchScreenPreview() {
    LaunchScreen()
}