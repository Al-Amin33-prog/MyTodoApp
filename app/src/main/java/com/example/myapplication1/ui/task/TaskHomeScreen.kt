package com.example.myapplication1.ui.task

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.Todo.R
import com.example.Todo.ui.theme.MyApplication1Theme
import com.example.myapplication1.data.local.entity.TaskEntity
import com.example.myapplication1.repository.RepositoryProvider
import com.example.myapplication1.repository.TaskRepository
import com.example.myapplication1.ui.state.TaskState
import com.example.myapplication1.viewmodel.TaskViewModel
import com.example.myapplication1.viewmodel.TaskViewModelFactory

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TaskScreen(
    onAddClick: () -> Unit,
    onEditClick: (Int) -> Unit,
    viewModel: TaskViewModel
    )

    {

    val state by viewModel.uiState.collectAsState()
    Scaffold(
        containerColor = Color(0xFFE8F5E9),
        topBar = {
            TopAppBar(
                title = { Text("My Todo") },
                colors = TopAppBarDefaults.topAppBarColors(
                    Color(0xFF2E7D32),
                    titleContentColor = Color.Companion.White
                )

            )
        },
        floatingActionButton = {
            FloatingActionButton(onAddClick) {
                Text("+")
            }
        }
    ) { padding ->
        Box(
            modifier = Modifier.Companion
                .fillMaxSize()
                .padding(padding)
        ) {
            when (state) {
                is TaskState.TaskLoading -> {
                    CircularProgressIndicator(modifier = Modifier.Companion.align(Alignment.Companion.Center))
                }

                is TaskState.TaskEmpty -> {
                    Text(
                        "No todos yet, Tap the + to add one",
                        modifier = Modifier.Companion.align(Alignment.Center)
                    )
                }

                is TaskState.TaskError -> {
                    Text(
                        text = (state as TaskState.TaskError).message,
                        modifier = Modifier.Companion.align(Alignment.Center)
                    )
                }

                is TaskState.TaskSuccess -> {
                    val tasks = (state as TaskState.TaskSuccess).taskModel
                    LazyColumn(modifier = Modifier.Companion.fillMaxSize()) {
                        items(tasks, key = { it.id }) { tasks ->
                            TaskItem(
                                task = tasks,
                                onCheckedChange = { checked ->
                                    viewModel.updateTask(tasks.copy(isCompleted = checked))

                                },
                                onEditClick = { onEditClick(tasks.id) },
                                onDeleteClick = { viewModel.deleteTask(tasks) }

                            )

                        }
                    }
                }
            }
        }

    }


}

@Composable
fun TaskItem(
    task: TaskEntity,
    onCheckedChange: (Boolean )-> Unit,
    onEditClick:() -> Unit,
    onDeleteClick:() -> Unit
){
    Card(
        modifier = Modifier.Companion
            .padding(8.dp)
            .fillMaxWidth()
            .clickable { onEditClick() },
        elevation = CardDefaults.cardElevation(4.dp)
    ) {
        Row(
            modifier = Modifier.Companion.padding(12.dp),
            verticalAlignment = Alignment.Companion.CenterVertically


        ) {
            Checkbox(
                checked = task.isCompleted,
                onCheckedChange = onCheckedChange,
                colors = CheckboxDefaults.colors(
                    checkedColor = Color(0xFF2E7D32),
                    checkmarkColor = Color.Companion.White,
                    uncheckedColor = Color.Companion.Gray
                )
            )
            Spacer(modifier = Modifier.Companion.width(12.dp))
            Column(modifier = Modifier.Companion.weight(1f)) {
                Text(task.title, style = MaterialTheme.typography.titleMedium)
                if (task.description.isNotBlank()) {
                    Spacer(modifier = Modifier.Companion.height(4.dp))
                    Text(task.description, style = MaterialTheme.typography.bodySmall)
                }
            }
            Spacer(modifier = Modifier.Companion.width(8.dp))
            IconButton(onClick = onEditClick) {
                Icon(
                    painter = painterResource(R.drawable.ic_edit),

                    contentDescription = "Edit"
                )
            }
            IconButton(onClick = onDeleteClick) {
                Icon(
                    painter = painterResource(R.drawable.ic_delete),
                    contentDescription = "Delete"
                )
            }
        }

    }

}

@Preview(showBackground = true)
@Composable
fun Task01Preview() {
    MyApplication1Theme {


    }
}