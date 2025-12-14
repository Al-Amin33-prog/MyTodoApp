package com.example.myapplication1


import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.CubicBezierEasing
import androidx.compose.animation.core.FastOutSlowInEasing
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
import com.example.myapplication1.ui.theme.MyApplication1Theme
import kotlinx.coroutines.delay

@Composable
fun TaskSplashLogo(
    navController: NavController,
    viewmodel: TaskViewModel = viewModel()
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
                durationMillis =  1000,
                easing = CubicBezierEasing(0.4f,0f,0.2f,1f)
            )
        )
        delay(2000)
        navController.navigate("List"){
            popUpTo("TaskLogo"){inclusive = true}
        }

    }
    Box(
        contentAlignment = Alignment.Center,
      modifier = Modifier
          .fillMaxSize()

    ) {
        Image(
            painter = painterResource(R.drawable.ic_todologo),
            contentDescription = "Logo",
            modifier = Modifier
                .fillMaxSize()
                .scale(scale.value),
            contentScale = ContentScale.Crop

        )
    }

}
@Preview(showBackground = true)
@Composable
fun logo(){
    MyApplication1Theme {


    }

}
