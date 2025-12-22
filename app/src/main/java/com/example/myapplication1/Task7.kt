package com.example.myapplication1

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.myapplication1.ui.theme.MyApplication1Theme
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun taskAddEditScreen(
    taskId: Int?,
    onSaved: () -> Unit,
    viewModel: TaskViewModel = viewModel()
){
    val scope  = rememberCoroutineScope()
    var title by remember { mutableStateOf("") }
    var description by remember { mutableStateOf("") }
    var hasLoaded by remember { mutableStateOf(false) }


    LaunchedEffect(taskId) {
        if (!hasLoaded && taskId != null){
            val task = viewModel.getTaskB(taskId)
            if (task != null){
                title = task.title
                description = task.description
            }
        }
        hasLoaded = true
    }
    Scaffold(
        containerColor = Color(0xFFF1F8E9),
        topBar = {
            TopAppBar(title = {
                Text(if (taskId == null)"Add Todo" else "Edit Todo",
                color = Color.Black)
            },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color.White
                )
            )
        }
    ) {padding ->
        Column(
            modifier = Modifier
                .padding(padding).padding(16.dp)
               // .background(Color(0xFFE8F5E9))
        ) {
            OutlinedTextField(
                value = title,
                onValueChange = {title = it},
                label = {Text("Title")},
                modifier = Modifier.fillMaxWidth(),
                colors = OutlinedTextFieldDefaults.colors(
                    Color.Black,
                    Color.Black,
                    Color.Black,

                    focusedBorderColor = Color.Black,
                    unfocusedBorderColor = Color.Black,

                    focusedLabelColor = Color.Black,
                    unfocusedLabelColor = Color.Black

                )
            )
            Spacer(modifier = Modifier.height(12.dp))
            OutlinedTextField(
                value = description,
                onValueChange = {description = it},
                label = {Text("Description")},
                modifier = Modifier
                    .fillMaxWidth()
                    .height(120.dp),
                colors = OutlinedTextFieldDefaults.colors(
                    Color.Black,
                    Color.Black,
                    cursorColor = Color.Black,
                    focusedBorderColor = Color.Black,
                    unfocusedBorderColor = Color.DarkGray,
                    focusedLabelColor = Color.Black,
                    unfocusedLabelColor = Color.DarkGray
                )
            )
            Spacer(modifier = Modifier.height(16.dp))
            Button(onClick = {
                if (title.isBlank()) return@Button
                scope.launch {
                    if (taskId == null){
                        viewModel.addTask(title.trim(), description.trim())
                    }else {
                        val old = viewModel.getTaskB(taskId)
                        if (old != null){
                            viewModel.updateTask(old.copy(title = title.trim(), description = description.trim()))
                        }
                    }
                    onSaved()
                }
            },
                modifier = Modifier.fillMaxWidth()

            ) {
                Text("Save")
            }
        }

    }

}

@Preview(showBackground = true)
@Composable
fun Task1Preview() {
    MyApplication1Theme {
       taskAddEditScreen(
           null,
           {},
           viewModel = viewModel()
       )
    }
}