package com.example.myapplication1.data.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.myapplication1.data.local.TaskEntity
import com.example.myapplication1.data.local.dao.TaskDao

@Database(
    entities = [TaskEntity::class],
    version = 1,
    exportSchema = false
)
abstract class TaskDataBase : RoomDatabase(){
    abstract fun taskDao(): TaskDao
}

