package com.asirim.mvvmtodoappstudy.ui.todoOperation

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.asirim.mvvmtodoappstudy.data.Todo
import com.asirim.mvvmtodoappstudy.data.TodoRepository
import com.asirim.mvvmtodoappstudy.util.DECIDED_AT
import com.asirim.mvvmtodoappstudy.util.UiEvent
import com.asirim.mvvmtodoappstudy.util.formatDateToLocalString
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import java.util.Date
import javax.inject.Inject

@HiltViewModel
class TodoOperationViewModel @Inject constructor(
    private val todoRepository: TodoRepository,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    var todo by mutableStateOf<Todo?>(null)
        private set

    var title by mutableStateOf(EMPTY_STRING)
        private set

    var description by mutableStateOf(EMPTY_STRING)
        private set

    private val _uiEvent = Channel<UiEvent>()
    val uiEvent = _uiEvent.receiveAsFlow()

    init {

        val todoId = savedStateHandle.get<Int>("todoId") ?: INVALID_TODO_ID

        if (todoId != INVALID_TODO_ID) {
            viewModelScope.launch {
                todoRepository.readTodoById(todoId)?.let { todo ->
                    title = todo.title
                    description = todo.description ?: EMPTY_STRING
                    this@TodoOperationViewModel.todo = todo
                }
            }
        }

    }


    fun onEvent(todoOperationEvent: TodoOperationEvent) {

        when (todoOperationEvent) {

            is TodoOperationEvent.OnTitleChangeClick -> title = todoOperationEvent.title

            is TodoOperationEvent.OnDescriptionChangeClick -> description =
                todoOperationEvent.description

            is TodoOperationEvent.OnSaveTodoClick -> {

                viewModelScope.launch {

                    if (title.isBlank()) {

                        sendUiEvent(
                            UiEvent.ShowSnackbar(
                                message = EMPTY_TITLE_WARNING
                            )
                        )

                        return@launch
                    }

                    todoRepository.createTodo(
                        Todo(
                            id = todo?.id,
                            title = title,
                            description = description,
                            decidedAt = DECIDED_AT + Date().formatDateToLocalString(),
                            isDone = todo?.isDone ?: false
                        )
                    )

                    sendUiEvent(UiEvent.PopBackStack)

                }

            }

        }

    }

    private fun sendUiEvent(uiEvent: UiEvent) {
        viewModelScope.launch {
            _uiEvent.send(uiEvent)
        }
    }

    companion object {
        const val EMPTY_STRING = ""
        const val INVALID_TODO_ID = -1
        const val EMPTY_TITLE_WARNING = "The title can't be empty"
    }

}