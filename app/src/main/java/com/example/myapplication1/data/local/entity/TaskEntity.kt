package com.example.myapplication1.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "tasks")
data class TaskEntity(
    @PrimaryKey(true)
    val id: Int = 0,
    val title: String,
    val description: String ,
    val isCompleted: Boolean = false,
    val createdAt: Long = System.currentTimeMillis()
)