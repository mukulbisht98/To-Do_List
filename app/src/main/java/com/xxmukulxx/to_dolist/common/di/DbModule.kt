package com.xxmukulxx.to_dolist.common.di

import android.app.Application
import androidx.room.Room
import com.xxmukulxx.to_dolist.common.database.AppDb
import com.xxmukulxx.to_dolist.feature_todo.data.data_source.TodoDao
import com.xxmukulxx.to_dolist.feature_todo.data.repository.TodoRepoImpl
import com.xxmukulxx.to_dolist.feature_todo.domain.TodoUseCases
import com.xxmukulxx.to_dolist.feature_todo.domain.repository.TodoRepository
import com.xxmukulxx.to_dolist.feature_todo.domain.use_cases.*
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DbModule {

    @Provides
    @Singleton
    fun provideAppDatabase(app: Application): AppDb =
        Room.databaseBuilder(app, AppDb::class.java, AppDb.DATABASE_NAME)
            .allowMainThreadQueries()
            .fallbackToDestructiveMigration().build()

    @Provides
    @Singleton
    fun provideTodoDao(appDb: AppDb): TodoDao = appDb.todoDao()

    @Provides
    @Singleton
    fun providesTodoRepository(dao: TodoDao): TodoRepository = TodoRepoImpl(dao)

    @Provides
    @Singleton
    fun providesTodoUseCases(repo: TodoRepository): TodoUseCases = TodoUseCases(
        getTodoList = GetTodoList(repo),
        getTodoById = GetTodoById(repo),
        insertTodo = InsertTodo(repo),
        deleteTodo = DeleteTodo(repo),
        updateTodo = UpdateTodo(repo),
        searchTodoDatabase = SearchTodoDatabase(repo)
    )
}