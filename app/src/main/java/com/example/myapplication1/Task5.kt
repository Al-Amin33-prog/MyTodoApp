package com.example.myapplication1

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch


class TaskViewModel(private val repository: TaskRepository = TaskInMemory())
    : ViewModel() {
    private val _tasks = MutableStateFlow<TaskState>(TaskState.TaskLoading)
    val  uiState: StateFlow<TaskState> = _tasks
    init {
        loadTask()
    }
    fun loadTask(){
        viewModelScope.launch {
            try {
                val task = repository.getAllTask()
                if (task.isEmpty()){
                    _tasks.value = TaskState.TaskEmpty
                }else{
                    _tasks.value = TaskState.TaskSuccess(task)
                }
            }catch (t: Throwable){
                _tasks.value = TaskState.TaskError(t.localizedMessage?:"Unknown Error")
            }
        }

    }
    fun addTask(title: String,description: String){
        viewModelScope.launch {
            try {
                repository.addTask(TaskModel(title = title, description = description))
                loadTask()
            }catch (t: Throwable){
                _tasks.value= TaskState.TaskError(t.localizedMessage?:"Unknow error")
            }
        }
    }
    fun updateTask(taskModel: TaskModel){
        viewModelScope.launch {
            try {
                repository.updateTask(taskModel)
                loadTask()
            }catch (t: Throwable){
                _tasks.value = TaskState.TaskError(t.localizedMessage?:"Unknown Error")
            }
        }
    }
    fun deleteTask(id:Int){
        viewModelScope.launch {
            try {
                repository.deleteTask(id)
                loadTask()
            }catch (t: Throwable){
                _tasks.value = TaskState.TaskError(t.localizedMessage?:"Unknown Error")
            }
        }
    }
   suspend fun getTaskB(id:Int): TaskModel?{
       return repository.getTaskById(id)

        }
    fun toggleTask(taskModel: TaskModel){
        viewModelScope.launch {
            updateTask(taskModel.copy(isDone = !taskModel.isDone))
        }
    }
  }
