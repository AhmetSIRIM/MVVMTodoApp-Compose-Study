package com.asirim.mvvmtodoappstudy.data

import kotlinx.coroutines.flow.Flow

interface TodoRepository {

    suspend fun createTodo(todo: Todo)

    fun readAllTodo(): Flow<List<Todo>>

    suspend fun readTodoById(id: Int): Todo

    suspend fun deleteTodo(todo: Todo)

}