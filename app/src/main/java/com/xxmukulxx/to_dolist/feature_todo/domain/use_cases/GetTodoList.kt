package com.xxmukulxx.to_dolist.feature_todo.domain.use_cases

import com.xxmukulxx.to_dolist.feature_todo.domain.model.TodoData
import com.xxmukulxx.to_dolist.feature_todo.domain.repository.TodoRepository
import kotlinx.coroutines.flow.Flow

class GetTodoList (private val repository: TodoRepository) {
    operator fun invoke(): Flow<List<TodoData>> {
        return repository.getTodoList()
    }
}