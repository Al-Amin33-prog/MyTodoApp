package com.example.myapplication1

data class TaskModel(
    val id: Int = 0,
    val title: String,
    val description: String = "",
    val isDone: Boolean = false
)
