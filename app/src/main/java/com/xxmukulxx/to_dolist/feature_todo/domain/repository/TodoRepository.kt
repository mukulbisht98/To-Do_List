package com.xxmukulxx.to_dolist.feature_todo.domain.repository

import com.xxmukulxx.to_dolist.feature_todo.domain.model.TodoData
import kotlinx.coroutines.flow.Flow

interface TodoRepository {

    fun getTodoList(): Flow<List<TodoData>>

    suspend fun getTodoById(id: Int): TodoData?

    suspend fun insertTodo(data: TodoData)

    suspend fun deleteTodo(id: Int)

    suspend fun updateTodo(data: TodoData)

    fun searchTodoDatabase(query:String) : Flow<List<TodoData>>
}