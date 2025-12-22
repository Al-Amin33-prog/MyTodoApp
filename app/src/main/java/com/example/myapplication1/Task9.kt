package com.example.myapplication1

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument

@Composable
fun TaskNavigator( taskViewmodel: TaskViewModel =  viewModel()){
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = "task_logo"
    ){
        composable("task_logo") {
            TaskSplashLogo(navController)

        }
        composable("login") {
            LoginScreen (
                onSkip = {
                    navController.navigate("list"){
                        popUpTo("login") {inclusive = true  }
                    }
                }
            )


        }
        composable("list") {
            TaskScreen(
                onAddClick = {navController.navigate("add")},
                onEditClick = {taskId-> navController.navigate("edit/$taskId")},
                viewModel = taskViewmodel

            )
        }
        composable("add") {
            taskAddEditScreen(
                taskId = null,
                onSaved = {
                    navController.popBackStack()
                },
                viewModel = taskViewmodel
            )

        }
        composable(
            "edit/{taskId}",
            arguments = listOf(navArgument("taskId"){type = NavType.IntType})
        ){backStack ->
            val id = backStack.arguments?.getInt("taskId")
            taskAddEditScreen(
                taskId = id,
                onSaved = {navController.popBackStack()},
                viewModel = taskViewmodel
            )
        }
    }


}