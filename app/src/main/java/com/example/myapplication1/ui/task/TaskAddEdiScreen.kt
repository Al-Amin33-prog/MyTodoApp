package com.example.Todo.ui.task

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
import com.example.myapplication1.viewmodel.TaskViewModel
import com.example.Todo.ui.theme.MyApplication1Theme
import com.example.myapplication1.data.local.entity.TaskEntity
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TaskAddEditScreen(
    taskId: Int?,
    onSaved: () -> Unit,
    viewModel: TaskViewModel
){
    val scope  = rememberCoroutineScope()
    var title by remember { mutableStateOf("") }
    var description by remember { mutableStateOf("") }
    var hasLoaded by remember { mutableStateOf(false) }


    LaunchedEffect(taskId) {
        if (!hasLoaded && taskId != null) {
            val task = viewModel.getTaskById(taskId)
            if (task != null) {
                title = task.title
                description = task.description
            }
        }
        hasLoaded = true
    }
    Scaffold(
        containerColor = Color(0xFFF1F8E9),
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        if (taskId == null) "Add Todo" else "Edit Todo",
                        color = Color.Companion.Black
                    )
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color.Companion.White
                )
            )
        }
    ) { padding ->
        Column(
            modifier = Modifier.Companion
                .padding(padding).padding(16.dp)
            // .background(Color(0xFFE8F5E9))
        ) {
            OutlinedTextField(
                value = title,
                onValueChange = { title = it },
                label = { Text("Title") },
                modifier = Modifier.Companion.fillMaxWidth(),
                colors = OutlinedTextFieldDefaults.colors(
                    Color.Companion.Black,
                    Color.Companion.Black,
                    Color.Companion.Black,

                    focusedBorderColor = Color.Companion.Black,
                    unfocusedBorderColor = Color.Companion.Black,

                    focusedLabelColor = Color.Companion.Black,
                    unfocusedLabelColor = Color.Companion.Black

                )
            )
            Spacer(modifier = Modifier.Companion.height(12.dp))
            OutlinedTextField(
                value = description,
                onValueChange = { description = it },
                label = { Text("Description") },
                modifier = Modifier.Companion
                    .fillMaxWidth()
                    .height(120.dp),
                colors = OutlinedTextFieldDefaults.colors(
                    Color.Companion.Black,
                    Color.Companion.Black,
                    cursorColor = Color.Companion.Black,
                    focusedBorderColor = Color.Companion.Black,
                    unfocusedBorderColor = Color.Companion.DarkGray,
                    focusedLabelColor = Color.Companion.Black,
                    unfocusedLabelColor = Color.Companion.DarkGray
                )
            )
            Spacer(modifier = Modifier.Companion.height(16.dp))
            Button(
                onClick = {
                    if (title.isBlank()) return@Button
                    scope.launch {
                        if (taskId == null) {
                            viewModel.insertTask(
                                TaskEntity(
                                    title = title.trim(),
                                    description = description.trim()
                                )
                            )
                        } else {
                            val old = viewModel.getTaskById(taskId)
                            if (old != null) {
                                viewModel.updateTask(
                                    old.copy(
                                        title = title.trim(),
                                        description = description.trim()
                                    )
                                )
                            }
                        }
                        onSaved()
                    }
                },
                modifier = Modifier.Companion.fillMaxWidth()

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
        TaskAddEditScreen(
            null,
            {},
            viewModel = viewModel()
        )
    }
}