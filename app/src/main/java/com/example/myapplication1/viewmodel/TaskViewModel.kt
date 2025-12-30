package com.example.myapplication1.viewmodel


import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplication1.data.local.entity.TaskEntity
import com.example.myapplication1.repository.TaskRepository
import com.example.myapplication1.ui.state.TaskState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch


class TaskViewModel(
    private val repository: TaskRepository
): ViewModel() {
   private val _uiState = MutableStateFlow<TaskState>(TaskState.TaskLoading)

    val uiState: StateFlow<TaskState> = _uiState.asStateFlow()
    init {
      observeTasks()
    }
    private fun observeTasks(){
        viewModelScope.launch {
            repository.getAllTasks()
                .collect { taskList ->
                    _uiState.value = when {
                        taskList.isEmpty() -> TaskState.TaskEmpty
                        else -> TaskState.TaskSuccess(taskList)
                    }

                }
        }
    }
    fun insertTask(task: TaskEntity){
        viewModelScope.launch {
            repository.insertTask(task)
        }
    }
    fun updateTask(task: TaskEntity){
        viewModelScope.launch {
            repository.updateTask(task)
        }
    }
    fun deleteTask(task: TaskEntity){
        viewModelScope.launch {
            repository.deleteTask(task)
        }
    }
    fun deleteAllTasks(){
        viewModelScope.launch {
            repository.deleteAllTasks()
        }
    }
    suspend fun getTaskById(taskId: Int): TaskEntity?{
        return repository.getTaskById(taskId)
    }



}


