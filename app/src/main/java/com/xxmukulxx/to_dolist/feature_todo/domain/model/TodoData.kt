package com.xxmukulxx.to_dolist.feature_todo.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class TodoData(
    val title: String,
    val content: String,
    val date: Long,
    val color: Int,
    @PrimaryKey(autoGenerate = true) val id: Int = 0
)

class InvalidTodoItemException(message: String) : Exception(message)