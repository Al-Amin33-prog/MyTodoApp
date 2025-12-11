package com.example.myapplication1

 sealed class TaskState {
     object TaskLoading: TaskState()
     object TaskEmpty: TaskState()
     data class TaskSuccess(val taskModel: List<TaskModel>): TaskState()
     data class TaskError(val message: String): TaskState()
}