package com.xxmukulxx.to_dolist.feature_todo.data.repository

import com.xxmukulxx.to_dolist.feature_todo.data.data_source.TodoDao
import com.xxmukulxx.to_dolist.feature_todo.domain.model.TodoData
import com.xxmukulxx.to_dolist.feature_todo.domain.repository.TodoRepository
import kotlinx.coroutines.flow.Flow

class TodoRepoImpl(private val todoDao: TodoDao) : TodoRepository {
    override fun getTodoList(): Flow<List<TodoData>> {
        return todoDao.getTodoList()
    }

    override suspend fun getTodoById(id: Int): TodoData? {
        return todoDao.getTodoById(id)
    }

    override suspend fun insertTodo(data: TodoData) {
        return todoDao.insertTodo(data)
    }

    override suspend fun deleteTodo(id: Int) {
        return todoDao.deleteTodo(id)
    }

    override suspend fun updateTodo(data: TodoData) {
        return todoDao.updateTodo(data)
    }

    override fun searchTodoDatabase(query: String): Flow<List<TodoData>> {
        return todoDao.searchTodoDatabase(query)
    }
}