package com.example.myapplication1.repository


import com.example.myapplication1.data.local.entity.TaskEntity
import kotlinx.coroutines.flow.Flow

interface TaskRepository{
    fun getAllTasks(): Flow<List<TaskEntity>>

    suspend fun getTaskById(taskId:Int): TaskEntity?

    suspend fun insertTask(task: TaskEntity)

    suspend fun updateTask(task: TaskEntity)

    suspend fun deleteTask(task: TaskEntity)

    suspend fun deleteAllTasks()

}