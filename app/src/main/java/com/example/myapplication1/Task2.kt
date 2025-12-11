package com.example.myapplication1

interface TaskRepository {
    suspend fun getAllTask(): List<TaskModel>

    suspend fun addTask(taskModel: TaskModel): TaskModel

    suspend fun updateTask(taskModel: TaskModel): TaskModel

    suspend fun deleteTask(id: Int): Boolean

    suspend fun getTaskById(id: Int): TaskModel?
}