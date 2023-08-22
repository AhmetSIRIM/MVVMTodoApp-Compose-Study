package com.asirim.mvvmtodoappcomposestudy.ui.todoList

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.asirim.mvvmtodoappcomposestudy.data.Todo
import com.asirim.mvvmtodoappcomposestudy.data.TodoRepository
import com.asirim.mvvmtodoappcomposestudy.util.COMPLETED_AT
import com.asirim.mvvmtodoappcomposestudy.util.DECIDED_AT
import com.asirim.mvvmtodoappcomposestudy.util.Routes
import com.asirim.mvvmtodoappcomposestudy.util.UiEvent
import com.asirim.mvvmtodoappcomposestudy.util.formatDateToLocalString
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import java.util.Date
import javax.inject.Inject

@HiltViewModel
class TodoListViewModel @Inject constructor(
    private val todoRepository: TodoRepository
) : ViewModel() {

    private val _uiEvent = Channel<UiEvent>()
    val uiEvent = _uiEvent.receiveAsFlow()

    val allTodo = todoRepository.readAllTodo()

    private var todoForUndoOptionAfterDelete: Todo? = null

    /** ["The 'todoRepository.createTodo(todoListEvent.todo.copy(isDone = todoListEvent.isDone))' part is actually an update operation due to '(onConflict = OnConflictStrategy.REPLACE)'"](https://youtu.be/A7CGcFjQQtQ?t=2867) */
    fun onEvent(todoListEvent: TodoListEvent) {

        when (todoListEvent) {

            is TodoListEvent.OnDeleteTodoClick -> {
                viewModelScope.launch {

                    todoForUndoOptionAfterDelete = todoListEvent.todo

                    todoRepository.deleteTodo(todoListEvent.todo)

                    sendUiEvent(
                        UiEvent.ShowSnackbar(
                            message = "'${todoListEvent.todo.title}' Deleted!",
                            action = "Undo"
                        )
                    )

                }
            }

            TodoListEvent.OnAddTodoClick -> {
                sendUiEvent(UiEvent.Navigate(Routes.TODO_OPERATION))
            }

            is TodoListEvent.OnDoneUpdateClick -> {
                viewModelScope.launch {

                    val todoStatePrefix = when (todoListEvent.todo.isDone) {
                        true -> DECIDED_AT
                        else -> COMPLETED_AT
                    }

                    todoRepository.createTodo(
                        todoListEvent.todo.copy(
                            decidedAt = todoStatePrefix + Date().formatDateToLocalString(),
                            isDone = todoListEvent.isDone
                        )
                    )

                }
            }

            is TodoListEvent.OnTodoClick -> sendUiEvent(
                UiEvent.Navigate(
                    Routes.TODO_OPERATION + "?todoId=${todoListEvent.todo.id}"
                )
            )

            TodoListEvent.OnUndoDeleteClick -> {
                todoForUndoOptionAfterDelete?.let {
                    viewModelScope.launch {
                        todoRepository.createTodo(it)
                    }
                }
            }

            is TodoListEvent.OnAddSampleTodoTodoClick -> {
                viewModelScope.launch {
                    todoRepository.createTodo(todoListEvent.dummyTodo)
                }
            }

        }

    }

    private fun sendUiEvent(uiEvent: UiEvent) {
        viewModelScope.launch {
            _uiEvent.send(uiEvent)
        }
    }

}