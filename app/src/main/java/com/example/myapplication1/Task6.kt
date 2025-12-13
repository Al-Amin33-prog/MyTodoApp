package com.example.myapplication1

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
import com.example.myapplication1.ui.theme.MyApplication1Theme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TaskScreen(
    onAddClick: () -> Unit,
    onEditClick: (Int) -> Unit,
    viewModel: TaskViewModel = viewModel()
){
    val state by viewModel.uiState.collectAsState()
    Scaffold(
        containerColor = Color(0xFFE8F5E9),
        topBar = {
            TopAppBar(
                title = { Text("My Todo") },
                colors = TopAppBarDefaults.topAppBarColors(
                    Color(0xFF2E7D32),
                    titleContentColor = Color.White
                )

                )
        },
        floatingActionButton = {
            FloatingActionButton(onAddClick) {
                Text("+")
            }
        }
    ) {padding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
        ) {
            when(state){
                is TaskState.TaskLoading-> {
                    CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
                }
                is TaskState.TaskEmpty -> {
                    Text(
                        "No todos yet, Tap the + to add one",
                       modifier = Modifier.align(Alignment.Center)
                    )
                }
                is TaskState.TaskError -> {
                    Text(
                        text = (state as TaskState.TaskError).message,
                        modifier = Modifier.align(Alignment.Center)
                    )
                }
                is TaskState.TaskSuccess -> {
                    val tasks = (state as TaskState.TaskSuccess).taskModel
                    LazyColumn(modifier = Modifier.fillMaxSize()) {
                        items(tasks, key = {it.id}){tasks ->
                            TaskItem(
                                task = tasks,
                                onCheckedChange = {checked ->
                                    viewModel.toggleTask(tasks.copy(isDone = checked))

                                },
                                onEditClick = {onEditClick(tasks.id)},
                                onDeleteClick = {viewModel.deleteTask(tasks.id)}

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
    task: TaskModel,
    onCheckedChange: (Boolean )-> Unit,
    onEditClick:() -> Unit,
    onDeleteClick:() -> Unit
){
    Card(
        modifier = Modifier
            .padding(8.dp)
            .fillMaxWidth()
            .clickable{onEditClick()},
        elevation = CardDefaults.cardElevation(4.dp)
    ) {
        Row(
          modifier = Modifier.padding(12.dp),
            verticalAlignment = Alignment.CenterVertically


        ) {
            Checkbox(
                checked = task.isDone,
                onCheckedChange = onCheckedChange
            )
            Spacer(modifier = Modifier.width(12.dp))
            Column(modifier = Modifier.weight(1f)) {
                Text(task.title, style = MaterialTheme.typography.titleMedium)
                if (task.description.isNotBlank()){
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(task.description, style = MaterialTheme.typography.bodySmall)
                }
            }
            Spacer(modifier = Modifier.width(8.dp))
            IconButton(onClick = onEditClick) {
                Icon(painter = painterResource(R.drawable.ic_edit, ),

                    contentDescription = "Edit")
            }
            IconButton(onClick = onDeleteClick) {
                Icon(painter = painterResource(R.drawable.ic_delete, ),
                    contentDescription = "Delete")
            }
        }

    }

}

@Preview(showBackground = true)
@Composable
fun Task01Preview() {
    MyApplication1Theme {
        TaskScreen(
            {},
            {},
            viewModel = viewModel()
        )
    }
}