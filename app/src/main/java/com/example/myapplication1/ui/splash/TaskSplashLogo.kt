package com.example.myapplication1.ui.splash

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.CubicBezierEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.Todo.R
import com.example.myapplication1.viewmodel.TaskViewModel
import com.example.Todo.ui.theme.MyApplication1Theme
import com.example.myapplication1.repository.RepositoryProvider
import com.example.myapplication1.viewmodel.TaskViewModelFactory
import kotlinx.coroutines.delay

@Composable
fun TaskSplashLogo(
    navController: NavController,
){
    val scale = remember {
        Animatable(
            0f
        )
    }
    LaunchedEffect(true) {
        scale.animateTo(
            1f,
            animationSpec = tween(
                durationMillis = 1000,
                easing = CubicBezierEasing(0.4f, 0f, 0.2f, 1f)
            )
        )
        delay(2000)
        navController.navigate("List") {
            popUpTo("TaskLogo") { inclusive = true }
        }

    }
    Box(
        contentAlignment = Alignment.Companion.Center,
        modifier = Modifier.Companion
            .fillMaxSize()

    ) {
        Image(
            painter = painterResource(R.drawable.ic_todologo),
            contentDescription = "Logo",
            modifier = Modifier.Companion
                .fillMaxSize()
                .scale(scale.value),
            contentScale = ContentScale.Companion.Crop

        )
    }

}

@Preview(showBackground = true)
@Composable
fun Logo(){
    MyApplication1Theme {


    }

}