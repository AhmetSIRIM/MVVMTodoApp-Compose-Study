package com.asirim.mvvmtodoappstudy.ui.todoList

import com.asirim.mvvmtodoappstudy.data.Todo

sealed class TodoListEvent {

    data class OnDeleteTodoClick(
        val todo: Todo
    ) : TodoListEvent()

    data class OnDoneUpdateClick(
        val todo: Todo,
        val isDone: Boolean
    ) : TodoListEvent()

    object OnUndoDeleteClick : TodoListEvent()

    data class OnTodoClick(
        val todo: Todo
    ) : TodoListEvent()

    object OnAddTodoClick : TodoListEvent()

    data class OnAddSampleTodoTodoClick(
        val dummyTodo: Todo
    ) : TodoListEvent()

}