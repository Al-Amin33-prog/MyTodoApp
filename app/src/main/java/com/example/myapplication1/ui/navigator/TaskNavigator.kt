package com.example.myapplication1.ui.navigator


import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.myapplication1.ui.login.LoginScreen
import com.example.myapplication1.ui.splash.TaskSplashLogo
import com.example.myapplication1.ui.task.TaskScreen
import com.example.Todo.ui.task.TaskAddEditScreen
import com.example.myapplication1.repository.RepositoryProvider
import com.example.myapplication1.viewmodel.TaskViewModel
import com.example.myapplication1.viewmodel.TaskViewModelFactory

@Composable
fun TaskNavigator(){

    val navController = rememberNavController()
    val taskViewModel: TaskViewModel = viewModel(
        factory = TaskViewModelFactory(
            RepositoryProvider.taskRepository
        )
    )

    NavHost(
        navController = navController,
        startDestination = "task_logo"
    ) {
        composable("task_logo") {
            TaskSplashLogo(navController)

        }
        composable("login") {
            LoginScreen(
                onSkip = {
                    navController.navigate("list") {
                        popUpTo("login") { inclusive = true }
                    }
                }
            )


        }
        composable("list") {
            TaskScreen(
                onAddClick = { navController.navigate("add") },
                onEditClick = { taskId -> navController.navigate("edit/$taskId") },
              viewModel = taskViewModel

            )
        }
        composable("add") {
            TaskAddEditScreen(
                taskId = null,
                onSaved = {
                    navController.popBackStack()
                },
                viewModel = taskViewModel
            )

        }
        composable(
            "edit/{taskId}",
            arguments = listOf(navArgument("taskId") { type = NavType.Companion.IntType })
        ) { backStack ->
            val id = backStack.arguments?.getInt("taskId")
            TaskAddEditScreen(
                taskId = id,
                onSaved = { navController.popBackStack() },
                viewModel = taskViewModel
            )
        }
    }


}