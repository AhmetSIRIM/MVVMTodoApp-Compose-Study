package com.asirim.mvvmtodoappstudy.ui.todoList

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.asirim.mvvmtodoappstudy.data.Todo
import com.asirim.mvvmtodoappstudy.data.TodoRepository
import com.asirim.mvvmtodoappstudy.util.Routes
import com.asirim.mvvmtodoappstudy.util.UiEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
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
                            message = "${todoListEvent.todo.title}  Deleted!",
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
                    todoRepository.createTodo(
                        todoListEvent.todo.copy(
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

        }

    }

    private fun sendUiEvent(uiEvent: UiEvent) {
        viewModelScope.launch {
            _uiEvent.send(uiEvent)
        }
    }

}