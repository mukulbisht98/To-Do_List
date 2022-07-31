package com.xxmukulxx.to_dolist.common.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.xxmukulxx.to_dolist.feature_todo.data.data_source.TodoDao
import com.xxmukulxx.to_dolist.feature_todo.domain.model.TodoData

@Database(
    entities = [TodoData::class],
    version = 1,
    exportSchema = false
)
abstract class AppDb : RoomDatabase() {
    abstract fun todoDao(): TodoDao

    companion object {
        const val DATABASE_NAME = "app_db"
    }
}
