package com.xxmukulxx.to_dolist.feature_todo.domain

import com.xxmukulxx.to_dolist.feature_todo.domain.use_cases.*

data class TodoUseCases(
    val getTodoList: GetTodoList,
    val getTodoById: GetTodoById,
    val insertTodo: InsertTodo,
    val deleteTodo: DeleteTodo,
    val updateTodo: UpdateTodo,
    val searchTodoDatabase: SearchTodoDatabase
)