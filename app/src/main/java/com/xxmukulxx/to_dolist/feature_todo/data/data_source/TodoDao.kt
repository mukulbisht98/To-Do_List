package com.xxmukulxx.to_dolist.feature_todo.data.data_source

import androidx.room.*
import com.xxmukulxx.to_dolist.feature_todo.domain.model.TodoData
import kotlinx.coroutines.flow.Flow

@Dao
interface TodoDao {

    @Query("SELECT * FROM tododata ORDER BY id DESC")
    fun getTodoList(): Flow<List<TodoData>>

    @Query("SELECT * FROM tododata WHERE id = :id")
    suspend fun getTodoById(id: Int): TodoData?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTodo(product: TodoData)

    @Query("SELECT * FROM tododata WHERE title LIKE :searchQuery")
    fun searchTodoDatabase(searchQuery: String): Flow<List<TodoData>>

    @Query("Update tododata set title =:title,content=:content,date =:date,color =:color where id=:productId")
    suspend fun update(
        title: String,
        content: String,
        date: Long,
        color: Int,
        productId: Int
    )

    @Update
    suspend fun updateTodo(tododata: TodoData)

    @Query("DELETE FROM tododata WHERE id = :productId")
    fun deleteTodo(productId: Int)
}