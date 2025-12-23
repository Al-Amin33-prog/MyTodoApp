package com.example.myapplication1.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "tasks")
data class TaskEntity(
    @PrimaryKey(true)
    val id: Int = 0,
    val title: String,
    val description: String = "",
    val isDone: Boolean = false
)