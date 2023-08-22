package com.asirim.mvvmtodoappcomposestudy.data

import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class TodoRepositoryImpl @Inject constructor(
    private val todoDao: TodoDao
) : TodoRepository {

    override suspend fun createTodo(todo: Todo) {
        todoDao.createTodo(todo)
    }

    override fun readAllTodo(): Flow<List<Todo>> {
        return todoDao.readAllTodo()
    }

    override suspend fun readTodoById(id: Int): Todo? {
        return todoDao.readTodoById(id)
    }

    override suspend fun deleteTodo(todo: Todo) {
        return todoDao.deleteTodo(todo)
    }

}