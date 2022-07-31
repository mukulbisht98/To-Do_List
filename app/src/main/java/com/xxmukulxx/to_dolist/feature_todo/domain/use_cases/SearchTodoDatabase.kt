package com.xxmukulxx.to_dolist.feature_todo.domain.use_cases

import com.xxmukulxx.to_dolist.feature_todo.domain.repository.TodoRepository

class SearchTodoDatabase(private val repository: TodoRepository) {
    suspend operator fun invoke(query: String) {
        repository.searchTodoDatabase(query)
    }
}