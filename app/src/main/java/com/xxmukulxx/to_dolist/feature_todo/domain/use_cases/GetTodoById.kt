package com.xxmukulxx.to_dolist.feature_todo.domain.use_cases

import com.xxmukulxx.to_dolist.feature_todo.domain.model.TodoData
import com.xxmukulxx.to_dolist.feature_todo.domain.repository.TodoRepository

class GetTodoById(private val repository: TodoRepository) {
    suspend operator fun invoke(id: Int): TodoData? {
        return repository.getTodoById(id)
    }
}