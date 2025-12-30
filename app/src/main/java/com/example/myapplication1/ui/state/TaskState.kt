package com.example.myapplication1.ui.state

import com.example.myapplication1.data.local.entity.TaskEntity

sealed class TaskState{
     object TaskLoading: TaskState()
     object TaskEmpty: TaskState()
     data class TaskSuccess(val taskModel: List<TaskEntity>): TaskState()
     data class TaskError(val message: String): TaskState()
}