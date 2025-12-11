package com.example.myapplication1

import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock

class TaskInMemory: TaskRepository {
    private val mutex = Mutex()

    private val items = mutableListOf<TaskModel>()

    private var nextId = 1

    override suspend fun getAllTask(): List<TaskModel> = mutex.withLock {
        items.toList()
    }
    override suspend fun addTask(taskModel: TaskModel): TaskModel = mutex.withLock {
        val withId = taskModel.copy(id = nextId++)
        items.add(withId)
        withId
    }
    override suspend fun updateTask(taskModel: TaskModel): TaskModel = mutex.withLock {
        val index = items.indexOfFirst{it.id == taskModel.id}
        if (index >= 0){
            items[index] = taskModel
            taskModel
        }else{
            throw IllegalArgumentException("Todo not found:${taskModel.id}")
        }
    }

    override suspend fun deleteTask(id: Int) = mutex.withLock {
        items.removeAll { it.id == id }
    }

    override suspend fun getTaskById(id: Int): TaskModel? = mutex.withLock {
        items.find { it.id == id }
    }
}