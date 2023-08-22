package com.asirim.mvvmtodoappstudy.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface TodoDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun createTodo(todo: Todo)

    @Query("SELECT * FROM todo")
    fun readAllTodo(): Flow<List<Todo>>

    @Query("SELECT * FROM todo WHERE id = :id")
    suspend fun readTodoById(id: Int): Todo?

    @Delete
    suspend fun deleteTodo(todo: Todo)

}