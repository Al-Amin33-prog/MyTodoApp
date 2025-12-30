package com.example.Todo.data.local.database

import android.content.Context
import androidx.room.Room
import com.example.myapplication1.data.local.database.TaskDataBase

object DataBaseProvider {
    @Volatile
    private var INSTANCE: TaskDataBase? = null
    fun getDataBase(context: Context): TaskDataBase {
        return INSTANCE ?: synchronized(this){
            val instance = Room.databaseBuilder(
                context.applicationContext,
                TaskDataBase::class.java,
                "task_database"
            ).build()
            INSTANCE = instance
            instance
        }
    }
}