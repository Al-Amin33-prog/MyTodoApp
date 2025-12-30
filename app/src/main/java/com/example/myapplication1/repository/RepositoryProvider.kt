package com.example.myapplication1.repository

import android.content.Context
import com.example.Todo.data.local.database.DataBaseProvider

object RepositoryProvider {
    lateinit var taskRepository: TaskRepository
    fun init(context: Context){
        val db = DataBaseProvider.getDataBase(context)
        taskRepository = TaskRepositoryImpl(db.taskDao())
    }
}